package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.RequiredFieldConfigurator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.groovv.model.api.location.State;
import io.groovv.model.api.registrations.RegistrationRequest;
import io.groovv.service.registrations.RegistrationService;
import javax.inject.Inject;
import lombok.val;

@AnonymousAllowed
@Route(value = "register", layout = AbstractUserPage.class)
public class RegistrationPage extends VerticalLayout {

  private final RegistrationService service;
  private final BeanValidationBinder<RegistrationRequest> registrationBinder;
  private TextField firstName;
  private TextField lastName;
  private EmailField emailAddress;
  private DatePicker dateOfBirth;
  private TextField phoneNumber;
  private Select<State> state;
  private TextField zipCode;

  @Inject
  public RegistrationPage(RegistrationService service) {
    this.service = service;
    registrationBinder = new BeanValidationBinder<>(RegistrationRequest.class);
    registrationBinder.setRequiredConfigurator(
        RequiredFieldConfigurator.NOT_EMPTY.chain(RequiredFieldConfigurator.NOT_NULL));
    setPadding(true);
    setSpacing(true);
    addClassName("login");
    addClassName("groovv-login-form");
    setWidth("unset");
    setMinHeight("60%");
    setJustifyContentMode(JustifyContentMode.START);
    configureForm();
    registrationBinder.setBean(new RegistrationRequest());
  }

  private void configureForm() {
    add(new H3("Register"));

    val formLayout = new FormLayout();
    formLayout.setMaxWidth("320px");
    //    formLayout.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("500", 2));
    formLayout.setResponsiveSteps(new ResponsiveStep("0", 2));

    firstName = new TextField("First Name");
    firstName.setClearButtonVisible(true);
    firstName.setRequired(true);
    firstName.setRequiredIndicatorVisible(true);

    lastName = new TextField("Last Name");
    lastName.setClearButtonVisible(true);
    lastName.setRequired(true);
    lastName.setRequiredIndicatorVisible(true);

    emailAddress = new EmailField("Email");
    emailAddress.setClearButtonVisible(true);
    emailAddress.setRequiredIndicatorVisible(true);

    dateOfBirth = new DatePicker("Date of Birth");
    dateOfBirth.setClearButtonVisible(true);
    dateOfBirth.setRequired(true);
    dateOfBirth.setRequiredIndicatorVisible(true);

    phoneNumber = new TextField("Phone Number");
    phoneNumber.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
    phoneNumber.setRequired(true);
    phoneNumber.setRequiredIndicatorVisible(true);

    state = new Select<State>();
    state.setItems(State.values());
    state.setLabel("State");
    state.setRequiredIndicatorVisible(true);

    formLayout.add(emailAddress, 2);
    formLayout.add(firstName, 2);
    formLayout.add(lastName, 2);
    formLayout.add(dateOfBirth, 1);
    formLayout.add(phoneNumber, 1);

    zipCode = new TextField("Zip");
    zipCode.setRequired(true);
    zipCode.setRequiredIndicatorVisible(true);

    formLayout.add(state, 1);
    formLayout.add(zipCode, 1);
    add(formLayout);

    configureBinding();

    //    val confirm =
    //        new Button(
    //            "Register",
    //            click -> {
    //              val bean = new RegistrationRequest();
    //              val errors = registrationBinder.();
    //              if(!errors.hasErrors()) {
    //                if (registrationBinder.writeBeanIfValid(bean)) {
    //                  service.add(bean);
    //                  UI.getCurrent().navigate(RegistrationSuccessfulPage.class);
    //                } else {
    //                  registrationBinder.setBean(bean);
    //                }
    //              }
    //            });

    val confirm =
        new Button(
            "Register",
            click -> {
              val validation = registrationBinder.validate();

              if (validation.isOk()) {
                val registration = registrationBinder.getBean();
                service.add(registration);
                UI.getCurrent().navigate(RegistrationSuccessfulPage.class);
              } else {
                validation
                    .getFieldValidationErrors()
                    .forEach(
                        error -> {
                          val field = error.getField();
                          if (field instanceof HasValidation hv) {
                            error.getMessage().ifPresent(hv::setErrorMessage);
                          }
                        });
              }
            });
    confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    formLayout.add(confirm);
  }

  private void configureBinding() {
    registrationBinder.setRequiredConfigurator(
        RequiredFieldConfigurator.NOT_EMPTY.chain(RequiredFieldConfigurator.NOT_NULL));
    registrationBinder.bindInstanceFields(this);

    //    registrationBinder.bind(emailAddress, "emailAddress");
    //    registrationBinder.bind(firstName, "firstName");
    //    registrationBinder.bind(lastName, "lastName");
    //    registrationBinder.bind(dateOfBirth, "dateOfBirth");
    //
    //
    //    registrationBinder.bind("dateOfBir")
    //        .forField(dateOfBirth)
    //        .asRequired()
    //        .bind(this::convertToCalendar, this::convertFromCalendar).validate();
    //
    //    registrationBinder
    //        .forField(phoneNumber)
    //        .asRequired()
    //        .bind(RegistrationRequest::getPhoneNumber,
    // RegistrationRequest::setPhoneNumber).validate();
    //
    //    registrationBinder
    //        .forField(state)
    //        .asRequired()
    //        .bind(RegistrationRequest::getState, RegistrationRequest::setState).validate();
    //
    //    registrationBinder
    //        .forField(zipCode)
    //        .asRequired()
    //        .bind(RegistrationRequest::getZipCode, RegistrationRequest::setZipCode).validate();
  }
}
