package io.groovv.model.api.core;

import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/** a realm a source of user information such as OAuth, databases, or filesystems */
@Entity
@Table(name = "REALMS")
public class Realm extends AbstractEntity<Identifier> {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "name")}))
  private String name;

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "provider")}))
  @Convert(converter = ClassConverter.class)
  private Class<?> provider;

  @Setter
  @Getter(
      onMethod =
          @__({
            @OneToMany(mappedBy = "realm", cascade = CascadeType.ALL, orphanRemoval = true),
          }))
  private Set<User> users;

  public Realm() {
    // don't retrieve users from this association--they won't appear in a deterministic order
    this(null);
  }

  public Realm(String name) {
    super(SEQUENCE.next());
    setName(name);
    users = new HashSet<>();
  }

  public void addUser(@NonNull User user) {
    user.setRealm(this);
    users.add(user);
  }
}
