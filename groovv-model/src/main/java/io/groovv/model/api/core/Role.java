package io.groovv.model.api.core;

import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ROLES")
public class Role extends AbstractSerializableEntity<Identifier> implements GrantedAuthority {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "name")}))
  private String name;

  @Setter
  @Getter(
      onMethod =
          @__({
            @ManyToOne(cascade = CascadeType.ALL),
            @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "parent_id")
          }))
  private Role parent;

  @Setter
  @Getter(
      onMethod =
          @__({
            @ManyToMany,
            @JoinTable(
                name = "ROLES_TO_USERS",
                joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
          }))
  private Set<User> users;

  @Setter
  @Getter(
      onMethod =
          @__({
            @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true),
          }))
  private Set<Role> implied;

  public Role(@NonNull String name) {
    this();
    setName(name);
  }

  public Role() {
    super(SEQUENCE.next());
  }

  @Override
  @Transient
  public String getAuthority() {
    return name;
  }

  public void setAuthority(String authority) {
    this.name = authority;
  }

  public boolean addImplied(@NonNull Role implication) {
    if (implied == null) {
      implied = new HashSet<>();
    }
    return implied.add(implication);
  }

  public boolean removeImplied(@NonNull Role implication) {
    if (implied == null) {
      implied = new HashSet<>();
    }
    return implied.add(implication);
  }

  /**
   * danger--this will generate an N+1 select over the hierarchy unless the hierarchy is cached
   *
   * @param implication the role to check for implication
   * @return true if the role is implied, false other
   */
  public boolean implies(@Nullable Role implication) {
    if (implication == null) {
      return false;
    }

    if (Objects.equals(name, implication.getName())) {
      return true;
    }

    for (val impl : implied) {
      if (impl.implies(implication)) {
        return true;
      }
    }
    return false;
  }
}
