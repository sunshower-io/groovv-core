package io.groovv.dto.views.model.ext;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import io.groovv.dto.views.model.core.UserView;
import io.groovv.model.api.ext.UserProfile;
import io.sunshower.persistence.id.Identifier;

@EntityView(UserProfile.class)
public interface UserProfileView {

  @IdMapping
  Identifier getId();

  @Mapping("user")
  UserView getUser();
}
