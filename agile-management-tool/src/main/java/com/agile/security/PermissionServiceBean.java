
package com.agile.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "permission")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"service", "right"})
public class PermissionServiceBean implements  Serializable {
   @XmlTransient
   private String authority;
   private String service;
   private String right;

   public String getAuthority() {
      return authority;
   }

   public void setAuthority(String authority) {
      this.authority = authority;
   }

   public String getService() {
      return service;
   }

   public void setService(String service) {
      this.service = service;
   }

   public String getRight() {
      return right;
   }

   public void setRight(String right) {
      this.right = right;
   }

   @Override
   public String toString() {
      StringBuilder st = new StringBuilder();
      st.append("[Permission Service Bean: ");
      st.append("[authority:").append(getAuthority());
      st.append("[service:").append(getService());
      st.append("][right:").append(getRight());
      st.append("]]");
      return st.toString();
   }

   @Override
   public boolean equals(Object obj) {
      boolean result = false;
      PermissionServiceBean permission = null;
      if (obj.getClass().equals(permission)) {
         permission = (PermissionServiceBean) obj;
         if (authority.equals(permission.getAuthority()) && service.equals(permission.getService()) && right.equals(permission.getRight())) {
            result = true;
         }
      }
      return result;
   }
}
