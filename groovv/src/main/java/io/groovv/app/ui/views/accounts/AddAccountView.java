package io.groovv.app.ui.views.accounts;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.views.home.HomeView;
import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "accounts/add", layout = HomeView.class)
public class AddAccountView extends VerticalLayout {


}
