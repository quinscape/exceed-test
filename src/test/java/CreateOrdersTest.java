import de.quinscape.exceed.runtime.config.DomainConfiguration;
import de.quinscape.exceed.runtime.config.ModelConfiguration;
import de.quinscape.exceed.runtime.config.ServiceConfiguration;
import de.quinscape.exceed.runtime.util.DomainUtil;
import de.quinscape.exceedapp.config.ShopActions;
import de.quinscape.exceedapp.domain.tables.pojos.Customer;
import de.quinscape.exceedapp.domain.tables.pojos.Product;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static de.quinscape.exceedapp.domain.Tables.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    ModelConfiguration.class,
    DomainConfiguration.class,
    ServiceConfiguration.class,
})
@Transactional
public class CreateOrdersTest
{
    private final static Logger log = LoggerFactory.getLogger(CreateOrdersTest.class);

    @Autowired
    private DSLContext dslContext;

    @Test
    @Rollback(false)
    public void testDSL() throws Exception
    {
        final Map<String, Product> products =
            DomainUtil.mapById(
                dslContext.select()
                    .from(PRODUCT)
                    .where(PRODUCT.DISCONTINUED.eq(false))
                    .fetchInto(Product.class)
            );

        final Map<String, Customer> customers =
            DomainUtil.mapById(
                dslContext.select()
                    .from(CUSTOMER)
                    .fetchInto(Customer.class)
            );

        List<String> productIds = products.values().stream().map( Product::getId ).collect(Collectors.toList());
        List<String> customerIds = customers.values().stream().map( Customer::getId ).collect(Collectors.toList());

        Random rnd = new Random();

        final ShopActions shopActions = new ShopActions(dslContext);

        for (int i = 0; i < 100; i++)
        {
            final Product product = random(rnd, productIds, products);
            final Customer customer = random(rnd, customerIds, customers);

            final String orderId = UUID.randomUUID().toString();
            log.info("CREATE ORDER: {}",
                dslContext.insertInto(ORDER)
                .set(ORDER.ID, orderId)
                .set(ORDER.NUMBER, shopActions.newOrderNumber())
                .set(ORDER.CUSTOMER_ID, customer.getId())
                .set(ORDER.ACCEPTED, Timestamp.valueOf(LocalDateTime.now().minus( rnd.nextInt(180), ChronoUnit.DAYS).toLocalDate().atStartOfDay()))
                .set(ORDER.SHIPPING_TYPE, 0)
                .set(ORDER.STATUS, randomStatus(rnd))
                .set(ORDER.SUM, product.getPrice())
                .execute()
            );

            log.info("CREATE OrderItem: {}",
                dslContext.insertInto(ORDER_ITEM)
                    .set(ORDER_ITEM.ID, UUID.randomUUID().toString())
                    .set(ORDER_ITEM.ORDER_ID, orderId)
                    .set(ORDER_ITEM.PRODUCT_ID, product.getId())
                    .set(ORDER_ITEM.QUANTITY, 1)
                    .execute()
            );

            log.info("{}, {}", product, customer);
        }

    }

    private final static Map<String, Integer> STATUS_WEIGHTS;
    private final static double WEIGHT_SCALE;

    static
    {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("ACCEPTED", 5);
        map.put("READY", 5);
        map.put("SENT", 5);
        map.put("DELIVERED", 5);
        map.put("RETURNED", 10);
        map.put("LOST_ON_DELIVERY", 1);
        map.put("LOST_ON_RETURN", 1);
        map.put("PAID", 70);
        map.put("CANCELED", 5);

        final Integer sumOfWeights = map.values().stream().reduce(Integer::sum).orElse(0);

        WEIGHT_SCALE = 1.0 / sumOfWeights;
        STATUS_WEIGHTS = Collections.unmodifiableMap(map);
    }



    private String randomStatus(Random rnd)
    {
        double v = rnd.nextDouble();

        for (Map.Entry<String, Integer> e : STATUS_WEIGHTS.entrySet())
        {
            v -= e.getValue() * WEIGHT_SCALE;

            if (v <= 0)
            {
                return e.getKey();
            }
        }
        return "ACCEPTED";
    }


    private <T> T random(Random rnd, List<String> productIds, Map<String, T> products)
    {
        final int index = rnd.nextInt(productIds.size());
        return products.get(productIds.get(index));
    }
}
