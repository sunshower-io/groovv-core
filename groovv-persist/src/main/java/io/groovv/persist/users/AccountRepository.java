package io.groovv.persist.users;

import io.groovv.model.api.accounts.Account;
import io.sunshower.persistence.id.Identifier;

public interface AccountRepository<Q> extends Repository<Identifier, Account, Q> {

}