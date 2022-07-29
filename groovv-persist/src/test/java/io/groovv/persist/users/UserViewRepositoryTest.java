package io.groovv.persist.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.glytching.junit.extension.random.Random;
import io.groovv.model.api.core.User;
import io.groovv.model.api.core.UserDetails;
import io.groovv.persist.config.PersistenceTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@PersistenceTest
public class UserViewRepositoryTest {

  @Inject private UserViewRepository repository;
  @Inject private UserRepository userRepository;

  @Random private UserDetails userDetails;

  @Random(excludes = {"permissions", "groups", "tenant", "roles"})
  private User user;

  @Test
  void ensureSavingUserResultsInViewBeingCorrect() {
    user.setDetails(userDetails);
    userRepository.save(user);
    assertEquals(1L, repository.count());
  }
}
