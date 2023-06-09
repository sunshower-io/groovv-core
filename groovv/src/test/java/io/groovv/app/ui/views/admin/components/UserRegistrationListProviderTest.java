package io.groovv.app.ui.views.admin.components;

import com.aire.ux.test.Context;
import com.aire.ux.test.Navigate;
import com.aire.ux.test.RouteLocation;
import com.aire.ux.test.TestContext;
import com.aire.ux.test.ViewTest;
import io.groovv.app.ui.GroovvUITest;
import io.groovv.app.ui.views.accounts.AccountView;
import io.groovv.app.ui.views.admin.UserRegistrationList;
import io.groovv.app.ui.views.auth.LoginPage;
import io.groovv.app.ui.views.dashboard.UserDashboard;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.app.ui.views.user.UserProfile;
import org.springframework.security.test.context.support.WithMockUser;

@GroovvUITest
@RouteLocation(scanClassPackage = HomeView.class)
@RouteLocation(scanClassPackage = UserProfile.class)
@RouteLocation(scanClassPackage = UserDashboard.class)
@RouteLocation(scanClassPackage = AccountView.class)
@RouteLocation(scanClassPackage = LoginPage.class)
@RouteLocation(scanClassPackage = UserRegistrationList.class)
class UserRegistrationListProviderTest {

  @ViewTest
  @WithMockUser
  @Navigate("admin/registrations")
  void ensureRegistrationButtonIsNotPresentForAnonymousUser(@Context TestContext $) {}
}
