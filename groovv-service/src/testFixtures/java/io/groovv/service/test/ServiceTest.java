package io.groovv.service.test;

import io.groovv.persist.config.PersistenceConfiguration;
import io.groovv.persist.config.PersistenceTest;
import io.groovv.persist.config.TestPersistenceConfiguration;
import io.groovv.service.config.ServiceConfiguration;
import io.sunshower.arcus.persist.flyway.FlywayTestConfiguration;
import io.sunshower.model.test.ModelTest;
import io.sunshower.model.test.ValidationTestConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.transaction.Transactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Rollback
@Transactional
@ModelTest
@PersistenceTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
      FlywayTestConfiguration.class,
      ValidationTestConfiguration.class,
      ServiceConfiguration.class,
      PersistenceConfiguration.class,
      TestPersistenceConfiguration.class
    })
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceTest {}
