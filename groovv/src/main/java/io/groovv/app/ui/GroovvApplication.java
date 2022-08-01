package io.groovv.app.ui;

import com.vaadin.flow.spring.SpringBootAutoConfiguration;
import io.groovv.app.ui.config.AdministrationConfiguration;
import io.groovv.app.ui.config.SecurityConfiguration;
import io.groovv.persist.config.PersistenceConfiguration;
import io.groovv.service.config.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = SpringBootAutoConfiguration.class)
@Import({
  SecurityConfiguration.class,
  PersistenceConfiguration.class,
  ServiceConfiguration.class,
  AdministrationConfiguration.class
})
public class GroovvApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(GroovvApplication.class, args);
  }
}
