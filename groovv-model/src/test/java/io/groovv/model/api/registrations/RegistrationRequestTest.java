package io.groovv.model.api.registrations;


import static org.junit.jupiter.api.Assertions.assertThrows;

import io.groovv.model.api.location.State;
import io.sunshower.model.test.ModelTest;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ModelTest
class RegistrationRequestTest {

  @PersistenceContext
  private EntityManager entityManager;

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
    assertThrows(ConstraintViolationException.class, () -> {
      entityManager.persist(request);
      entityManager.flush();
    });
  }


  protected RegistrationRequest createValidRequest() {
    val request = new RegistrationRequest();
    request.setEmailAddress("user@mydomain.com");
    request.setFirstName("User");
    request.setLastName("OfGroovv");
    val calender = Calendar.getInstance();
    calender.set(1978, 6, 6);;
    request.setDateOfBirth(calender);
    request.setRealm(Realm.Google);
    request.setState(State.Colorado);
    return request;
  }

}