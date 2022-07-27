package io.groovv.model.api.core;

import java.io.Serializable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessControlledEntity<ID extends Serializable> extends AbstractEntity<ID> {

  /** the ACL identity for this entity */
  @Getter(
      onMethod =
          @__({
            @OneToOne(fetch = FetchType.LAZY),
            @JoinColumn(name = "id", insertable = false, updatable = false)
          }))
  private ObjectIdentity identity;

  public AccessControlledEntity(ID id) {
    super(id);
  }
}
