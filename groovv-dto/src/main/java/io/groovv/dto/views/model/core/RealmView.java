package io.groovv.dto.views.model.core;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.UpdatableEntityView;
import io.groovv.model.api.core.Realm;
import io.sunshower.persistence.id.Identifier;

@CreatableEntityView
@UpdatableEntityView
@EntityView(Realm.class)
public interface RealmView {

  @IdMapping
  Identifier getId();

  @Mapping("name")
  String getName();

  void setName(String name);
}
