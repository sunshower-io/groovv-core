package io.groovv.persist.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.blazebit.persistence.spring.data.repository.EntityViewRepository;
import com.blazebit.persistence.view.EntityViewManager;
import io.github.glytching.junit.extension.random.Random;
import io.groovv.dto.views.model.core.UserView;
import io.groovv.model.api.core.User;
import io.groovv.model.api.core.UserDetails;
import io.groovv.persist.config.PersistenceTest;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.junit.jupiter.api.Test;

@PersistenceTest
public class UserViewRepositoryTest {

  @Inject
  private UserViewRepository repository;
  @Inject
  private UserRepository userRepository;

  @Random
  private UserDetails userDetails;

  @PersistenceContext
  EntityManager entityManager;

  @Random(excludes = {"permissions", "groups", "tenant", "roles"})
  private User user;

  @Inject
  private EntityViewManager entityViewManager;

  @Test
  void ensureSavingUserResultsInViewBeingCorrect() {
    user.setDetails(userDetails);
    userRepository.save(user);
    assertEquals(1L, repository.count());
    assertEquals(user.getId(), repository.findOne(user.getId()).getId());
  }

  @Test
  void ensureFamilyNameAndGivenNamesAreMapped() {
    user.setDetails(userDetails);
    userRepository.save(user);

    val view = repository.findOne(user.getId());
    assertEquals(user.getDetails().getGivenName(), view.getGivenName());
    assertEquals(user.getDetails().getFamilyName(), view.getFamilyName());
  }

  @Test
  void ensureCreatingViewWorks() {
    val userView = entityViewManager.create(UserView.class);
    userView.setFamilyName("Haswell");
    userView.setGivenName("Josiah");

    entityViewManager.saveFull(entityManager, userView);
    val user = userRepository.findById(userView.getId()).get();
    assertEquals(userView.getFamilyName(), user.getDetails().getFamilyName());
  }
}
