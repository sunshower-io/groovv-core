package io.sunshower.model.test;

import io.groovv.model.api.location.State;
import io.groovv.model.api.registrations.Realm;
import io.groovv.model.api.registrations.RegistrationRequest;
import java.util.Calendar;
import lombok.val;

public class RegistrationTests {

  public static RegistrationRequest createValidRequest() {
    val request = new RegistrationRequest();
    request.setEmailAddress("user@mydomain.com");
    request.setFirstName("User");
    request.setLastName("OfGroovv");
    val calender = Calendar.getInstance();
    calender.set(1978, 6, 6);
    request.setDateOfBirth(calender);
    request.setRealm(Realm.Google);
    request.setState(State.Colorado);

    request.setZipCode("80524");
    request.setPhoneNumber("9704438664");
    return request;
  }
}
