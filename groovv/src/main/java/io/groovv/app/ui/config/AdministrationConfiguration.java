package io.groovv.app.ui.config;

import com.aire.ux.AsynchronousSessionQueue;
import com.aire.ux.ComponentInclusionManager;
import com.aire.ux.DefaultUserInterface;
import com.aire.ux.Extensions;
import com.aire.ux.PartialSelection;
import com.aire.ux.Selection;
import com.aire.ux.UserInterface;
import com.aire.ux.actions.ActionManager;
import com.aire.ux.actions.DefaultActionManager;
import com.aire.ux.concurrency.AccessQueue;
import com.aire.ux.ext.ExtensionRegistry;
import com.aire.ux.ext.spring.SpringComponentInclusionManager;
import com.aire.ux.ext.spring.SpringExtensionRegistry;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.server.VaadinService;
import io.groovv.app.ui.components.annotations.UiDecorator;
import io.groovv.app.ui.config.inclusionvoters.PermissionBasedComponentInclusionVoter;
import io.groovv.app.ui.config.inclusionvoters.RoleBasedComponentInclusionVoter;
import java.util.function.Supplier;
import lombok.val;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@ComponentScan(basePackages = "io.groovv.app.ui.views.admin.components")
public class AdministrationConfiguration {


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
      val decorator = factory.findAnnotationOnBean(name, UiDecorator.class);
//      val path = Selection.path(decorator.value());
      val complete = Selection.<HasElement>path(decorator.value());
      val hostTarget = Selection.<HasElement>path(complete.trunk());

      val ext = Extensions.create(hostTarget.leaf(), (HasElement target) -> {
        val componentSupplier = (Supplier<Component>) ctx.getBean(name, Supplier.class);
        target.getElement().appendChild(componentSupplier.get().getElement());
      });
      ui.register(hostTarget, ext);
    }

  }
}
