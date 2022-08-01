package io.groovv.app.ui.config.inclusionvoters;

import static org.junit.jupiter.api.Assertions.*;

import com.aire.ux.ComponentInclusionManager;
import com.aire.ux.test.Context;
import com.aire.ux.test.Navigate;
import com.aire.ux.test.RouteLocation;
import com.aire.ux.test.TestContext;
import com.aire.ux.test.ViewTest;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.GroovvUITest;
import io.groovv.app.ui.views.accounts.AccountView;
import io.groovv.app.ui.views.admin.UserRegistrationList;
import io.groovv.app.ui.views.auth.LoginPage;
import io.groovv.app.ui.views.dashboard.UserDashboard;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.app.ui.views.user.UserProfile;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

@GroovvUITest
@RouteLocation(scanClassPackage = HomeView.class)
@RouteLocation(scanClassPackage = UserProfile.class)
@RouteLocation(scanClassPackage = UserDashboard.class)
@RouteLocation(scanClassPackage = AccountView.class)
@RouteLocation(scanClassPackage = LoginPage.class)
@RouteLocation(scanClassPackage = UserRegistrationList.class)
class RoleBasedComponentInclusionVoterTest {

  @Inject
  private ComponentInclusionManager inclusionManager;

  @Test
  void ensureInclusionManagerIsConfigured() {
    assertNotNull(inclusionManager);
  }

  @ViewTest
  @Navigate("")
  @WithMockUser
  void ensureNoValueIsPresentWhenLoggedInAsDefaultUser(@Context TestContext $) {
    assertTrue($.selectComponents("vaadin-button[text='Registrations']").isEmpty());
  }

  @ViewTest
  @Navigate("")
  @WithMockUser(roles = "super-administrator")
  void ensureValueIsPresentWhenAuthenticatedAsASuperUser(@Context TestContext $) {
    $.navigate(HomeView.class);
    assertTrue($.selectComponents("vaadin-button[text='Registrations']").isEmpty());
  }

}