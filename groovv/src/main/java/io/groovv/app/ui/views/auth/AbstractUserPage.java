package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport(value = "./styles/groovv/views/auth/login.css")
public class AbstractUserPage extends HorizontalLayout {


  protected final VerticalLayout layout;


  protected AbstractUserPage() {
    setSizeFull();
    addClassName("login");
    addClassName("groovv-login-form");
    layout = new VerticalLayout();
    layout.setWidth("unset");
    layout.add(new H1("Groovv"));
    layout.add(new H2("Retirement for Everyone"));
    add(layout);

    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultVerticalComponentAlignment(Alignment.CENTER);
  }




}
