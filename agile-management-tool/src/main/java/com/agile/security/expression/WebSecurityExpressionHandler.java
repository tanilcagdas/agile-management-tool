
package com.agile.security.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;



public class WebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {
   private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

   private WebSecurityExpressionHandler() {
      super();
   }

   @Override
   protected SecurityExpressionRoot createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
      WebSecurityExpressionRoot root = new WebSecurityExpressionRoot(authentication, fi);
      root.setPermissionEvaluator(getPermissionEvaluator());
      root.setTrustResolver(trustResolver);
      root.setRoleHierarchy(getRoleHierarchy());
      return root;
   }

   public AuthenticationTrustResolver getTrustResolver() {
      return trustResolver;
   }

   public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
      this.trustResolver = trustResolver;
   }

}
