package io.groovv.persist.config;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public enum PersistenceEnvironment {
  DatabaseUserName("GROOVV_DB_USERNAME", "groovv.db.username"),
  DatabasePassword("GROOVV_DB_PASSWORD", "groovv.db.password"),
  LeaderDomainName("GROOVV_DB_LEADER_FQDN", "groovv.db.leader.fqdn"),
  ReadReplicaDomainName("GROOVV_DB_READ_REPLICA_FQDN", "groovv.db.replicas.read.fqdn", true);

  private final boolean prefix;

  private final String propertyName;
  private final String environmentName;

  PersistenceEnvironment(final String environmentName, final String propertyName) {
    this(environmentName, propertyName, false);
  }

  PersistenceEnvironment(
      final String environmentName, final String propertyName, boolean isPrefix) {
    this.prefix = isPrefix;
    this.propertyName = propertyName;
    this.environmentName = environmentName;
  }

  public boolean exists() {
    return isEnvironmentVariable() || isSystemProperty();
  }

  public boolean isSystemProperty() {
    return System.getProperties().containsKey(propertyName);
  }

  public boolean isEnvironmentVariable() {
    return System.getenv().containsKey(environmentName);
  }

  static final AtomicReference<List<String>> valueCache;

  static {
    valueCache = new AtomicReference<>();
  }

  public List<String> getValues() {
    if (prefix) {
      var cachedValue = valueCache.get();
      if (cachedValue != null) {
        return cachedValue;
      }
      cachedValue = new ArrayList<>();

      cachedValue.addAll(
          System.getenv().entrySet().stream()
              .flatMap(
                  e ->
                      e.getKey().startsWith(environmentName)
                          ? Stream.of(e.getValue())
                          : Stream.empty())
              .toList());

      cachedValue.addAll(
          System.getProperties().entrySet().stream()
              .flatMap(
                  e ->
                      String.valueOf(e.getKey()).startsWith(propertyName)
                          ? Stream.of(String.valueOf(e.getValue()))
                          : Stream.empty())
              .toList());
      valueCache.set(cachedValue);
      return cachedValue;
    } else {
      return List.of(getString());
    }
  }

  public String getString() {
    if (isEnvironmentVariable()) {
      return System.getenv(environmentName);
    }
    if (isSystemProperty()) {
      return System.getProperty(propertyName);
    }
    throw new NoSuchElementException(
        "Error: no environment variable with name '%s' exists".formatted(environmentName));
  }

  public String getString(String defaultValue) {
    try {
      return getString();
    } catch (Exception ex) {
      return defaultValue;
    }
  }

  public int getInt(int defaultValue) {
    try {
      return Integer.parseInt(getString());
    } catch (Exception ex) {
      return defaultValue;
    }
  }

  public int getInt() {
    return Integer.parseInt(getString());
  }
}
