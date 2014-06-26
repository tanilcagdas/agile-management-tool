
package com.agile.security.expression;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.IpAddressMatcher;

public class WebSecurityExpressionRoot extends SecurityExpressionRoot {
   public final HttpServletRequest request;

   public WebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
      super(a);
      this.request = fi.getRequest();
   }

   public boolean hasIpAddress(String ipAddress) {
      return (new IpAddressMatcher(ipAddress).matches(request));
   }
}
