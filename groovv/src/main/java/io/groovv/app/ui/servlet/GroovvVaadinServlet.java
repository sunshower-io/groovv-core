package io.groovv.app.ui.servlet;
import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.spring.SpringServlet;
import lombok.val;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.web.context.WebApplicationContext;

public class GroovvVaadinServlet extends SpringServlet implements DisposableBean {

  private final WebApplicationContext context;

  public GroovvVaadinServlet(
      WebApplicationContext context,
      boolean rootMapping) {
    super(context, rootMapping);
    this.context = context;
  }

  @Override
  protected VaadinServletService createServletService(
      DeploymentConfiguration deploymentConfiguration) throws ServiceException {
    val service = new VaadinSpringServletService(this, deploymentConfiguration, context);
    service.init();
    return service;
  }

}
