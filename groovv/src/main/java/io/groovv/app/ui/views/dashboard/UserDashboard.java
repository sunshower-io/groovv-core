package io.groovv.app.ui.views.dashboard;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.views.home.HomeView;
import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "dashboard", layout = HomeView.class)
public class UserDashboard extends VerticalLayout {

}
