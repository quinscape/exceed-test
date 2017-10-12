package de.quinscape.exceedapp.config;

import de.quinscape.exceed.runtime.RuntimeContext;
import de.quinscape.exceed.runtime.domain.DomainObject;
import de.quinscape.exceed.runtime.util.JSONUtil;
import de.quinscape.exceedapp.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyOrderService
    implements OrderService
{
    private final static Logger log = LoggerFactory.getLogger(DummyOrderService.class);

    @Override
    public void cancelOrder(RuntimeContext runtimeContext, DomainObject domainObject)
    {
        log.info("CANCEL ORDER: {}", dump(runtimeContext, domainObject));
    }


    private String dump(RuntimeContext runtimeContext, DomainObject domainObject)
    {
        return JSONUtil.formatJSON(runtimeContext.getDomainService().toJSON(domainObject));
    }
}
