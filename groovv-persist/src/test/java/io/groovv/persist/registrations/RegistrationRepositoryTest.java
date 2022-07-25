package io.groovv.persist.registrations;

import static io.sunshower.model.test.RegistrationTests.createValidRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.groovv.persist.PersistenceTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@PersistenceTest
public class RegistrationRepositoryTest {

  @Inject private RegistrationRepository repository;

  @Test
  void ensureSavingValidRegistrationWorks() {
    repository.save(createValidRequest());
    assertEquals(1L, repository.count());
  }
}
