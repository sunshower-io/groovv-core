package io.groovv.app.ui;

import io.groovv.app.ui.config.PersistenceConfiguration;
import io.groovv.app.ui.config.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfiguration.class, PersistenceConfiguration.class})
public class GroovvApplication {

  public static void main(String[] args) {
    SpringApplication.run(GroovvApplication.class, args);
  }
}
