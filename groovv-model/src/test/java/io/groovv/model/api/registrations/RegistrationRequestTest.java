package io.groovv.model.api.registrations;

import static io.sunshower.model.test.RegistrationTests.createValidRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.sunshower.model.test.ModelTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ModelTest
class RegistrationRequestTest {

  @PersistenceContext private EntityManager entityManager;

  private RegistrationRequest request;

  @BeforeEach
  void setUp() {
    request = createValidRequest();
  }

  @Test
  void ensureSavingValidRequestWorks() {
    entityManager.persist(request);
    entityManager.flush();
  }

  @Test
  void ensureSavingRequestWithNullEmailFails() {
    request.setEmailAddress(null);
    assertThrows(
        ConstraintViolationException.class,
        () -> {
          entityManager.persist(request);
          entityManager.flush();
        });
  }
}
