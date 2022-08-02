package io.groovv.model.api.core;

import io.sunshower.persistence.id.Identifier;
import java.io.Serializable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SerializableTenantedEntity extends TenantedEntity implements Serializable {

  protected SerializableTenantedEntity(Identifier identifier) {
    super(identifier);
  }
}
