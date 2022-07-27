package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.val;

@AnonymousAllowed
@Route(value = "login", layout = AbstractUserPage.class)
@CssImport(value = "./styles/groovv/views/auth/login-form.css", themeFor = "vaadin-login-form")
public class LoginPage extends VerticalLayout {

  private static final String OAUTH_URL = "/oauth2/authorization/google";

  public LoginPage() {
    getStyle().set("margin-top", "0");
    getStyle().set("padding-top", "0");
    createLoginForm();
    createOAuthProviders();
    setWidth("unset");
    setMinHeight("60%");
    setJustifyContentMode(JustifyContentMode.START);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
  }

  private void createLoginForm() {
    //    layout.add(new LoginForm());
    add(new LoginForm());
  }

  private void createOAuthProviders() {
    val hr = new Div();
    hr.setClassName("hr-strike");
    hr.setText("Or Login Via");
    add(hr);
    val formLayout = new HorizontalLayout();
    setMaxWidth("720px");
    val img = new Image("assets/images/oauth/providers/icons/google.svg", "Google OAuth");
    img.setWidth("24px");
    img.setHeight("24px");
    val loginLink = new Anchor(OAUTH_URL, "Google");
    loginLink.getElement().setAttribute("router-ignore", "true");
    loginLink.add(img);
    formLayout.add(loginLink);
    add(formLayout);
  }
}
