package cz.microshop.cart.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartConfiguration {
    @Bean
    @ConditionalOnMissingBean(CartConfigurationProperties.class)
    public CartConfigurationProperties frameworkMesosConfigProperties() {
        return new CartConfigurationProperties();
    }
}