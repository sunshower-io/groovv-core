package io.groovv.persist.users;

import io.groovv.model.api.core.Persistable;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

public interface Repository<ID extends Serializable, T extends Persistable<ID>> extends
    CrudRepository<T, ID> {

}
