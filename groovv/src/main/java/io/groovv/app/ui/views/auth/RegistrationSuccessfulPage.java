package io.groovv.app.ui.views.auth;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route(value = "registration/success")
@CssImport(value = "./styles/groovv/views/auth/login-form.css", themeFor = "vaadin-login-form")
public class RegistrationSuccessfulPage extends VerticalLayout {

  public RegistrationSuccessfulPage() {
    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    setSizeFull();
    addClassName("login");
    addClassName("groovv-login-form");
    add(new H1("Groovv"));

    add(new H3("Thanks for registering!"));
    add(new Paragraph("We'll be in touch shortly!"));
  }
}
