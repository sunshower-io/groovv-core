package io.groovv.model.api.ext;

import io.groovv.model.api.converters.LocaleConverter;
import io.groovv.model.api.core.AbstractEntity;
import io.groovv.model.api.core.AbstractSerializableEntity;
import io.groovv.model.api.core.User;
import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_PROFILES")
public class UserProfile extends AbstractSerializableEntity<Identifier> {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  @Setter
  @Getter(
      onMethod =
          @__({
            @OneToOne(cascade = CascadeType.ALL),
            @JoinColumn(name = "user_id", referencedColumnName = "id")
          }))
  private User user;

  @Getter(
      onMethod =
          @__({
            @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner"),
          }))
  private ProfileImage image;

  @Setter
  @Getter(
      onMethod =
          @__({@Basic, @Column(name = "locale"), @Convert(converter = LocaleConverter.class)}))
  private Locale locale;

  public UserProfile() {
    super(SEQUENCE.next());
  }

  public void setImage(ProfileImage image) {
    if (image != null) {
      this.image = image;
      image.setOwner(this);
    } else {
      this.image = null;
    }
  }
}
