package io.groovv.app.ui.views.admin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.views.home.HomeView;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ROOT")
@Route(value = "admin/registrations", layout = HomeView.class)
public class UserRegistrationList extends VerticalLayout {}
