package io.groovv.app.ui.views.home;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.Orientation;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletRequest;
import io.groovv.app.ui.views.accounts.AccountView;
import io.groovv.app.ui.views.dashboard.UserDashboard;
import io.groovv.app.ui.views.user.UserProfile;
import javax.annotation.security.PermitAll;
import lombok.val;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Route("")
@PermitAll
public class HomeView extends AppLayout {

  public HomeView() {

    val toggle = new DrawerToggle();
    val tabs = createTabs();
    val header = new H1("Groovv");

    header.getStyle()
        .set("font-size", "var(--lumo-font-size-l)")
        .set("margin", "0");

    addToDrawer(tabs);
//    addToNavbar(toggle, header);
    addToNavbar(true, toggle, header, createLogout());
  }

  private Component createLogout() {
    val logout = new Button("Log out", e -> {
      UI.getCurrent().getPage().setLocation("/login");
      SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
      logoutHandler.logout(
          VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
          null);
    });
    return logout;
  }

  private Tabs createTabs() {
    val tabs = new Tabs();
    tabs.setWidthFull();
    tabs.setOrientation(Orientation.VERTICAL);

    tabs.add(new Tab(VaadinIcon.MONEY.create(), new RouterLink("Accounts", AccountView.class)));
    tabs.add(new Tab(VaadinIcon.USER.create(), new RouterLink("Profile", UserProfile.class)));
    tabs.add(
        new Tab(VaadinIcon.DASHBOARD.create(), new RouterLink("Dashboard", UserDashboard.class)));

    return tabs;
  }
}
