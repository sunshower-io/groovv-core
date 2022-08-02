package io.groovv.persist.users;

import io.groovv.model.api.core.User;
import io.sunshower.persistence.id.Identifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Identifier> {}
