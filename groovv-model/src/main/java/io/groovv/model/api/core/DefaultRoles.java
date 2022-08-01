package io.groovv.model.api.core;

import lombok.val;

public enum DefaultRoles {


  /**
   * anonymous implies nothing and may not be used
   */
  Anonymous("ROLE_ANONYMOUS"),

  /**
   * user implies anonymous
   */
  User("ROLE_USER", Anonymous),
  /**
   * administrator implies root
   */
  Administrator("ROLE_ADMINISTRATOR", User),

  /**
   * Root implies administrator
    */
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

}
