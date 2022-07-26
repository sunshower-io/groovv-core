package io.groovv.app.ui.config;

import io.groovv.app.ui.components.UIUtils;
import lombok.val;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class SecurityUtils {

  private SecurityUtils() {}

  public static boolean isUserLoggedIn() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null
        && !(authentication instanceof AnonymousAuthenticationToken)
        && authentication.isAuthenticated();
  }

  public static Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public static PrincipalDetails getCurrentUser(boolean required) {
    val result = getPrincipalDetails();
    if (result == null && required) {
      throw new IllegalArgumentException("Error: no current principal found");
    }
    return result;
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
          principalDetails.getAttribute("picture"));
    }
    if (principal instanceof User principalDetails) {
      return new PrincipalDetails(
          principalDetails.getUsername(),
          "<unknown>",
          principalDetails.getUsername(),
          UIUtils.base64Svg(principalDetails.getUsername()));
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
