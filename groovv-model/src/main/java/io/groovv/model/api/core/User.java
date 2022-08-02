package io.groovv.model.api.core;

import io.groovv.model.api.converters.DateConverter;
import io.sunshower.arcus.condensation.Alias;
import io.sunshower.arcus.condensation.Attribute;
import io.sunshower.arcus.condensation.Convert;
import io.sunshower.arcus.condensation.Element;
import io.sunshower.arcus.condensation.RootElement;
import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@RootElement
@Table(name = SecurityTables.USER)
@SuppressWarnings("PMD")
public class User extends PropertyEncryptedEntity<Identifier, User, EncryptedUserProperty>
    implements org.springframework.security.core.userdetails.UserDetails {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "locked")}))
  @Attribute
  private boolean locked;

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "expired")}))
  @Attribute
  private boolean expired;

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "created"), @Temporal(TemporalType.TIMESTAMP)}))
  @Attribute
  @Convert(DateConverter.class)
  private Date created;

  @Setter
  @Getter(
      onMethod =
      @__({@Basic, @Column(name = "last_authenticated"), @Temporal(TemporalType.TIMESTAMP)}))
  @Attribute(alias = @Alias(read = "last-authenticated", write = "last-authenticated"))
  @Convert(DateConverter.class)
  private Date lastAuthenticated;
  /**
   * username for this user
   */
  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = SecurityTables.User.USERNAME)}))
  @Attribute
  private String username;

  /**
   * password--always a salted hash
   */
  @Setter
  @Attribute
  @Getter(onMethod = @__({@Basic, @Column(name = SecurityTables.User.PASSWORD)}))
  private String password;

  /**
   * a role is a category of user that grants or prohibits access to system-functionality
   */
  @Setter
  @Getter(onMethod = @__({@ManyToMany(mappedBy = "users")}))
  private Set<Role> roles;

  @Setter
  @Getter(onMethod = @__({@ManyToMany(mappedBy = "users")}))
  private Set<Group> groups;

  /**
   * a permission is a granted authority that grants a specific user access to a specific object
   */
  @Setter
  @Getter(
      onMethod =
      @__({
          @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true),
      }))
  private Set<Permission> permissions;

  @Getter(
      onMethod =
      @__({
          @PrimaryKeyJoinColumn,
          @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
      }))
  @Element
  @NotNull
  private UserDetails details;

  @Setter
  @NotNull
  @Getter(
      onMethod =
      @__({
          @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL),
          @JoinTable(
              name = "REALM_TO_USERS",
              joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
              inverseJoinColumns = @JoinColumn(name = "realm_id", referencedColumnName = "id"),
              foreignKey = @ForeignKey(name = "users_to_realm_ref"),
              inverseForeignKey = @ForeignKey(name = "realm_to_users_realm_ref"))
      }))
  private Realm realm;

  public User() {
    super(SEQUENCE.next());
    details = new UserDetails();
    details.setUser(this);
  }

  public User(org.springframework.security.core.userdetails.UserDetails user) {
    this();
    setUsername(user.getUsername());
    setPassword(user.getPassword());
  }

  public void setDetails(UserDetails details) {
    if (details != null) {
      this.details = details;
      details.setUser(this);
    } else {
      this.details = null;
    }
  }

  @Transient
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  @Transient
  public boolean isAccountNonExpired() {
    return !expired;
  }

  @Override
  @Transient
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  @Transient
  public boolean isCredentialsNonExpired() {
    return !expired;
  }

  @Override
  @Transient
  public boolean isEnabled() {
    return isAccountNonExpired() && isAccountNonLocked();
  }
}
