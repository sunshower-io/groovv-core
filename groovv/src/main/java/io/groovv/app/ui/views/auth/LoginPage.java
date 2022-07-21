package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.app.ui.views.home.HomeView;
import lombok.val;

@Route("login")
@CssImport(value = "./styles/groovv/views/auth/login.css", themeFor =
    "vaadin-login-overlay-wrapper")
@AnonymousAllowed
public class LoginPage extends VerticalLayout {
  private static final String OAUTH_URL = "/oauth2/authorization/google";

  public LoginPage() {
    Anchor loginLink = new Anchor(OAUTH_URL, "Login with Google");
    // Set router-ignore attribute so that Vaadin router doesn't handle the login request
    loginLink.getElement().setAttribute("router-ignore", true);
    add(loginLink);
    getStyle().set("padding", "200px");
    setAlignItems(FlexComponent.Alignment.CENTER);
  }

//
//  public LoginPage() {
//    addClassName("login-rich-content");
//    addClassName("login-view");
//    setSizeFull();
//    setAlignItems(Alignment.CENTER);
//
//    val loginForm = new LoginOverlay();
//    loginForm.setTitle("Groovv");
//    loginForm.setDescription("Investment for Everyone");
//    loginForm.setOpened(true);
//    loginForm.addLoginListener(login -> {
//      UI.getCurrent().access(() -> {
//        loginForm.setOpened(false);
//        UI.getCurrent().navigate(HomeView.class);
//      });
//
//    });
//    add(loginForm);
//
//  }

}
