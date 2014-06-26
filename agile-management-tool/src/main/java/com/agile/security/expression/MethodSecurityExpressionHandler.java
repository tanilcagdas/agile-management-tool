
package com.agile.security.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class MethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
   private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

   public MethodSecurityExpressionHandler() {
      super();
   }

   @Override
   protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
                                                                             MethodInvocation invocation) {
      MethodSecurityExpressionRoot root = new MethodSecurityExpressionRoot(authentication);
      root.setThis(invocation.getThis());
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
