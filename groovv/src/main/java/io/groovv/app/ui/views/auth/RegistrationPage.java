package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.service.registrations.RegistrationService;
import javax.inject.Inject;
import lombok.val;

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
    val formLayout = new FormLayout();

    val firstNameField = new TextField("First Name");
    val lastNameField = new TextField("Last Name");
    val emailAddressField = new TextField("Email");
    val dateOfBirthField = new DatePicker("Date of Birth");
    formLayout.add(firstNameField, lastNameField, emailAddressField, dateOfBirthField);
  }
}
