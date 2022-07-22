package io.groovv.persist.users;

import io.groovv.model.api.accounts.Account;
import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.val;

/**
 * back by collections for now.  Will integrate JPA for MVP
 */
public class InMemoryAccountRepository<Q> extends
    AbstractInMemoryRepository<Identifier, Account, Q> implements AccountRepository<Q> {


  public InMemoryAccountRepository() {
    super(Account.class, Identifier.class, Queriers.noOp(),
        Identifiers.newSequence());
  }

  @Override
  protected <S extends Account> void setId(S entity, Identifier identifier) {
    entity.setId(identifier);
  }

  @Override
  protected <S extends Account> Identifier getId(S entity) {
    return entity.getId();
  }
}
