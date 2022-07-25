package io.groovv.model.api.registrations;

import io.groovv.model.api.core.AbstractEntity;
import io.sunshower.persistence.id.Identifier;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = UtilityTables.REGISTRATION_REQUEST)
public class RegistrationRequest extends AbstractEntity<Identifier> {

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "email_address")}))
  private String emailAddress;


  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "first_name")}))
  private String firstName;


  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "last_name")}))
  private String lastName;


  @Setter
  @Getter(onMethod = @__({@Temporal(TemporalType.DATE), @Column(name = "date_of_birth")}))
  private Calendar dateOfBirth;

}
