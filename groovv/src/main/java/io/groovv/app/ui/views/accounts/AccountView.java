package io.groovv.app.ui.views.accounts;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.data.providers.RepositoryDataProvider;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.model.api.accounts.Account;
import io.groovv.persist.users.AccountRepository;
import io.sunshower.arcus.identicon.Jdenticon;
import io.sunshower.lang.common.encodings.Encoding;
import io.sunshower.lang.common.encodings.Encodings;
import io.sunshower.lang.common.encodings.Encodings.Type;
import io.sunshower.persistence.id.Identifier;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.val;

@PermitAll
@Route(value = "accounts", layout = HomeView.class)
@CssImport(value = "./styles/groovv/views/accounts/account-view.css")
public class AccountView extends VerticalLayout {

  static final SerializableBiConsumer<Image, Account> statusComponentUpdater;

  static {
    statusComponentUpdater = new IdenticonComponentUpdater();
  }

  private final AccountRepository repository;

  @Inject
  public AccountView(@NonNull AccountRepository repository) {
    this.repository = repository;
    addClassName("accounts");

    setSizeFull();
    doLayout();
    setAlignItems(Alignment.CENTER);
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
    val icon = VaadinIcon.PIGGY_BANK_COIN.create();
    add(icon);
    add(new H2("Connect a bank account"));
    add(new Paragraph("Connecting a bank account allows us to transfer assets to your retirement"));

    val button = new Button("Add Bank Account");
    button.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_PRIMARY);

    button.addClickListener(event -> {
      UI.getCurrent().navigate(AddAccountView.class);
    });
    add(button);


  }

  private boolean hasAccounts() {
    return repository.count() > 0;
  }

  @SuppressWarnings("unchecked")
  private void configureAccountList() {

    val grid = new Grid<>(Account.class);
    grid.addColumn(createIdenticonComponentRenderer());
//    grid.setItems(new RepositoryDataProvider<>(repository));
    grid.setItems(new RepositoryDataProvider<Identifier, Account, Void>(repository));

  }


  private static ComponentRenderer<Image, Account> createIdenticonComponentRenderer() {
    return new ComponentRenderer<>(Image::new, statusComponentUpdater);
  }


  static final class IdenticonComponentUpdater implements SerializableBiConsumer<Image, Account> {

    static final Encoding encoding = Encodings.create(Type.Base64);


    @Override
    public void accept(Image image, Account account) {
      val svg = Jdenticon.toSvg(account);
      val url = String.format("data:image/svg;base64,", encoding.encode(svg));
      image.setSrc(url);
      image.setWidth("32px");
      image.setHeight("32px");
    }
  }

}
