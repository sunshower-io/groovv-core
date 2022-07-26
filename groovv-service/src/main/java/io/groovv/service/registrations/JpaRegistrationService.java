package io.groovv.service.registrations;

import io.groovv.model.api.core.Realm;
import io.groovv.model.api.core.User;
import io.groovv.model.api.registrations.RegistrationRequest;
import io.groovv.model.api.registrations.RegistrationRequest.Status;
import io.groovv.persist.registrations.RegistrationRepository;
import io.sunshower.persistence.id.Identifier;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class JpaRegistrationService implements RegistrationService {

  @PersistenceContext
  private EntityManager entityManager;
  private final RegistrationRepository repository;

  @Inject
  public JpaRegistrationService(final RegistrationRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<RegistrationRequest> list() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Identifier add(RegistrationRequest request) {
    return repository.save(request).getId();
  }

  @Override
  public List<RegistrationRequest> getRequestsByStatus(Status status) {
    return entityManager
        .createQuery(
            "select r from RegistrationRequest r where r.status = :status",
            RegistrationRequest.class)
        .setParameter("status", status)
        .getResultList();
  }

  @Override
  public User activate(Identifier id) {
    val opt = repository.findById(id);
    if (opt.isEmpty()) {
      throw new EntityNotFoundException(
          "Error: User registration identified by '%s' was not found".formatted(id));
    }
    val req = opt.get();
    req.setStatus(Status.Active);

    val realm = findOrCreateRealmEntity(req.getRealm());
    val user = requestToUser(req);
    realm.addUser(user);
    entityManager.persist(user);
    return user;
  }

  private User requestToUser(RegistrationRequest request) {
    val user = new User();
    val details = user.getDetails();

    user.setUsername(request.getEmailAddress());
    details.setFamilyName(request.getLastName());
    details.setGivenName(request.getFirstName());

    user.setLocked(false);
    user.setExpired(false);
    return user;
  }

  private Realm findOrCreateRealmEntity(io.groovv.model.api.registrations.Realm realm) {

    val realms =
        entityManager
            .createQuery("select r from Realm r where r.name = :name", Realm.class)
            .setParameter("name", realm.getKey())
            .getResultList();

    if (realms.isEmpty()) {
      val r = new Realm();
      r.setName(realm.getKey());
      entityManager.persist(r);
      return r;
    }

    if (realms.size() > 1) {
      throw new DataIntegrityViolationException(
          "Error: there should be no more than a single realm with the given key (%s)"
              .formatted(realm));
    }

    return realms.get(0);
  }
}
