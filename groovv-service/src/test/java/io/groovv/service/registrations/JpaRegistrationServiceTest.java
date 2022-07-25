package io.groovv.service.registrations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.groovv.model.api.core.User;
import io.groovv.model.api.registrations.RegistrationRequest.Status;
import io.groovv.persist.registrations.RegistrationRepository;
import io.groovv.service.test.ServiceTest;
import io.sunshower.model.test.RegistrationTests;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.junit.jupiter.api.Test;

@ServiceTest
public class JpaRegistrationServiceTest {

  @Inject
  private RegistrationService registrationService;

  @Inject
  private RegistrationRepository repository;

  @PersistenceContext
  private EntityManager entityManager;

  @Test
  void ensureSavingRepositoryWorks() {
    val request = RegistrationTests.createValidRequest();
    repository.save(request);

    assertEquals(1L, registrationService.getRequestsByStatus(Status.Inactive).size());
  }

  @Test
  void ensureActivatingRegistrationWorks() {
    val request = repository.save(RegistrationTests.createValidRequest());
    val user = registrationService.activate(request.getId());
    assertNotNull(user);
    assertEquals(1, registrationService.getRequestsByStatus(Status.Active).size());
    val ulist = entityManager.createQuery(
            "select u from User u where u.username = :username and u.locked = false", User.class)
        .setParameter("username", request.getEmailAddress()).getResultList();
    assertEquals(List.of(user), ulist);
  }
}
