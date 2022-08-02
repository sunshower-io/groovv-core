package io.groovv.model.api.core;

import io.groovv.model.api.converters.Base58Converter;
import io.sunshower.arcus.condensation.Alias;
import io.sunshower.arcus.condensation.Attribute;
import io.sunshower.arcus.condensation.Convert;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class EncryptedProperty<
    ID extends Serializable,
    P extends PropertyEncryptedEntity<ID, P, E>,
    E extends EncryptedProperty<ID, P, E>>
    extends AbstractEntity<ID> {

  /**
   * you can override this, but you'll usually have to override the annotations as well
   */
  @Setter
  @Getter(
      onMethod =
      @__({@ManyToOne, @PrimaryKeyJoinColumn(name = "owner_id", referencedColumnName = "id")}))
  private E owner;

  @Setter
  @Getter(onMethod = @__({@Basic, @Column(name = "property")}))
  private String property;

  @Setter
  @Getter(
      onMethod = @__({@Basic, @Attribute, @Column(name = "salt"), @Convert(Base58Converter.class)}))
  private byte[] salt;

  @Setter
  @Getter(
      onMethod =
      @__({
          @Basic,
          @Convert(Base58Converter.class),
          @Column(name = "initialization_vector"),
          @Attribute(
              alias = @Alias(read = "initialization-vector", write = "initialization-vector")),
      }))
  private byte[] initializationVector;

  protected EncryptedProperty(ID id) {
    super(id);
  }
}
