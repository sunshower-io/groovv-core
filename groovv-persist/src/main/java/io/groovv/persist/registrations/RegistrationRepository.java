package io.groovv.persist.registrations;

import io.groovv.model.api.registrations.RegistrationRequest;
import io.sunshower.persistence.id.Identifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RegistrationRepository
    extends PagingAndSortingRepository<RegistrationRequest, Identifier> {}
