package io.groovv.model.api.core;

import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;

public class EncryptedUserProperty
    extends EncryptedProperty<Identifier, User, EncryptedUserProperty> {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  public EncryptedUserProperty() {
    super(SEQUENCE.next());
  }
}
