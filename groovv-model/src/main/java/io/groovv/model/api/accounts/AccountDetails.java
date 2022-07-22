package io.groovv.model.api.accounts;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AccountDetails {


  @Basic
  @Column(name = "routing_number")
  private String routingNumber;

  @Column(name = "account_number")
  private String accountNumber;

}
