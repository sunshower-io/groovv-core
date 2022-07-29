package io.groovv.model.api.ext;

import io.groovv.model.api.core.AbstractEntity;
import io.groovv.model.api.core.User;
import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends AbstractEntity<Identifier> {

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

  @Setter
  @Getter(
      onMethod =
          @__({
            @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner"),
          }))
  private ProfileImage image;

  public UserProfile() {
    super(SEQUENCE.next());
  }
}
