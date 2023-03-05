package com.lupo.suggestapi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({@PropertySource("classpath:/config/default.properties")})
@ComponentScan(basePackages = "com.lupo.suggestapi")
@EnableCaching
public class SuggestApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SuggestApiApplication.class,
                              args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(SuggestApiApplication.class);
    }


}
