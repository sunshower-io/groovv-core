package io.groovv.persist.users;

import com.blazebit.persistence.spring.data.repository.EntityViewRepository;
import io.groovv.dto.views.model.core.UserView;
import io.sunshower.persistence.id.Identifier;

public interface UserViewRepository extends EntityViewRepository<UserView, Identifier> {}
