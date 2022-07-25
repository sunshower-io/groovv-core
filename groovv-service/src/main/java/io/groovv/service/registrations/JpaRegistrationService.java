package io.groovv.service.registrations;

import io.groovv.model.api.registrations.RegistrationRequest;
import io.sunshower.persistence.id.Identifier;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class JpaRegistrationService implements RegistrationService {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<RegistrationRequest> list() {
    return null;
  }

  @Override
  public Identifier add(RegistrationRequest request) {
    return null;
  }
}
