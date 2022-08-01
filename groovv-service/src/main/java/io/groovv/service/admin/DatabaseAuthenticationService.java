package io.groovv.service.admin;

import io.groovv.dto.views.model.core.PrincipalView;
import io.groovv.model.api.core.DefaultRoles;
import io.groovv.model.api.core.Role;
import io.sunshower.persistence.id.Identifier;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatabaseAuthenticationService implements AuthenticationService {

  @Override
  public PrincipalView authenticate(Object principal) throws AuthenticationException {
    return null;
  }

  @Override
  public boolean isDefaultRoleTablePopulated() {
    return false;
  }

  @Override
  public boolean populateDefaultRoleTable() {
    return false;
  }

  @Override
  public Optional<Role> findByName(String name) {
    return Optional.empty();
  }

  @Override
  public List<Role> lookup(DefaultRoles... roles) {
    return null;
  }

  @Override
  public void addRoles(Identifier userId, String... roles) {

  }
}
