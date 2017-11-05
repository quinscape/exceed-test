import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletContext;

@Configuration
public class TestConfiguration
{
    public ServletContext servletContext()
    {
        return new MockServletContext();
    }
}
