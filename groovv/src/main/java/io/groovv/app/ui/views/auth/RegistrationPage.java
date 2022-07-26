package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.service.registrations.RegistrationService;
import javax.inject.Inject;
import lombok.val;

@AnonymousAllowed
@Route(value = "register", layout = AbstractUserPage.class)
public class RegistrationPage extends VerticalLayout {


  private final RegistrationService service;

  @Inject
  public RegistrationPage(RegistrationService service) {
    this.service = service;
    setPadding(true);
    setSpacing(true);
    addClassName("login");
    addClassName("groovv-login-form");
    setWidth("unset");
    setMinHeight("60%");
    setJustifyContentMode(JustifyContentMode.START);
//    setDefaultHorizontalComponentAlignment(Alignment.START);
    configureForm();
  }

  private void configureForm() {
    add(new H3("Register"));
    val formLayout = new FormLayout();
    formLayout.setMaxWidth("320px");
    formLayout.setResponsiveSteps(new ResponsiveStep("0", 1));

    val firstNameField = new TextField("First Name");
    val lastNameField = new TextField("Last Name");
    val emailAddressField = new TextField("Email");
    val dateOfBirthField = new DatePicker("Date of Birth");
    formLayout.add(firstNameField, lastNameField, emailAddressField, dateOfBirthField);
    add(formLayout);
  }
}
