package io.groovv.persist.config;

import io.github.glytching.junit.extension.random.RandomBeansExtension;
import io.sunshower.arcus.persist.flyway.FlywayTestConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.transaction.Transactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Rollback
@Transactional
@ExtendWith({SpringExtension.class, RandomBeansExtension.class})
@ContextConfiguration(
    classes = {
      FlywayTestConfiguration.class,
      PersistenceConfiguration.class,
      TestPersistenceConfiguration.class
    })
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistenceTest {}
