package io.groovv.app.ui.views.accounts;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aire.ux.test.Context;
import com.aire.ux.test.RouteLocation;
import com.aire.ux.test.Select;
import com.aire.ux.test.TestContext;
import com.aire.ux.test.ViewTest;
import com.vaadin.flow.component.button.Button;
import io.groovv.app.ui.GroovvUITest;
import io.groovv.app.ui.views.dashboard.UserDashboard;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.app.ui.views.user.UserProfile;
import lombok.val;
import org.springframework.security.test.context.support.WithMockUser;

@GroovvUITest
@RouteLocation(scanClassPackage = HomeView.class)
@RouteLocation(scanClassPackage = UserProfile.class)
@RouteLocation(scanClassPackage = UserDashboard.class)
@RouteLocation(scanClassPackage = AccountView.class)
class AccountViewTest {



  @ViewTest
  @WithMockUser
  void ensureButtonExists(@Select AccountView view, @Context TestContext $) {
    val btn = $.selectFirst("vaadin-button[theme*=large]", Button.class).get();
    btn.click();
    assertEquals(4, $.selectComponents("vaadin-text-field").size());
  }
}