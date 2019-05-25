package com.tordot.tour.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer
//                .defaultContentType(MediaType.TEXT_HTML)
//                .ignoreAcceptHeader(true);
//    }
//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/acc/**").addResourceLocations("/acc/");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//
//    }

//    @Bean
//    public ViewResolver viewResolverHtml() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/accueil/");
//        viewResolver.setSuffix("");
//        viewResolver.setOrder(2);
//        return viewResolver;
//    }

//    @Bean
//    public JasperReportsViewResolver getJasperReportsViewResolver() {
//        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
//        resolver.setPrefix("classpath:/reports/jasper/");
//        resolver.setSuffix(".jasper");
//        resolver.setReportDataKey("datasource");
//        resolver.setViewNames("rpt_*");
//        resolver.setViewClass(JasperReportsMultiFormatView.class);
//        resolver.setOrder(0);
//        return resolver;
//    }

    /*  i18n */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.FRENCH);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
