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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.val;

/**
 * back by collections for now.  Will integrate JPA for MVP
 */
public class InMemoryAccountRepository implements AccountRepository {


  private final Map<Identifier, Account> accounts;


  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  public InMemoryAccountRepository() {
    accounts = new LinkedHashMap<>();
  }

  @Override
  public <S extends Account> S save(S entity) {
    var id = entity.getId();
    if (id == null) {
      id = SEQUENCE.next();
      entity.setId(id);
    }
    accounts.put(id, entity);
    return entity;
  }

  @Override
  public <S extends Account> Iterable<S> saveAll(@NonNull Iterable<S> entities) {
    return StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(
            entities.iterator(),
            Spliterator.ORDERED
        ), false
    ).map(this::save).toList();
  }

  @Override
  public Optional<Account> findById(@NonNull Identifier identifier) {
    return Optional.ofNullable(accounts.get(identifier));
  }

  @Override
  public boolean existsById(@NonNull Identifier identifier) {
    return accounts.get(identifier) != null;
  }

  @Override
  public Iterable<Account> findAll() {
    return accounts.values();
  }

  @Override
  public Iterable<Account> findAllById(@NonNull Iterable<Identifier> identifiers) {
    return StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(
            identifiers.iterator(),
            Spliterator.ORDERED
        ), false
    ).flatMap(t -> findById(t).stream()).toList();
  }

  @Override
  public long count() {
    return accounts.size();
  }

  @Override
  public void deleteById(Identifier identifier) {
    accounts.remove(identifier);
  }

  @Override
  public void delete(Account entity) {
    val id = entity.getId();
    if(id != null) {
      accounts.remove(id);
    }
  }

  @Override
  public void deleteAll(Iterable<? extends Account> entities) {
    entities.forEach(this::delete);
  }

  @Override
  public void deleteAll() {
    accounts.clear();
  }
}
