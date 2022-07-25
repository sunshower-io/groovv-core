package io.groovv.model.api.accounts;

import static org.junit.jupiter.api.Assertions.*;

import io.groovv.model.api.core.ModelTest;
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