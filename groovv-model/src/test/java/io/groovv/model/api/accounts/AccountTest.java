package io.groovv.model.api.accounts;

import io.sunshower.model.test.ModelTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;

@ModelTest
class AccountTest {

  @PersistenceContext private EntityManager entityManager;



  @Test
  void ensureAccountIsMappedCorrectly() {

  }
}