package io.groovv.model.api.core;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ADMINISTRATOR_EMAILS")
public class Administrator {

  @Setter
  @Getter(
      onMethod =
          @__({
            @Id,
            @NotNull,
            @Column(name = "id"),
            @Basic(optional = false),
            @GeneratedValue(strategy = GenerationType.IDENTITY)
          }))
  private Integer id;

  @Setter
  @Getter(
      onMethod =
          @__({
            @OneToMany,
            @JoinTable(
                name = "ADMINISTRATORS_TO_USERS",
                joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id"),
                inverseJoinColumns =
                    @JoinColumn(name = "username", referencedColumnName = "username"))
          }))
  private Set<User> users = new HashSet<>();
}
