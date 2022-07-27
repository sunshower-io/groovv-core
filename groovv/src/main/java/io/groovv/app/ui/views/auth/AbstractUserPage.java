package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import io.groovv.app.ui.components.RouteTabs;
import lombok.val;

@CssImport(value = "./styles/groovv/views/auth/login.css")
public class AbstractUserPage extends VerticalLayout implements RouterLayout {

  protected AbstractUserPage() {
    setSizeFull();
    addClassName("login");
    addClassName("groovv-login-form");
    setWidth("unset");
    add(new H1("Groovv"));
    add(new H2("Retirement for Everyone"));

    val tabs = new RouteTabs();
    tabs.add(new RouterLink("Register", RegistrationPage.class));
    tabs.add(new RouterLink("Login", LoginPage.class));
    add(tabs);

    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
  }
}
