package io.groovv.model.api.accounts;

import io.groovv.model.api.core.AbstractEntity;
import io.groovv.model.api.core.User;
import io.sunshower.persistence.id.Identifier;
import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account extends AbstractEntity<Identifier> {



  @ManyToOne
  @PrimaryKeyJoinColumn
  private User owner;

  @Basic
  private String name;

  @Embedded
  private AccountDetails details;


}
