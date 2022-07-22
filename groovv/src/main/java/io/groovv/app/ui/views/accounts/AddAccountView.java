package io.groovv.app.ui.views.accounts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.config.SecurityUtils;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.persist.users.AccountRepository;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import lombok.val;

@PermitAll
@Route(value = "accounts/add", layout = HomeView.class)
public class AddAccountView extends VerticalLayout {


  private final AccountRepository repository;

  @Inject
  public AddAccountView(final AccountRepository repository) {
    this.repository = repository;
    createAccountForm();
    setPadding(true);
  }

  private void createAccountForm() {
    val principal = SecurityUtils.getCurrentUser(true);
    add(new H1("Connect Bank Account"));
    val layout = new FormLayout();
    layout.setResponsiveSteps(
        new ResponsiveStep("0", 1),
        new ResponsiveStep("720px", 2)
    );

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

    val menuBar = new MenuBar();
    val cancelButton = new Button("Cancel", VaadinIcon.CLOSE.create());
    val createButton = new Button("Create", VaadinIcon.CHECK.create());
    createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    menuBar.addItem(cancelButton);
    menuBar.addItem(createButton);
    layout.add(menuBar);


    add(layout);

  }

}
