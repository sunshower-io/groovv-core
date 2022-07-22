package io.groovv.model.api.accounts;

import io.groovv.model.api.core.AbstractEntity;
import io.sunshower.persistence.id.Identifier;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account extends AbstractEntity<Identifier> {

  private String name;
  private String description;
  private String routingNumber;
  private String accountNumber;
  private String accountHolderName;

}
