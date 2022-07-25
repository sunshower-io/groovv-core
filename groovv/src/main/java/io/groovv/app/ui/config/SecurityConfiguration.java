package io.groovv.app.ui.config;

import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfiguration extends VaadinWebSecurityConfigurerAdapter {

  private static final String LOGIN_URL = "/login";




  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    http.oauth2Login().loginPage(LOGIN_URL).permitAll();
  }

  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers(
            "/VAADIN/**",
            "/favicon.ico",
            "/robots.txt",
            "/manifest.webmanifest",
            "/sw.js",
            "/aire/initialize/**",
            "/offline-page.html",
            "/icons/**",
            "/images/**",
            "/frontend/**",
            "/webjars/**",
            "/h2-console/**",
            "/assets/**",
            "/sw-runtime-resources-precache.js",
            "/frontend-es5/**",
            "/frontend-es6/**");
  }
}
