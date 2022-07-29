package io.groovv.app.ui.views.admin.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import io.groovv.app.ui.components.annotations.UiDecorator;
import io.groovv.app.ui.views.admin.UserRegistrationList;
import java.util.function.Supplier;
import javax.annotation.security.RolesAllowed;
import org.springframework.stereotype.Component;

@Component
@RolesAllowed("super-administrator")
@UiDecorator(":home:primary-navigation")
public class UserRegistrationListProvider implements Supplier<Button> {

  @Override
  public Button get() {
    return new Button(
        "Registrations",
        click -> {
          UI.getCurrent().navigate(UserRegistrationList.class);
        });
  }
}
