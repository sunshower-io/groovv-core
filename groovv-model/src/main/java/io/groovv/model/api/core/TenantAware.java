package io.groovv.model.api.core;

public interface TenantAware {

  void setTenant(Tenant tenant);

  Tenant getTenant();
}
