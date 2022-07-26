package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.val;

@Route("login")
@AnonymousAllowed
public class LoginPage extends AbstractUserPage {

  private static final String OAUTH_URL = "/oauth2/authorization/google";


  public LoginPage() {
    super();
    createLoginForm();
    createOAuthProviders();
  }

  private void createLoginForm() {
    layout.add(new LoginForm());
  }

  private void createOAuthProviders() {
    val hr = new Div();
    hr.setClassName("hr-strike");
    hr.setText("Or Login Via");
    layout.add(hr);
    val formLayout = new HorizontalLayout();
    formLayout.setWidthFull();
    formLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    val img = new Image("assets/images/oauth/providers/icons/google.svg", "Google OAuth");
    img.setWidth("24px");
    img.setHeight("24px");
    val loginLink = new Anchor(OAUTH_URL, "Google");
    loginLink.getElement().setAttribute("router-ignore", "true");
    loginLink.add(img);
    formLayout.add(loginLink);
    layout.add(formLayout);
  }
}
