package io.groovv.model.api.registrations;

import io.groovv.model.api.core.AbstractEntity;
import io.groovv.model.api.location.State;
import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
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

  @Setter
  @Getter(onMethod = @__({@Email, @NotNull, @Basic, @Column(name = "email_address")}))
  private String emailAddress;

  @Setter
  @Getter(
      onMethod =
          @__({
            @NotNull,
            @NotEmpty,
            @Pattern(regexp = "(^$|\\d{10})"),
            @Basic,
            @Column(name = "phone_number")
          }))
  private String phoneNumber;

  @Setter
  @Getter(
      onMethod =
          @__({
            @Basic,
            @NotNull,
            @NotEmpty,
            @Column(name = "zip_code"),
            @Pattern(regexp = "(^$|\\d{5})")
          }))
  private String zipCode;

  @Setter
  @Getter(onMethod = @__({@Basic, @NotNull, @NotEmpty, @Column(name = "first_name")}))
  private String firstName;

  @Setter
  @Getter(onMethod = @__({@Basic, @NotNull, @NotEmpty, @Column(name = "last_name")}))
  private String lastName;

  @Setter
  @Getter(onMethod = @__({@NotNull, @Column(name = "date_of_birth")}))
  private LocalDate dateOfBirth;

  @Setter
  @Getter(onMethod = @__({@NotNull, @Column(name = "state"), @Enumerated(EnumType.ORDINAL)}))
  private State state;

  @Setter
  @Getter(onMethod = @__({@NotNull, @Column(name = "realm"), @Enumerated(EnumType.ORDINAL)}))
  private Realm realm;

  @Setter
  @Getter(
      onMethod =
          @__({
            @NotNull,
            @Column(name = "status"),
            @Enumerated(EnumType.ORDINAL),
          }))
  private Status status;

  public enum Status {
    Active,
    Inactive
  }

  public RegistrationRequest() {
    super(SEQUENCE.next());
    setStatus(Status.Inactive);
    setRealm(Realm.Groovv);
  }
}
