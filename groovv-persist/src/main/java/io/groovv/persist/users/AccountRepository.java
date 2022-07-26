package io.groovv.persist.users;

import io.groovv.model.api.accounts.Account;
import io.sunshower.persistence.id.Identifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends PagingAndSortingRepository<Account, Identifier> {}
