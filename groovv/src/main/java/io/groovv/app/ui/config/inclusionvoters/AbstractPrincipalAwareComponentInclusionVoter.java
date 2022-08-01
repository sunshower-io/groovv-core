package io.groovv.app.ui.config.inclusionvoters;

import com.aire.ux.ComponentInclusionVoter;
import com.aire.ux.ExtensionDefinition;
import io.sunshower.arcus.condensation.mappings.LRUCache;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import lombok.NonNull;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractPrincipalAwareComponentInclusionVoter<A extends Annotation>
    implements ComponentInclusionVoter {

  private final Class<A> annotationType;
  private final LRUCache<Class<?>, Set<String>> authorityCache;

  protected AbstractPrincipalAwareComponentInclusionVoter(@NonNull Class<A> annotationType) {
    // this doesn't need to be very big
    this.annotationType = annotationType;
    this.authorityCache = new LRUCache<>(32);
  }

  @Override
  public final boolean decide(ExtensionDefinition<?> extensionDefinition) {
    val requiredAuthorities = resolve(extensionDefinition);
    val currentAuthorities = getCurrentAuthorities();
    for (val current : currentAuthorities) {
      if (requiredAuthorities.contains(current)) {
        return true;
      }
    }
    return false;
  }

  protected Class<? extends Annotation> getAnnotationType() {
    return annotationType;
  }

  @NonNull
  protected final Collection<? extends GrantedAuthority> getCurrentAuthorities() {
    val ctx = SecurityContextHolder.getContext();
    if (ctx == null) {
      return Collections.emptyList();
    }
    val authentication = ctx.getAuthentication();
    if (authentication == null) {
      return Collections.emptyList();
    }
    val authorities = authentication.getAuthorities();
    if (authorities == null) {
      return Collections.emptyList();
    }
    return authorities;
  }

  protected abstract Set<String> readAllowedAnnotations(@NonNull A annotation);

  private Set<String> resolve(ExtensionDefinition<?> definition) {
    val ext = definition.getExtension();
    var result = authorityCache.get(ext.getSource());
    if (result == null) {
      result = populateCache(ext.getSource());
    }
    if (!result.isEmpty()) {
      return result;
    }
    result = authorityCache.get(definition.getType());
    if (result == null) {
      result = populateCache(definition.getType());
    }
    if (!result.isEmpty()) {
      return result;
    }
    return Collections.emptySet();
  }

  private Set<String> populateCache(Class<?> annotatedType) {
    if (annotatedType.isAnnotationPresent(annotationType)) {
      val result = readAllowedAnnotations(annotatedType.getAnnotation(annotationType));
      authorityCache.put(annotatedType, result);
    }
    return Collections.emptySet();
  }
}
