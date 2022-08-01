package io.groovv.service.admin;

import io.groovv.dto.views.model.core.PrincipalView;
import io.groovv.model.api.core.DefaultRoles;
import io.groovv.model.api.core.Role;
import io.sunshower.persistence.id.Identifier;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class DatabaseAuthenticationService implements AuthenticationService {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public PrincipalView authenticate(Object principal) throws AuthenticationException {
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isDefaultRoleTablePopulated() {
    val roles = DefaultRoles.getRoles();
    val roleNames = roles.stream().map(DefaultRoles::getAuthority).collect(Collectors.toList());
    val count =
        entityManager
            .createQuery("select count(r) from Role r where r.name in (:names)", Long.class)
            .setParameter("names", roleNames)
            .getSingleResult();
    return ((long) roles.size()) == count;
  }

  @Override
  @Transactional
  public boolean populateDefaultRoleTable() {

    try {
      if (isDefaultRoleTablePopulated()) {
        return true;
      }
      log.info("Populating default role-table....");

      entityManager.persist(DefaultRoles.toHierarchy());
      entityManager.flush();
      log.info("Successfully populated default role-table");
      return true;
    } catch (Exception ex) {
      log.warn("Failed to populate default role-table.  Reason: {}", ex.getMessage());
      // unknown if this could occur
      return false;
    }
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
  public void addRoles(Identifier userId, String... roles) {}
}
