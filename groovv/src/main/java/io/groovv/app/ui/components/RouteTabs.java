package io.groovv.app.ui.components;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import java.util.LinkedHashMap;
import java.util.Map;

public class RouteTabs extends Tabs implements BeforeEnterObserver {

  private final Map<RouterLink, Tab> tabMap;

  public RouteTabs() {
    tabMap = new LinkedHashMap<>();
  }

  public void add(RouterLink routerLink) {
    routerLink.setHighlightCondition(HighlightConditions.sameLocation());
    routerLink.setHighlightAction(
        (link, shouldHighlight) -> {
          if (shouldHighlight) {
            setSelectedTab(tabMap.get(routerLink));
          }
        });
    tabMap.put(routerLink, new Tab(routerLink));
    super.add(tabMap.get(routerLink));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    // In case no tabs will match
    setSelectedTab(null);
  }
}
