package io.groovv.app.ui.config;


import io.groovv.persist.users.AccountRepository;
import io.groovv.persist.users.InMemoryAccountRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;

@Configuration
public class PersistenceConfiguration {

  @Bean
  public AuthenticationEventPublisher authenticationEventPublisher(
      ApplicationEventPublisher applicationEventPublisher) {
    return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
  }
  @Bean
  public AccountRepository accountRepository() {
    return new InMemoryAccountRepository();
  }

}
