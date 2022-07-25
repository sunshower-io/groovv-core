package io.groovv.service.registrations;

import io.groovv.model.api.core.User;
import io.groovv.model.api.registrations.RegistrationRequest;
import io.groovv.model.api.registrations.RegistrationRequest.Status;
import io.sunshower.persistence.id.Identifier;
import java.util.List;

public interface RegistrationService {

  List<RegistrationRequest> list();

  Identifier add(RegistrationRequest request);

  List<RegistrationRequest> getRequestsByStatus(Status inactive);

  User activate(Identifier id);
}
