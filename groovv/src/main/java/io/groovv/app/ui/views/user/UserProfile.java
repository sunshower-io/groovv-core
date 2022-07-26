package io.groovv.app.ui.views.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.groovv.app.ui.views.home.HomeView;
import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "profile", layout = HomeView.class)
public class UserProfile extends VerticalLayout {}
