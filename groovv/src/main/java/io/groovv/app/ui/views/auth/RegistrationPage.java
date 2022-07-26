package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.model.api.location.State;
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
    configureForm();
  }

  private void configureForm() {
    add(new H3("Register"));

    val formLayout = new FormLayout();
    formLayout.setMaxWidth("320px");
//    formLayout.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("500", 2));
    formLayout.setResponsiveSteps(new ResponsiveStep("0", 2));

    val firstNameField = new TextField("First Name");
    firstNameField.setClearButtonVisible(true);
    val lastNameField = new TextField("Last Name");
    lastNameField.setClearButtonVisible(true);
    val emailAddressField = new EmailField("Email");
    emailAddressField.setClearButtonVisible(true);
    val dateOfBirthField = new DatePicker("Date of Birth");
    dateOfBirthField.setClearButtonVisible(true);
    val phoneNumber = new TextField("Phone Number");
    phoneNumber.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");

    val state = new Select<State>();
    state.setItems(State.values());
    state.setLabel("State");

    formLayout.add(emailAddressField, 2);
    formLayout.add(firstNameField, 2);
    formLayout.add(lastNameField, 2);
    formLayout.add(dateOfBirthField, 1);
    formLayout.add(phoneNumber, 1);

    val zipCode = new TextField("Zip");
    formLayout.add(state, 1);
    formLayout.add(zipCode, 1);

    add(formLayout);
  }
}
