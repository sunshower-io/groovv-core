package io.groovv.persist.core;

import io.groovv.model.api.core.Persistable;
import java.io.Serializable;
import lombok.NonNull;

public abstract class AbstractRepository<ID extends Serializable, T extends Persistable<ID>, Q> implements
    Repository<ID, T, Q> {

  protected final Class<T> type;
  protected final Class<ID> idType;

  protected final Querier<ID, T, Q> querier;


  public AbstractRepository(@NonNull Class<T> type, @NonNull Class<ID> idType,
      @NonNull Querier<ID, T, Q> querier) {
    this.type = type;
    this.idType = idType;
    this.querier = querier;
  }


  @Override
  public Class<T> getType() {
    return type;
  }

  @Override
  public Class<T> getIdType() {
    return type;
  }
}
