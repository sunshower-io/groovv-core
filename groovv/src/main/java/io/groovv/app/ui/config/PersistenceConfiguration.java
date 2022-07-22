package io.groovv.app.ui.config;


import io.groovv.persist.users.AccountRepository;
import io.groovv.persist.users.InMemoryAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

  @Bean
  public AccountRepository accountRepository() {
    return new InMemoryAccountRepository();
  }

}
