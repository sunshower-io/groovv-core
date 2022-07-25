package io.groovv.persist.registrations;

import io.groovv.model.api.registrations.Realm;
import io.groovv.model.api.registrations.RegistrationRequest;
import io.sunshower.persistence.id.Identifier;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RegistrationRepository
    extends PagingAndSortingRepository<RegistrationRequest, Identifier> {

  @Query("SELECT r from RegistrationRequest r where r.realm = :realm")
  List<RegistrationRequest> findByRealm(@Param("realm") Realm realm);
}
