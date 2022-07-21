package io.groovv.app.ui.config;

import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfiguration extends VaadinWebSecurityConfigurerAdapter {

  private static final String LOGIN_URL = "/login";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    http.oauth2Login().loginPage(LOGIN_URL).permitAll();
  }
}
