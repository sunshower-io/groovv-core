package io.sunshower.model.test;

import javax.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationTestConfiguration {

  @Bean
  public Validator validator() {
    return new LocalValidatorFactoryBean();
  }

}
