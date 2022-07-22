package io.groovv.app.ui.views.accounts;

import static org.junit.jupiter.api.Assertions.*;

import com.aire.ux.test.RouteLocation;
import com.aire.ux.test.Select;
import com.aire.ux.test.ViewTest;
import io.groovv.app.ui.GroovvUITest;
import io.groovv.app.ui.views.dashboard.UserDashboard;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.app.ui.views.user.UserProfile;
import io.sunshower.arcus.identicon.Jdenticon;
import org.junit.jupiter.api.Test;

@GroovvUITest
@RouteLocation(scanClassPackage = HomeView.class)
@RouteLocation(scanClassPackage = UserProfile.class)
@RouteLocation(scanClassPackage = UserDashboard.class)
@RouteLocation(scanClassPackage = AccountView.class)
class AccountViewTest {



  @ViewTest
  void ensureId(@Select AccountView view) {
    assertNotNull(view);
  }
}