package io.groovv.service.admin;

import io.groovv.dto.views.model.core.PrincipalView;
import io.groovv.model.api.core.DefaultRoles;
import io.groovv.model.api.core.Role;
import io.sunshower.persistence.id.Identifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Stream;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {


  static Stream<RealmAuthenticationMapper> loadMappers() {
    return ServiceLoader.load(RealmAuthenticationMapper.class, Thread.currentThread()
        .getContextClassLoader()).stream().map(Provider::get);
  }


  /**
   * authenticate and synthesize a principal (if possible).  The procedure for this is as follows:
   * <p>
   * 1. If a principal's username appears in the administrators_to_users table, add the role
   *
   * @param principal
   * @return
   * @throws AuthenticationException
   */
  PrincipalView authenticate(Object principal) throws AuthenticationException;


  /**
   * determine if default role values are populated
   *
   * @return true if populated, false otherwise
   */
  boolean isDefaultRoleTablePopulated();

  /**
   *
   * @return true if population was successful or the role table is populated
   */
  boolean populateDefaultRoleTable();

  /**
   * @param name the name of the role to lookup
   * @return the role if present
   */
  Optional<Role> findByName(String name);


  /**
   * @param role the role to look for
   * @return the role if present
   */
  default Optional<Role> lookup(DefaultRoles role) {
    return findByName(role.name());
  }

  /**
   * @param roles the roles to lookup in the database
   * @return a set of roles (unflattened)
   */
  List<Role> lookup(DefaultRoles... roles);


  void addRoles(Identifier userId, String... roles);

  /**
   * @param userId the userId to add a role to
   * @param roles  the roles to add
   */
  default void addRoles(Identifier userId, DefaultRoles... roles) {
    addRoles(userId, Arrays.stream(roles).map(Enum::name).toArray(String[]::new));
  }


}
