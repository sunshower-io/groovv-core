package io.groovv.app.ui.config;

import com.aire.ux.AsynchronousSessionQueue;
import com.aire.ux.ComponentInclusionManager;
import com.aire.ux.DefaultUserInterface;
import com.aire.ux.Extensions;
import com.aire.ux.Registration;
import com.aire.ux.Selection;
import com.aire.ux.UserInterface;
import com.aire.ux.actions.ActionManager;
import com.aire.ux.actions.DefaultActionManager;
import com.aire.ux.concurrency.AccessQueue;
import com.aire.ux.ext.ExtensionRegistry;
import com.aire.ux.ext.spring.SpringComponentInclusionManager;
import com.aire.ux.ext.spring.SpringExtensionRegistry;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.VaadinService;
import io.groovv.app.ui.components.annotations.UiDecorator;
import io.groovv.app.ui.config.inclusionvoters.PermissionBasedComponentInclusionVoter;
import io.groovv.app.ui.config.inclusionvoters.RoleBasedComponentInclusionVoter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.val;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@ComponentScan(basePackages = "io.groovv.app.ui.views.admin.components")
public class AdministrationConfiguration {


  private final List<Registration> registrations;

  public AdministrationConfiguration() {
    registrations = new ArrayList<>();
  }

  @Bean
  public AccessQueue accessQueue() {
    return new AsynchronousSessionQueue();
  }

  @Bean
  public ComponentInclusionManager componentInclusionManager() {
    val inclusionManager = new SpringComponentInclusionManager();
    inclusionManager.register(new RoleBasedComponentInclusionVoter());
    inclusionManager.register(new PermissionBasedComponentInclusionVoter());
    return inclusionManager;
  }

  @Bean
  public ActionManager actionManager() {
    return new DefaultActionManager();
  }

  @Bean
  public UserInterface userInterface(ExtensionRegistry registry, AccessQueue accessQueue,
      ActionManager actionManager) {
    return new DefaultUserInterface(registry, accessQueue, actionManager);
  }

  @Bean
  public ExtensionRegistry extensionRegistry(AccessQueue queue, ComponentInclusionManager manager) {
    return new SpringExtensionRegistry(
        queue, () -> VaadinService.getCurrent().getContext(), manager);
  }

  @EventListener
  @SuppressWarnings("unchecked")
  public void onStartup(ContextRefreshedEvent event) {
    val ctx = event.getApplicationContext();
    val ui = ctx.getBean(UserInterface.class);
    val names = ctx.getBeanNamesForAnnotation(UiDecorator.class);
    val factory = (ListableBeanFactory) ctx.getAutowireCapableBeanFactory();
    for (val name : names) {
      doRegisterExtensions(ctx, ui, factory, name);
    }
  }

  private void doRegisterExtensions(ApplicationContext ctx, UserInterface ui,
      ListableBeanFactory factory,
      String name) {
    val decorator = factory.findAnnotationOnBean(name, UiDecorator.class);
    val complete = Selection.<Component>path(decorator.value());
    val hostTarget = Selection.<Component>path(complete.trunk());
    val ext = Extensions.create(hostTarget.leaf(), (Component target) -> {
      val componentConsumer = (Consumer<Component>) ctx.getBean(name, Consumer.class);
      componentConsumer.accept(target);
    });
    registrations.add(ui.register(hostTarget, ext));
  }

  @EventListener
  public void onShutdown(ContextStoppedEvent event) {
    registrations.forEach(Registration::close);
  }
}
