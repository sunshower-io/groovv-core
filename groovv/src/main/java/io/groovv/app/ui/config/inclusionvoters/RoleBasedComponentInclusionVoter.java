package io.groovv.app.ui.config.inclusionvoters;

import java.util.Set;
import javax.annotation.security.RolesAllowed;
import lombok.NonNull;

public class RoleBasedComponentInclusionVoter extends
    AbstractPrincipalAwareComponentInclusionVoter<RolesAllowed> {

  public RoleBasedComponentInclusionVoter() {
    super(RolesAllowed.class);
  }

  @Override
  protected Set<String> readAllowedAnnotations(@NonNull RolesAllowed annotation) {
    return Set.of(annotation.value());
  }
}
