package io.groovv.model.api.core;

import static org.junit.jupiter.api.Assertions.*;

import lombok.val;
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

  @Test
  void ensureRoleHierarchyIsPopulated() {
    val hierarchy = DefaultRoles.toHierarchy();
    assertEquals(1, hierarchy.getImplied().size());
    assertEquals("ROLE_ADMINISTRATOR", hierarchy.getImplied().iterator().next().getName());
  }
}
