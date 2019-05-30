package org.papaja.adminify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings({"unused"})
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan({"org.papaja.application.adminify"})
public class SpringSessionConfig {

    private Environment environment;

    @Autowired
    public SpringSessionConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public CookieSerializer getCookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();

        serializer.setCookieName(environment.getProperty("session.cookie.name"));
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");

        return serializer;
    }

}
