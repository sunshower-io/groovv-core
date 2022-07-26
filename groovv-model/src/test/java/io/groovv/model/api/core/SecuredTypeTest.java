package io.groovv.model.api.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.sunshower.model.test.ModelTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.junit.jupiter.api.Test;

@ModelTest
class SecuredTypeTest {

  @PersistenceContext EntityManager entityManager;

  @Test
  void ensureEverythingIsConfiguredProperly() {
    assertNotNull(entityManager);
  }

  @Test
  void ensurePersistingSecurityIdentityWorks() {
    val sid = new SecurityIdentity();

    val tenant = tenant();

    val realm = new Realm();
    realm.setName("groovv");
    entityManager.persist(realm);

    entityManager.persist(tenant);

    val user = new User();
    realm.addUser(user);

    user.setUsername("sup");
    user.setPassword("password");
    user.getDetails().setGivenName("test");
    user.getDetails().setFamilyName("test");
    sid.setOwner(user);
    entityManager.persist(user);
    tenant.addUser(user);
    entityManager.persist(sid);
    entityManager.flush();
    assertNotNull(sid.getId());
  }

  private Tenant tenant() {
    val tenant = new Tenant();
    tenant.setName("pepsi");
    return tenant;
  }
}
