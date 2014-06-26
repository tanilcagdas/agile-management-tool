
package com.agile.beans.security;

import com.agile.interfaces.ServiceBeanIF;

public class AuthorityServiceBean implements ServiceBeanIF {

   private String               name;


   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }



}
