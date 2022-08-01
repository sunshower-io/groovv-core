package io.groovv.model.api.core;

import java.util.ArrayDeque;
import java.util.EnumSet;
import java.util.Set;
import lombok.val;

public enum DefaultRoles {

  /** anonymous implies nothing and may not be used */
  Anonymous("ROLE_ANONYMOUS"),

  /** user implies anonymous */
  User("ROLE_USER", Anonymous),
  /** administrator implies root */
  Administrator("ROLE_ADMINISTRATOR", User),

  /** Root implies administrator */
  Root("ROLE_ROOT", Administrator);

  final String name;
  final DefaultRoles[] implied;

  DefaultRoles(String name, DefaultRoles... implied) {
    this.name = name;
    this.implied = implied;
  }

  public boolean implies(DefaultRoles role) {
    if (role == null) {
      return false;
    }

    if (role == this) {
      return true;
    }

    for (val r : implied) {
      if (r.implies(role)) {
        return true;
      }
    }
    return false;
  }

  public String getAuthority() {
    return name;
  }

  public static Set<DefaultRoles> getRoles() {
    return EnumSet.allOf(DefaultRoles.class);
  }

  public static Role toHierarchy() {
    val stack = new ArrayDeque<Role>();
    val root = new Role(DefaultRoles.Root.name);
    val defaultRoles = new ArrayDeque<DefaultRoles>();

    stack.add(root);
    defaultRoles.add(DefaultRoles.Root);

    while (!stack.isEmpty()) {
      val current = stack.pop();
      val cdef = defaultRoles.pop();
      for (val implied : cdef.implied) {
        val child = new Role(implied.name);
        current.addImplied(child);
        stack.add(child);
        defaultRoles.add(implied);
      }
    }
    return root;
  }
}
