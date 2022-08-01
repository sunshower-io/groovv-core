package io.groovv.app.ui.views.home;

import com.aire.ux.Host;
import com.aire.ux.Slot;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.Orientation;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletRequest;
import io.groovv.app.ui.config.PrincipalDetails;
import io.groovv.app.ui.config.SecurityUtils;
import io.groovv.app.ui.views.accounts.AccountView;
import io.groovv.app.ui.views.dashboard.UserDashboard;
import io.groovv.app.ui.views.user.UserProfile;
import io.groovv.persist.users.AccountRepository;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import lombok.Getter;
import lombok.val;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Route("")
@PermitAll
@Host("home")
@CssImport(value = "./styles/groovv/views/home/home.css")
public class HomeView extends AppLayout {

  private final Map<Menus, Component> submenus;
  @Getter
  @Slot(":primary-navigation-layout")
  private final HorizontalLayout menuLayout;

  @Getter
  @Slot(":primary-navigation")
  private final MenuBar navigationMenuBar;

  private final AccountRepository accountRepository;

  @Inject
  public HomeView(final AccountRepository accountRepository) {
    submenus = new EnumMap<>(Menus.class);
    menuLayout = createMenuLayout();
    navigationMenuBar = createNavigationMenuBar();
    this.accountRepository = accountRepository;

    initializeLayout();
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    if (accountRepository.count() == 0) {
      UI.getCurrent().navigate(AccountView.class);
    }
  }

  private void initializeLayout() {
    val toggle = new DrawerToggle();
    val tabs = createTabs();
    val header = new H1("Groovv");

    header.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");

    addToDrawer(tabs);
    //    addToNavbar(toggle, header);
    addToNavbar(true, toggle, header, menuLayout);
    menuLayout.add(navigationMenuBar);
  }

  private MenuBar createNavigationMenuBar() {
    val menuBar = new MenuBar();
    menuBar.addThemeVariants(MenuBarVariant.LUMO_SMALL, MenuBarVariant.LUMO_ICON);

    val details = SecurityUtils.getPrincipalDetails();
    if (details == null) {
      return menuBar;
    }

    var avatar = new Avatar(details.givenName());
    avatar.setImage(details.pictureUrl());
    val userMenu = menuBar.addItem(avatar);
    userMenu.addThemeNames("tertiary");
    userMenu.getSubMenu().addItem(createLogout(details));
    submenus.put(Menus.USER, userMenu);
    return menuBar;
  }

  public Component getSubmenu(Menus menu) {
    return submenus.get(menu);
  }

  private void doLogOut() {
    UI.getCurrent().getPage().setLocation("/login");
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
  }

  private Component createLogout(PrincipalDetails details) {
    val logout =
        new Button(
            "Log out " + details.givenName(),
            e -> {
              doLogOut();
            });
    logout.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
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

  private HorizontalLayout createMenuLayout() {
    val menuLayout = new HorizontalLayout();
    menuLayout.setWidthFull();
    menuLayout.setJustifyContentMode(JustifyContentMode.END);
    menuLayout.setPadding(true);
    return menuLayout;
  }

  public enum Menus {
    USER
  }
}
