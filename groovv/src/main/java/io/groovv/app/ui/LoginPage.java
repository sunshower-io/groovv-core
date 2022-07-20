package io.groovv.app.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.views.home.HomeView;
import lombok.val;

@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class LoginPage extends VerticalLayout {

  public LoginPage() {
    addClassName("login-rich-content");
    addClassName("login-view");
    setSizeFull();
    setAlignItems(Alignment.CENTER);


    val loginForm = new LoginOverlay();
    loginForm.setTitle("Welcome to Groovv");
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
