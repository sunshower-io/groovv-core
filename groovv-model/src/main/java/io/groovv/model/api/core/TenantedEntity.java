package io.groovv.model.api.core;

import io.sunshower.persistence.id.Identifier;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class TenantedEntity extends AbstractEntity<Identifier> {

  @Setter
  @Getter(onMethod = @__({@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL), @JoinColumn(name = "tenant_id")}))
  private Tenant tenant;

  protected TenantedEntity(Identifier identifier) {
    super(identifier);
  }
}
