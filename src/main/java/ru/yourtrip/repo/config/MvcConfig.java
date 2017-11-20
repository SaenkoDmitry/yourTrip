package ru.yourtrip.repo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/auth/sign-up").setViewName("login");
        registry.addViewController("/blog/creatingRoute").setViewName("createroute");
        registry.addViewController("/search/routes").setViewName("routesfinder");
        registry.addViewController("/").setViewName("index");
    }
}