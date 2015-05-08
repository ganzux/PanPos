package com.ganzux.urjc.ssdd.postealo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

/**
 * Main class
 * @author ganzux
 */
@ComponentScan
@EnableAutoConfiguration
public class Postealo {

	/**
	 * Main method to run the App
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(Postealo.class, args);
    }

    /**
     * Method to override basic error pages
     * @return containerCustomizer
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
     
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                ErrorPage error404Page = 
                		new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.htm");
                ErrorPage error405Page = 
                		new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405.htm");
     
                container.addErrorPages(error404Page, error405Page);
            }
        };
    }
}
