package io.groovv.service.config;

import io.groovv.persist.config.PersistenceConfiguration;
import io.groovv.service.registrations.RegistrationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan(basePackageClasses = RegistrationService.class)
public class ServiceConfiguration {

}
