package io.groovv.persist.config;

import io.groovv.persist.registrations.RegistrationRepository;
import io.groovv.persist.users.AccountRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {RegistrationRepository.class, AccountRepository.class})
public class PersistenceConfiguration {}
