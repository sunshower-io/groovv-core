package io.groovv.model.api.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DefaultRolesTest {

  @Test
  void ensureRootIsImpliedByAdmin() {
    assertTrue(DefaultRoles.Root.implies(DefaultRoles.Administrator));
  }

  @Test
  void ensureRootImpliesAnonymous() {
    assertTrue(DefaultRoles.Root.implies(DefaultRoles.Anonymous));
  }

}