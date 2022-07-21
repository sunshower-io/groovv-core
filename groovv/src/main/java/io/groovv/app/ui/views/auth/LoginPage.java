package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.app.ui.views.home.HomeView;
import lombok.val;

@Route("login")
@AnonymousAllowed
@CssImport(value = "./styles/groovv/views/auth/login.css")
public class LoginPage extends HorizontalLayout {

  private static final String OAUTH_URL = "/oauth2/authorization/google";

  public LoginPage() {
    setSizeFull();
    setAlignItems(Alignment.CENTER);
    setJustifyContentMode(JustifyContentMode.CENTER);
    addClassName("groovv-login-form");
    createForm();
  }

  private void createForm() {
    val vlayout = new VerticalLayout();
    vlayout.getStyle().set("width", "auto");
    val formLayout = new FormLayout();

    formLayout.add(new H1("Groovv"));
    formLayout.add(new H2("Retirement for Everyone"));
    formLayout.add(new H3("Login"));
    val img = new Image("assets/images/oauth/providers/icons/google.svg", "Google OAuth");
    img.setWidth("24px");
    img.setHeight("24px");
    val loginLink = new Anchor(OAUTH_URL, "Google");
    loginLink.getElement().setAttribute("router-ignore", "true");
    img.add(loginLink);
    formLayout.add(img);
    vlayout.add(formLayout);
    add(vlayout);
  }


}
