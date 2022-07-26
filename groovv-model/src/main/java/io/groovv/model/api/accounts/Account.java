package io.groovv.model.api.accounts;

import io.groovv.model.api.core.AbstractEntity;
import io.groovv.model.api.core.User;
import io.sunshower.persistence.id.Identifier;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_BANK_ACCOUNTS")
public class Account extends AbstractEntity<Identifier> {

  @Basic @NotEmpty private String name;

  @Basic
  @NotEmpty
  @Column(name = "owner_name")
  private String ownerName;

  @NotNull @ManyToOne @PrimaryKeyJoinColumn private User owner;

  @NotNull @Embedded private AccountDetails details;

  public Account() {
    this.details = new AccountDetails();
  }
}
