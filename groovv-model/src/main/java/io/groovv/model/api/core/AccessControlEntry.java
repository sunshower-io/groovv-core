package io.groovv.model.api.core;

import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = SecurityTables.ACCESS_CONTROL_ENTRY)
public class AccessControlEntry extends AbstractEntity<Identifier> {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "ace_order")}))
  private int order;

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "mask")}))
  private int mask;

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "granting")}))
  private boolean granting;

  @Setter
  @Getter(onMethod = @__({@OneToOne, @JoinColumn(name = "sid")}))
  private SecurityIdentity identity;

  @Setter
  @Getter(
      onMethod =
          @__({@OneToOne(fetch = FetchType.LAZY), @JoinColumn(name = "acl_object_identity")}))
  private SecuredObject instance;

  @Setter
  @Getter(onMethod = @__({@Embedded}))
  private AuditResult auditResult;

  public AccessControlEntry() {
    super(SEQUENCE.next());
  }
}
