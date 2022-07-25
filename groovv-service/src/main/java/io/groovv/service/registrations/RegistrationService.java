package io.groovv.service.registrations;

import io.groovv.model.api.registrations.RegistrationRequest;
import io.sunshower.persistence.id.Identifier;
import java.util.List;

public interface RegistrationService {

  List<RegistrationRequest> list();

  Identifier add(RegistrationRequest request);
}
