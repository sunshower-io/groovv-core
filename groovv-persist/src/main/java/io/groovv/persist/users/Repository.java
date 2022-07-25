package io.groovv.persist.users;

import io.groovv.model.api.core.Persistable;
import java.io.Serializable;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;

public interface Repository<ID extends Serializable, T extends Persistable<ID>, Q> extends
    CrudRepository<T, ID> {

  Class<T> getType();
  Class<T> getIdType();

  Stream<ID> ids();
  Stream<ID> ids(int start, int limit);

  Stream<ID> queryIds(Q query, int start, int limit);

  Stream<T> query(Q query, int start, int limit);


  Stream<T> queryAll(int offset, int limit);
}
