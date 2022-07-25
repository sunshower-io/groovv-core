package io.groovv.app.ui.data.providers;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import io.groovv.model.api.core.Persistable;
import io.groovv.persist.users.Repository;
import java.io.Serializable;
import java.util.stream.Stream;
import lombok.NonNull;

public class RepositoryDataProvider<ID extends Serializable, T extends Persistable<ID>, Q> extends
    AbstractBackEndDataProvider<T, Q> {

  private final Repository<ID, T, Q> repository;

  public RepositoryDataProvider(@NonNull final Repository<ID, T, Q> repository) {
    this.repository = repository;
  }

  @Override
  protected Stream<T> fetchFromBackEnd(Query<T, Q> query) {
    return query.getFilter()
        .map(filter -> repository.query(filter, query.getOffset(), query.getLimit()))
        .orElse(repository.queryAll(query.getOffset(), query.getLimit()));
  }

  @Override
  protected int sizeInBackEnd(Query<T, Q> query) {
    return (int) repository.count();
  }
}
