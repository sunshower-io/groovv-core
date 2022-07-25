package io.groovv.app.ui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

/**
 * Use the @PWA annotation make the application installable on phones, tablets and some desktop
 * browsers.
 */
@PWA(name = "Groovv Investment Platform", shortName = "Groovv.io")
public class AppShell implements AppShellConfigurator {}
