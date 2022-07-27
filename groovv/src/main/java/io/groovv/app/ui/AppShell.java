package io.groovv.app.ui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

/**
 * Use the @PWA annotation make the application installable on phones, tablets and some desktop
 * browsers.
 */
@PageTitle("Groovv Investment Platform")
@Theme("groovv")
@PWA(
    name = "Groovv Investment Platform",
    shortName = "Groovv.io",
    iconPath = "assets/images/groovv/img/icons/icon.png")
public class AppShell implements AppShellConfigurator {

  @Override
  public void configurePage(AppShellSettings settings) {
    AppShellConfigurator.super.configurePage(settings);
    //    settings.addFavIcon("icon", "img/icons/icon-192x192.png", "192x192");
    //    settings.addLink("shortcut icon", "img/icons/favicon.ico");
  }
}
