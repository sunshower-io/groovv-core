package io.groovv.service.admin;

import io.groovv.dto.views.model.core.PrincipalView;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface RealmAuthenticationMapper {

  Optional<PrincipalView> bind(Object principal,
      Function<Object, String> imageUrlProvider);

}
