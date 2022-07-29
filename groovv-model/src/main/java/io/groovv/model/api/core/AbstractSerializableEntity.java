package io.groovv.model.api.core;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("PMD")
public class AbstractSerializableEntity<ID extends Serializable> extends AbstractEntity<ID>
    implements Serializable {

  protected AbstractSerializableEntity(ID id) {
    super(id);
  }
}
