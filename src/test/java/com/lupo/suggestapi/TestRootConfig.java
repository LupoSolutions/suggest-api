package com.lupo.suggestapi;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@EnableAutoConfiguration(exclude = {
        JmxAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.lupo.suggestapi"})
@PropertySources({@PropertySource("classpath:/config/default.properties")})
@EnableCaching
public class TestRootConfig {
}
