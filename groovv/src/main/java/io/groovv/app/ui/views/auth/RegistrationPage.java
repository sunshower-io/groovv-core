package io.groovv.app.ui.views.auth;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.service.registrations.RegistrationService;
import javax.inject.Inject;

@Route("register")
@AnonymousAllowed
public class RegistrationPage extends HorizontalLayout {

  private final VerticalLayout layout;

  private final RegistrationService service;


  @Inject
  public RegistrationPage(RegistrationService service) {
    this.service = service;
    addClassName("login");
    addClassName("groovv-login-form");
    setSizeFull();
    configureForm();
    layout = new VerticalLayout();
    layout.setWidth("unset");


  }

  private void configureForm() {

  }


}
