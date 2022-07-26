package io.groovv.app.ui.views.auth;

import static org.junit.jupiter.api.Assertions.*;

import com.aire.ux.test.Context;
import com.aire.ux.test.Forms;
import com.aire.ux.test.Navigate;
import com.aire.ux.test.RouteLocation;
import com.aire.ux.test.Select;
import com.aire.ux.test.TestContext;
import com.aire.ux.test.ViewTest;
import com.vaadin.flow.component.button.Button;
import io.groovv.app.ui.GroovvUITest;
import io.groovv.app.ui.views.accounts.AccountView;
import io.groovv.app.ui.views.dashboard.UserDashboard;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.app.ui.views.user.UserProfile;
import io.groovv.model.api.location.State;
import io.groovv.service.registrations.RegistrationService;
import java.time.LocalDate;
import javax.inject.Inject;
import lombok.val;

@GroovvUITest
@RouteLocation(scanClassPackage = HomeView.class)
@RouteLocation(scanClassPackage = UserProfile.class)
@RouteLocation(scanClassPackage = UserDashboard.class)
@RouteLocation(scanClassPackage = AccountView.class)
@RouteLocation(scanClassPackage = LoginPage.class)
class RegistrationPageTest {


  @Inject private RegistrationService registrationService;


  @ViewTest
  @Navigate("register")
  void ensureFormStructureIsCorrect(@Context TestContext $) {
    assertEquals(4, $.selectComponents("vaadin-text-field").size());
    assertEquals(1, $.selectComponents("vaadin-email-field").size());
    assertEquals(1, $.selectComponents("vaadin-date-picker[colspan='1']").size());
  }


  @ViewTest
  @Navigate("register")
  void ensureSavingValidRegistrationWorks(@Context TestContext $) {
    val date = LocalDate.of(1000, 6, 6);
    Forms.populate($)

        .field("vaadin-email-field").value("user@test.com")
        .field("vaadin-form-layout vaadin-text-field:nth-child(2)").value("User")
        .field("vaadin-form-layout vaadin-text-field:nth-child(3)").value("OfGroovv")
        .field("vaadin-date-picker").value(date)
        .field("vaadin-form-layout vaadin-text-field:nth-child(5)").value("5555555555")
        .field("vaadin-select").value(State.Alaska)
        .field("vaadin-form-layout vaadin-text-field:nth-child(7)").value("80524");
    $.selectFirst(Button.class).get().click();
    val regs = registrationService.list();
    assertEquals(1, regs.size());
    val reg = regs.get(0);

    assertEquals("user@test.com", reg.getEmailAddress());
    assertEquals("User", reg.getFirstName());
    assertEquals("OfGroovv", reg.getLastName());
    assertEquals("5555555555", reg.getPhoneNumber());
    assertEquals("80524", reg.getZipCode());
  }

}