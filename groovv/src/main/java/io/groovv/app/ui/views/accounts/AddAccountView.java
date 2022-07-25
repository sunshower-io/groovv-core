package io.groovv.app.ui.views.accounts;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.RequiredFieldConfigurator;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.config.SecurityUtils;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.model.api.accounts.Account;
import io.groovv.model.api.core.User;
import io.groovv.model.api.core.UserDetails;
import io.groovv.persist.users.AccountRepository;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import lombok.val;

@PermitAll
@Route(value = "accounts/add", layout = HomeView.class)
public class AddAccountView extends VerticalLayout {

  private final AccountRepository repository;
  private final BeanValidationBinder<Account> accountBinder;

  @Inject
  public AddAccountView(final AccountRepository repository) {
    this.repository = repository;
    this.accountBinder = new BeanValidationBinder<>(Account.class);

    setPadding(true);
    createAccountForm();
  }

  private void createAccountForm() {
    val principal = SecurityUtils.getCurrentUser(true);

    /** // TODO: 7/22/22 Josiah : map to database via repository--blocked on CPU quotas */
    val account = new Account();
    val user = new User();
    val details = new UserDetails();
    user.setDetails(details);
    details.setGivenName(principal.givenName());
    details.setFamilyName(principal.familyName());

    account.setOwner(user);

    add(new H2("Connect Bank Account"));
    val layout = new FormLayout();
    layout.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("720px", 2));

    val routingNumber = new TextField();
    routingNumber.setLabel("Routing Number");
    routingNumber.setClearButtonVisible(true);

    val accountNumber = new TextField();
    accountNumber.setLabel("Account Number");
    accountNumber.setClearButtonVisible(true);

    val accountName = new TextField();
    accountName.setLabel("Account Name");
    accountName.setClearButtonVisible(true);

    val accountOwnerName = new TextField();
    accountOwnerName.setLabel("Owner Name");
    accountOwnerName.setPlaceholder("Name on account");
    accountOwnerName.setValue(
        String.format("%s %s", principal.givenName(), principal.familyName()));
    layout.add(accountOwnerName);
    layout.add(accountName);

    layout.add(routingNumber);
    layout.add(accountNumber);

    accountBinder.setRequiredConfigurator(
        RequiredFieldConfigurator.NOT_EMPTY.chain(RequiredFieldConfigurator.NOT_NULL));

    accountBinder.forField(accountName).bind(Account::getName, Account::setName);

    accountBinder.forField(accountOwnerName).bind(Account::getOwnerName, Account::setOwnerName);

    accountBinder
        .forField(accountNumber)
        .bind(
            acc -> acc.getDetails().getAccountNumber(),
            (acc, value) -> acc.getDetails().setAccountNumber(value));

    val menuBar = new MenuBar();
    val cancelButton =
        new Button(
            "Cancel",
            VaadinIcon.CLOSE.create(),
            click -> {
              UI.getCurrent().navigate(AccountView.class);
              val notification = new Notification();
              notification.setDuration(1500);
              notification.setPosition(Position.TOP_STRETCH);
              notification.setText("No accounts have been added");
              notification.open();
            });
    val createButton =
        new Button(
            "Create",
            VaadinIcon.CHECK.create(),
            click -> {
              try {
                accountBinder.writeBean(account);
                repository.save(account);

                UI.getCurrent().navigate(AccountView.class);
                val notification = new Notification();
                notification.setDuration(1500);
                notification.setPosition(Position.TOP_STRETCH);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setText(
                    String.format("Successfully added account %s", account.getName()));
                notification.open();
              } catch (ValidationException e) {

              }
            });
    createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    menuBar.addItem(cancelButton);

    menuBar.addItem(createButton);
    layout.add(menuBar);

    add(layout);
  }
}
