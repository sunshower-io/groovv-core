package io.groovv.model.api.core;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class TenantedPropertyEncryptedEntity<
    ID extends Serializable,
    P extends TenantedPropertyEncryptedEntity<ID, P, E>,
    E extends EncryptedProperty<ID, P, E>
    > extends PropertyEncryptedEntity<ID, P, E> implements TenantAware {

  @Setter
  @Getter(
      onMethod =
      @__({
          @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL),
          @JoinColumn(name = "tenant_id")
      }))
  private Tenant tenant;

  protected TenantedPropertyEncryptedEntity(ID id) {
    super(id);
  }
}
