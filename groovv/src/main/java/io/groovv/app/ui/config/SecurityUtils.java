package io.groovv.app.ui.config;

import com.vaadin.flow.server.HandlerHelper.RequestType;
import com.vaadin.flow.shared.ApplicationConstants;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class SecurityUtils {

  private SecurityUtils() {
  }

  public static boolean isUserLoggedIn() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null
           && !(authentication instanceof AnonymousAuthenticationToken)
           && authentication.isAuthenticated();
  }

  public static Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public static PrincipalDetails getPrincipalDetails() {
    val authentication = getAuthentication();
    if (authentication == null) {
      return null;
    }
    val principal = authentication.getPrincipal();
    if (principal instanceof OAuth2AuthenticatedPrincipal principalDetails) {
      return new PrincipalDetails(
          principalDetails.getAttribute("given_name"),
          principalDetails.getAttribute("family_name"),
          principalDetails.getAttribute("email"),
          principalDetails.getAttribute("picture")
      );
    }
    return null;
  }


  public static boolean isOAuthLogin() {
    val authentication = getAuthentication();
    if (authentication.getPrincipal() instanceof OAuth2AuthenticatedPrincipal) {
      return true;
    }
    return false;

  }
}
