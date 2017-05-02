package de.quinscape.exceedapp;

import de.quinscape.exceed.runtime.ExceedApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import({
    ExceedApplicationConfiguration.class,
})
@Configuration
public class EditorConfiguration
    extends SpringBootServletInitializer
{
    private final static Logger log = LoggerFactory.getLogger(EditorConfiguration.class);

    public static void main(String[] args)
    {
        SpringApplication.run(EditorConfiguration.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        // Customize the application or call application.sources(...) to add sources

        // Since our example is itself a @Configuration class we actually don't
        // need to override this method.
        return application;
    }
}
