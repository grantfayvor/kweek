package com.kweek.config;

/**
 * Created by Harrison on 2017-02-05.
 */

/*@Configuration
@ImportResource
@EnableWebMvc
public class ResourceConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("static*//**//*");
        resolver.setSuffix(".html");
        return resolver;
    }

    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources*//**").addResourceLocations("classpath:/resources*//**//*");
        registry.addResourceHandler("/static*//**").addResourceLocations("classpath:/static*//**//*");
    }

}*/

public class ResourceConfig {
}
