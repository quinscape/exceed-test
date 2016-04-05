import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

/**
 * Created by sven on 25.10.15.
 */
public class Test
{

    private final static Logger log = LoggerFactory.getLogger(Test.class);


    @org.junit.Test
    public void testName() throws Exception
    {
        log.info("{}", Instant.parse("2015-09-08T01:55:28Z"));


    }
}
