package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.views.home.HomeView;
import lombok.val;

@Route("")
@CssImport(value = "./styles/groovv/views/auth/login.css", themeFor =
    "vaadin-login-overlay-wrapper")
public class LoginPage extends VerticalLayout {

  public LoginPage() {
    addClassName("login-rich-content");
    addClassName("login-view");
    setSizeFull();
    setAlignItems(Alignment.CENTER);

    val loginForm = new LoginOverlay();
    loginForm.setTitle("Groovv");
    loginForm.setDescription("Investment for Everyone");
    loginForm.setOpened(true);
    loginForm.addLoginListener(login -> {
      UI.getCurrent().access(() -> {
        loginForm.setOpened(false);
        UI.getCurrent().navigate(HomeView.class);
      });

    });
    add(loginForm);

  }

}
