package io.groovv.service.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.groovv.service.test.ServiceTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@ServiceTest
class DatabaseAuthenticationServiceTest {

  @Inject private AuthenticationService authenticationService;

  @Test
  void ensureRoleTableIsNotPopulatedInitially() {
    assertFalse(authenticationService.isDefaultRoleTablePopulated());
  }

  @Test
  void ensureBuildingDefaultRoleTableWorks() {
    assertTrue(authenticationService.populateDefaultRoleTable());
    assertTrue(authenticationService.isDefaultRoleTablePopulated());
  }
}
