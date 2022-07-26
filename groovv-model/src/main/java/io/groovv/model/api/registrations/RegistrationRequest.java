package io.groovv.model.api.registrations;

import io.groovv.model.api.core.AbstractEntity;
import io.groovv.model.api.location.State;
import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = UtilityTables.REGISTRATION_REQUEST)
public class RegistrationRequest extends AbstractEntity<Identifier> {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  public RegistrationRequest() {
    super(SEQUENCE.next());
    setStatus(Status.Inactive);
  }

  @Setter
  @Email
  @NotNull
  @Getter(onMethod = @__({@Basic, @Column(name = "email_address")}))
  private String emailAddress;

  @Setter
  @NotNull
  @NotEmpty
  @Pattern(regexp = "(^$|\\d{10})")
  @Getter(onMethod = @__({@Basic, @Column(name = "phone_number")}))
  private String phoneNumber;

  @Setter
  @NotNull
  @NotEmpty
  @Pattern(regexp = "(^$|\\d{5})")
  @Getter(onMethod = @__({@Basic, @Column(name = "zip_code")}))
  private String zipCode;

  @Setter
  @NotNull
  @NotEmpty
  @Getter(onMethod = @__({@Basic, @Column(name = "first_name")}))
  private String firstName;

  @Setter
  @NotNull
  @NotEmpty
  @Getter(onMethod = @__({@Basic, @Column(name = "last_name")}))
  private String lastName;

  @Setter
  @NotNull
  @Getter(onMethod = @__({@Temporal(TemporalType.DATE), @Column(name = "date_of_birth")}))
  private Calendar dateOfBirth;

  @Setter
  @NotNull
  @Getter(onMethod = @__({@Enumerated(EnumType.ORDINAL), @Column(name = "state")}))
  private State state;

  @Setter
  @NotNull
  @Getter(onMethod = @__({@Enumerated(EnumType.ORDINAL), @Column(name = "realm")}))
  private Realm realm;

  @Setter
  @NotNull
  @Getter(onMethod = @__({@Enumerated(EnumType.ORDINAL), @Column(name = "status")}))
  private Status status;

  public enum Status {
    Active,
    Inactive
  }
}
