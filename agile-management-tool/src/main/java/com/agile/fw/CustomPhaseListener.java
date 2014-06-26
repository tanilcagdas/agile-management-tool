
package com.agile.fw;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class CustomPhaseListener implements PhaseListener {

   private static final long serialVersionUID = 1L;

   @Override
   public void afterPhase(PhaseEvent arg0) {

      /*
       * check to see directed to login page, if yes ajax causes problem to redirect. prepare response and redirect to login page
       */
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

      HttpSession session = request.getSession(false);
      if (session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) == null &&
               "partial/ajax".equals(request.getHeader("Faces-Request"))) {
            response.reset();
            response.resetBuffer();
            try {
               response.getWriter().print(xmlPartialRedirectToPage(request, "/login"));
               response.flushBuffer();
            }
            catch (IOException e) {
               e.printStackTrace();
            }
      }
   }

   @Override
   public void beforePhase(PhaseEvent arg0) {
      Exception e = (Exception) FacesContext.getCurrentInstance()
                                            .getExternalContext()
                                            .getSessionMap()
                                            .get(WebAttributes.AUTHENTICATION_EXCEPTION);

      if (e instanceof BadCredentialsException) {
         // logger.debug("Found exception in session map: " + e);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
         FacesContext.getCurrentInstance().addMessage(null,
                                                      new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                       "Invalid username or password!",
                                                                       "Invalid username or password!"));
      }

   }

   @Override
   public PhaseId getPhaseId() {
      return PhaseId.RENDER_RESPONSE;
   }

   private String xmlPartialRedirectToPage(HttpServletRequest request, String page) {
      StringBuilder sb = new StringBuilder();
      sb.append("<?xml version='1.0' encoding='UTF-8'?>");
      sb.append("<partial-response><redirect url=\"")
        .append(request.getContextPath())
        .append(request.getServletPath())
        .append(page)
        .append("\"/></partial-response>");
      return sb.toString();
   }
}
