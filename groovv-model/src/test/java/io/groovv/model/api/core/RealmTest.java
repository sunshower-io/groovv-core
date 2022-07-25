package io.groovv.model.api.core;

import static org.junit.jupiter.api.Assertions.*;

import io.sunshower.model.test.ModelTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.junit.jupiter.api.Test;

@ModelTest
class RealmTest {

  @PersistenceContext
  EntityManager entityManager;

  @Test
  void ensureRealmIDIsGenerated() {
    val realm = new Realm();
    realm.setName("test");
    entityManager.persist(realm);
    entityManager.flush();
    assertNotNull(realm.getId());
  }

}