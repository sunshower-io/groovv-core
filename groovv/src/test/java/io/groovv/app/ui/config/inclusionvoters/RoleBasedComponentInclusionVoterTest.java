package io.groovv.app.ui.config.inclusionvoters;

import static org.junit.jupiter.api.Assertions.*;

import com.aire.ux.ComponentInclusionManager;
import io.groovv.app.ui.GroovvUITest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@GroovvUITest
class RoleBasedComponentInclusionVoterTest {

  @Inject
  private ComponentInclusionManager inclusionManager;

  @Test
  void ensureInclusionManagerIsConfigured() {
    assertNotNull(inclusionManager);
  }

}