package io.groovv.app.ui.views.accounts;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.components.UIUtils;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.model.api.accounts.Account;
import io.groovv.persist.users.AccountRepository;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.val;

@PermitAll
@Route(value = "accounts", layout = HomeView.class)
@CssImport(value = "./styles/groovv/views/accounts/account-view.css")
public class AccountView extends VerticalLayout {

  static final SerializableBiConsumer<Image, Account> statusComponentUpdater;
  static final SerializableBiConsumer<VerticalLayout, Account> accountDetailsComponentUpdater;

  static {
    statusComponentUpdater = new IdenticonComponentUpdater();
    accountDetailsComponentUpdater = new AccountDetailsComponentUpdater();
  }

  private final AccountRepository repository;

  @Inject
  public AccountView(@NonNull AccountRepository repository) {
    this.repository = repository;
    addClassName("accounts");
    setAlignItems(Alignment.START);

    setSizeFull();
    doLayout();
    setJustifyContentMode(JustifyContentMode.CENTER);
  }

  private void doLayout() {
    if (!hasAccounts()) {
      addCta();
    } else {
      configureAccountList();
    }
  }

  private void addCta() {
    setAlignItems(Alignment.CENTER);
    val icon = VaadinIcon.PIGGY_BANK_COIN.create();
    add(icon);
    add(new H2("Connect a bank account"));
    add(new Paragraph("Connecting a bank account allows us to transfer assets to your retirement"));

    val button = new Button("Add Bank Account");
    button.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_PRIMARY);

    button.addClickListener(
        event -> {
          UI.getCurrent().navigate(AddAccountView.class);
        });
    add(button);
  }

  private boolean hasAccounts() {
    return repository.count() > 0;
  }

  @SuppressWarnings("unchecked")
  private void configureAccountList() {

    add(
        new Button(
            "Add",
            VaadinIcon.PLUS.create(),
            e -> {
              UI.getCurrent().navigate(AddAccountView.class);
            }));
    val grid = new Grid<Account>();
    grid.addColumn(createIdenticonComponentRenderer()).setAutoWidth(true).setFlexGrow(0);
    //    grid.setItems(new RepositoryDataProvider<Identifier, Account, Void>(repository));
    grid.addColumn(new ComponentRenderer<>(VerticalLayout::new, accountDetailsComponentUpdater))
        .setHeader("Details")
        .setAutoWidth(true);
    add(grid);
  }

  private static ComponentRenderer<Image, Account> createIdenticonComponentRenderer() {
    return new ComponentRenderer<>(Image::new, statusComponentUpdater);
  }

  static final class AccountDetailsComponentUpdater
      implements SerializableBiConsumer<VerticalLayout, Account> {

    @Override
    public void accept(VerticalLayout accountDetailsComponent, Account account) {
      val nameSpan = new Span(account.getName());
      val ownerName = new Span(account.getOwnerName());
      ownerName.getStyle().set("font-size", "var(--lumo-font-size-s)");
      accountDetailsComponent.add(nameSpan, ownerName);
    }
  }

  static final class IdenticonComponentUpdater implements SerializableBiConsumer<Image, Account> {

    @Override
    public void accept(Image image, Account account) {
      image.setSrc(UIUtils.base64Svg(account));
      image.setWidth("32px");
      image.setHeight("32px");
    }
  }
}
