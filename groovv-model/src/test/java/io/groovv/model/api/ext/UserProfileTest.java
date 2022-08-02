package io.groovv.model.api.ext;

import io.groovv.model.api.core.User;
import io.sunshower.model.test.ModelTest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.junit.jupiter.api.Test;

@ModelTest
class UserProfileTest {

  @PersistenceContext private EntityManager entityManager;

  @Test
  void ensureUserWithProfileCanBeCreated() {

    val user = validUser();
    val profile = validProfile();
    profile.setUser(user);
    profile.setImage(validImage());

    entityManager.flush();
  }

  ProfileImage validImage() {
    val img = new ProfileImage();
    img.setData("Hello".getBytes(StandardCharsets.UTF_8));
    return img;
  }

  private UserProfile validProfile() {
    val profile = new UserProfile();
    profile.setLocale(Locale.CANADA);
    return profile;
  }

  private User validUser() {
    val user = new User();
    user.getDetails().setFamilyName("First");
    user.getDetails().setGivenName("Last");
    return user;
  }
}
