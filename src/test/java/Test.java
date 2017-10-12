import de.quinscape.exceed.runtime.util.JsUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test
{
    private final static Logger log = LoggerFactory.getLogger(Test.class);


    @org.junit.Test
    public void test() throws Exception
    {
        final ScriptObjectMirror o = (ScriptObjectMirror) JsUtil.createEngine().eval("([1,2,3])");

        log.info("RESULT: {}", o.isArray());

    }
}
