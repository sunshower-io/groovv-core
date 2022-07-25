package io.groovv.persist.core;

import io.groovv.model.api.core.Persistable;
import io.sunshower.persistence.id.Sequence;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.val;

public abstract class AbstractInMemoryRepository<ID extends Serializable, T extends Persistable<ID>, Q> extends
    AbstractRepository<ID, T, Q> {

  private final Map<ID, T> store;
  private final Sequence<ID> sequence;

  protected AbstractInMemoryRepository(@NonNull Class<T> type, @NonNull Class<ID> idType,
      @NonNull Querier<ID, T, Q> querier, @NonNull Sequence<ID> sequence) {
    super(type, idType, querier);
    this.sequence = sequence;
    this.store = new LinkedHashMap<>();
  }

  @Override
  public Stream<ID> ids() {
    return store.keySet().stream();
  }

  @Override
  public Stream<ID> ids(int start, int limit) {
    return ids();
  }

  @Override
  public Stream<T> queryAll(int offset, int limit) {
    return store.values().stream();
  }

  @Override
  public Stream<ID> queryIds(Q query, int start, int limit) {
    return ids();
  }

  @Override
  public Stream<T> query(Q query, int start, int limit) {
    return store.values().stream();
  }

  @Override
  public <S extends T> S save(@NonNull S entity) {
    var id = getId(entity);
    if (id == null) {
      id = sequence.next();
      setId(entity, id);
    }
    store.put(id, entity);
    return entity;
  }


  @Override
  public <S extends T> Iterable<S> saveAll(@NonNull Iterable<S> entities) {
    return StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(
            entities.iterator(),
            Spliterator.ORDERED
        ), false
    ).map(this::save).toList();
  }

  @Override
  public Optional<T> findById(@NonNull ID id) {
    return Optional.of(store.get(id));
  }

  @Override
  public boolean existsById(@NonNull ID id) {
    return store.containsKey(id);
  }

  @Override
  public Iterable<T> findAll() {
    return store.values();
  }

  @Override
  public Iterable<T> findAllById(@NonNull Iterable<ID> ids) {
    return StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(
            ids.iterator(),
            Spliterator.ORDERED
        ), false
    ).flatMap(t -> findById(t).stream()).toList();
  }

  @Override
  public long count() {
    return store.size();
  }

  @Override
  public void deleteById(@NonNull ID id) {
    store.remove(id);
  }

  @Override
  public void delete(@NonNull T entity) {
    val id = entity.getId();
    if (id != null) {
      deleteById(id);
    }
  }

  @Override
  public void deleteAll(@NonNull Iterable<? extends T> entities) {
    entities.forEach(this::delete);
  }

  @Override
  public void deleteAll() {
    store.clear();
  }

  protected abstract <S extends T> void setId(S entity, ID id);


  protected abstract <S extends T> ID getId(S entity);
}
