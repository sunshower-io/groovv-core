package io.groovv.service.admin.mappers;

import io.groovv.dto.views.model.core.PrincipalView;
import io.groovv.model.api.core.User;
import io.groovv.service.admin.RealmAuthenticationMapper;
import java.util.Optional;
import java.util.function.Function;
import lombok.val;

public class UserRealmAuthenticationMapper implements RealmAuthenticationMapper {

  @Override
  public Optional<PrincipalView> bind(Object principal, Function<Object, String> imageUrlProvider) {
    if (principal instanceof User u) {
      return Optional.of(
          new PrincipalView(
              u.getUsername(),
              getFamilyName(u),
              u.getUsername(),
              imageUrlProvider.apply(principal)));
    }
    return Optional.empty();
  }

  private String getFamilyName(User u) {
    val defaultValue = "<unknown>";
    var details = u.getDetails();
    if (details == null) {
      return defaultValue;
    }

    val fname = details.getFamilyName();
    if (fname == null) {
      return defaultValue;
    }
    return fname;
  }
}
