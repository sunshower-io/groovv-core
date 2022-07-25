package io.groovv.persist.users;

public class Queriers {

  public static <ID, T, Q> Querier<ID, T, Q> noOp() {
    return new Querier<>() {

    };
  }

}
