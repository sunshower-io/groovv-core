package io.groovv.app.ui.views.admin.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import io.groovv.app.ui.components.annotations.UiDecorator;
import io.groovv.app.ui.views.admin.UserRegistrationList;
import io.groovv.app.ui.views.home.HomeView;
import io.groovv.app.ui.views.home.HomeView.Menus;
import java.util.function.Consumer;
import javax.annotation.security.RolesAllowed;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RolesAllowed("super-administrator")
@UiDecorator(":home:primary-navigation")
public class UserRegistrationListProvider implements Consumer<HomeView> {

  @Override
  public void accept(HomeView homeView) {
    val userMenu = (MenuItem) homeView.getSubmenu(Menus.USER);
    assert userMenu != null;
    userMenu.getSubMenu()
        .addItem("Registrations", click -> UI.getCurrent().navigate(UserRegistrationList.class));
  }

}
