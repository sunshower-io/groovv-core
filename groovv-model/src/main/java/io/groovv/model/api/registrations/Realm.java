package io.groovv.model.api.registrations;

import lombok.Getter;

public enum Realm {
  Groovv("groovv"),
  Google("google");

  @Getter final String key;

  Realm(final String key) {
    this.key = key;
  }
}
