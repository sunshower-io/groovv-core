package io.groovv.model.api.core;

import io.groovv.model.api.converters.IdentifierConverter;
import io.sunshower.arcus.condensation.Attribute;
import io.sunshower.arcus.condensation.Convert;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@SuppressWarnings("PMD")
public class AbstractEntity<ID extends Serializable> implements Persistable<ID> {

  @Setter
  @Attribute
  @Convert(IdentifierConverter.class)
  @Getter(onMethod = @__({@Id, @Column(name = "id")}))
  private ID id;

  protected AbstractEntity(ID id) {
    this.id = id;
  }

  @Override
  public Persistable<ID> clone() {
    return new AbstractEntity<>(id);
  }

  @Override
  public int hashCode() {
    return isNew() ? 0 : Objects.hashCode(getId());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }

    if (getClass().isAssignableFrom(o.getClass())) {
      return ((AbstractEntity<ID>) o).getId().equals(id);
    }
    return false;
  }
}
