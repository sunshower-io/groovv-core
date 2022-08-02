package io.groovv.model.api.core;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class PropertyEncryptedEntity<
        ID extends Serializable,
        P extends PropertyEncryptedEntity<ID, P, E>,
        E extends EncryptedProperty<ID, P, E>>
    extends AbstractEntity<ID> {

  @Setter
  @Getter(
      onMethod =
          @__({@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)}))
  private Set<E> encryptedProperties;

  protected PropertyEncryptedEntity(ID id) {
    super(id);
  }
}
