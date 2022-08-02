package io.groovv.model.api.core;

import static org.junit.jupiter.api.Assertions.*;

import io.sunshower.model.test.ModelTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.junit.jupiter.api.Test;

@ModelTest
class AdministratorTest {

  @PersistenceContext private EntityManager entityManager;

  @Test
  void ensureDefaultAdministratorIsAvailable() {

    val user = new User();
    user.setUsername("admin@groovv.co");
    user.setPassword("password!");

    val details = new UserDetails();
    user.setDetails(details);

    details.setGivenName("Josiah");
    details.setFamilyName("Haswell");
    val realm = new Realm();
    realm.setName("google");
    realm.addUser(user);

    entityManager.persist(user);
    entityManager.flush();

    assertEquals(
        "admin@groovv.co",
        entityManager
            .createQuery(
                "select a from Administrator a left join fetch a.users u", Administrator.class)
            .setFirstResult(0)
            .setMaxResults(1)
            .getSingleResult()
            .getUsers()
            .iterator()
            .next()
            .getUsername());
  }
}
