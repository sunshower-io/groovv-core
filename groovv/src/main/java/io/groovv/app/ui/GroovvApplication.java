package io.groovv.app.ui;

import io.groovv.app.ui.config.SecurityConfiguration;
import io.groovv.persist.config.PersistenceConfiguration;
import io.groovv.service.config.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfiguration.class, PersistenceConfiguration.class, ServiceConfiguration.class})
public class GroovvApplication {

  public static void main(String[] args) {
    SpringApplication.run(GroovvApplication.class, args);
  }
}
