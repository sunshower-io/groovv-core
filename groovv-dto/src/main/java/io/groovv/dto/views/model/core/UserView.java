package io.groovv.dto.views.model.core;

import com.blazebit.persistence.view.CascadeType;
import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;
import io.groovv.model.api.core.User;
import io.sunshower.persistence.id.Identifier;

@CreatableEntityView
@UpdatableEntityView
@EntityView(User.class)
public interface UserView {

  @IdMapping
  Identifier getId();

  @Mapping
  @UpdatableMapping(cascade = {CascadeType.DELETE})
  RealmView getRealm();
}
