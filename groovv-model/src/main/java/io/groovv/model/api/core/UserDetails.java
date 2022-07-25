package io.groovv.model.api.core;

import io.sunshower.arcus.condensation.Alias;
import io.sunshower.arcus.condensation.Attribute;
import io.sunshower.arcus.condensation.RootElement;
import io.sunshower.persistence.id.Identifier;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@RootElement
@Table(name = "USER_DETAILS")
public class UserDetails extends SerializableAbstractEntity<Identifier> implements IconAware {

  @Setter
  @NotNull
  @NotEmpty
  @Getter(onMethod = @__({@Basic, @Column(name = "given_name")}))
  @Attribute(alias = @Alias(read = "given-name", write = "given-name"))
  private String givenName;

  @Setter
  @NotNull
  @NotEmpty
  @Getter(onMethod = @__({@Basic, @Column(name = "family_name")}))
  @Attribute(alias = @Alias(read = "family-name", write = "family-name"))
  private String familyName;

  @Setter
  @Getter(onMethod = @__({@Embedded}))
  private Icon icon;

  @Setter
  @Getter(
      onMethod =
      @__({
          @MapsId,
          @JoinColumn(name = "id"),
          @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY),
      }))
  private User user;

  public UserDetails() {
  }

  public UserDetails(@NonNull User user) {
    this.user = user;
    user.setDetails(this);
  }
}
