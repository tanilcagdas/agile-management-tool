
package com.agile.security.expression;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class MethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
   private Method                 method;
   private Object                 filterObject;
   private Object                 returnObject;
   private Object                 target;

   public MethodSecurityExpressionRoot(Authentication a) {
      super(a);
   }

   public MethodSecurityExpressionRoot(Authentication a, MethodInvocation mi) {
      super(a);
      this.method = mi.getMethod();
   }

   @Override
   public void setFilterObject(Object filterObject) {
      this.filterObject = filterObject;
   }

   @Override
   public Object getFilterObject() {
      return filterObject;
   }

   @Override
   public void setReturnObject(Object returnObject) {
      this.returnObject = returnObject;
   }

   @Override
   public Object getReturnObject() {
      return returnObject;
   }

   @Override
   public Object getThis() {
      return target;
   }

   public void setThis(Object target) {
      this.target = target;
   }
}
