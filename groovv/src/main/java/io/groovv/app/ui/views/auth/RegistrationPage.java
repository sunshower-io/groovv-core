package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.RequiredFieldConfigurator;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.model.api.location.State;
import io.groovv.model.api.registrations.RegistrationRequest;
import io.groovv.service.registrations.RegistrationService;
import java.time.LocalDate;
import java.util.Calendar;
import javax.inject.Inject;
import lombok.val;

@AnonymousAllowed
@Route(value = "register", layout = AbstractUserPage.class)
public class RegistrationPage extends VerticalLayout {

  private final RegistrationService service;
  private final BeanValidationBinder<RegistrationRequest> registrationBinder;
  private TextField firstNameField;
  private TextField lastNameField;
  private EmailField emailAddressField;
  private DatePicker dateOfBirthField;
  private TextField phoneNumber;
  private Select<State> state;
  private TextField zipCode;

  @Inject
  public RegistrationPage(RegistrationService service) {
    this.service = service;
    registrationBinder = new BeanValidationBinder<>(RegistrationRequest.class);
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

    firstNameField = new TextField("First Name");
    firstNameField.setClearButtonVisible(true);
    lastNameField = new TextField("Last Name");
    lastNameField.setClearButtonVisible(true);
    emailAddressField = new EmailField("Email");
    emailAddressField.setClearButtonVisible(true);
    dateOfBirthField = new DatePicker("Date of Birth");
    dateOfBirthField.setClearButtonVisible(true);
    phoneNumber = new TextField("Phone Number");
    phoneNumber.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");

    state = new Select<State>();
    state.setItems(State.values());
    state.setLabel("State");

    formLayout.add(emailAddressField, 2);
    formLayout.add(firstNameField, 2);
    formLayout.add(lastNameField, 2);
    formLayout.add(dateOfBirthField, 1);
    formLayout.add(phoneNumber, 1);

    zipCode = new TextField("Zip");
    formLayout.add(state, 1);
    formLayout.add(zipCode, 1);
    add(formLayout);

    configureBinding();

    val confirm =
        new Button(
            "Register",
            click -> {
              val bean = new RegistrationRequest();
              try {
                registrationBinder.writeBean(bean);
                service.add(bean);
                val notification = new Notification();
                notification.setDuration(1500);
                notification.setPosition(Position.TOP_STRETCH);
                notification.setText("Successfully registered!  We'll be in touch!");
                notification.open();
              } catch (ValidationException e) {
                e.printStackTrace();
              }
            });
    confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    formLayout.add(confirm);
  }

  private void configureBinding() {
    registrationBinder.setRequiredConfigurator(
        RequiredFieldConfigurator.NOT_EMPTY.chain(RequiredFieldConfigurator.NOT_NULL));

    registrationBinder
        .forField(emailAddressField)
        .bind(RegistrationRequest::getEmailAddress, RegistrationRequest::setEmailAddress);

    registrationBinder
        .forField(firstNameField)
        .bind(RegistrationRequest::getFirstName, RegistrationRequest::setFirstName);

    registrationBinder
        .forField(lastNameField)
        .bind(RegistrationRequest::getLastName, RegistrationRequest::setLastName);

    registrationBinder
        .forField(dateOfBirthField)
        .bind(this::convertToCalendar, this::convertFromCalendar);

    registrationBinder
        .forField(phoneNumber)
        .bind(RegistrationRequest::getPhoneNumber, RegistrationRequest::setPhoneNumber);

    registrationBinder
        .forField(state)
        .bind(RegistrationRequest::getState, RegistrationRequest::setState);

    registrationBinder
        .forField(zipCode)
        .bind(RegistrationRequest::getZipCode, RegistrationRequest::setZipCode);
  }

  private LocalDate convertToCalendar(RegistrationRequest request) {
    val date = request.getDateOfBirth();
    if (date == null) {
      return LocalDate.now();
    }
    return LocalDate.of(
        date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
  }

  private void convertFromCalendar(RegistrationRequest request, LocalDate localDate) {
    if (localDate != null) {
      val instance = Calendar.getInstance();
      instance.set(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
      request.setDateOfBirth(instance);
    }
  }
}
