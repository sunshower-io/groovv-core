package io.groovv.dto.views.model.core;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import io.groovv.model.api.core.User;
import io.sunshower.persistence.id.Identifier;

@EntityView(User.class)
public interface UserView {

  @IdMapping
  Identifier getId();
}
