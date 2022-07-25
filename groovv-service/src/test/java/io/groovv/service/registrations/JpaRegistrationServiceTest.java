package io.groovv.service.registrations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.groovv.model.api.registrations.RegistrationRequest.Status;
import io.groovv.persist.registrations.RegistrationRepository;
import io.groovv.service.test.ServiceTest;
import io.sunshower.model.test.RegistrationTests;
import javax.inject.Inject;
import lombok.val;
import org.junit.jupiter.api.Test;

@ServiceTest
public class JpaRegistrationServiceTest {

  @Inject private RegistrationService registrationService;

  @Inject private RegistrationRepository repository;

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
  }
}
