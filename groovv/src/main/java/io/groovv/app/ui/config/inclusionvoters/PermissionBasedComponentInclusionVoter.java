package io.groovv.app.ui.config.inclusionvoters;

import com.aire.ux.ComponentInclusionVoter;
import com.aire.ux.ExtensionDefinition;

public class PermissionBasedComponentInclusionVoter implements ComponentInclusionVoter {

  @Override
  public boolean decide(ExtensionDefinition<?> extensionDefinition) {
    return true;
  }
}
