
package com.agile.fw;

import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/*
 * ProvActionListener class is used to capture the Magor exception messages
 * and display on the respective pages.
 */

public class CustomActionListener implements ActionListener {

   // This method is called when the user performs any action like modify,
   // add, save etc. If the backing bean method throws an exception then it
   // is caught here and faceMessages configured to display on the
   // respective page.

   private Logger                 logger = Logger.getLogger(CustomActionListener.class);

   public CustomActionListener() {
      super();
   }

   public void processAction(ActionEvent event) {
      FacesMessage fm = null;
      try {
         UIComponent source = event.getComponent();
         ActionSource2 actionSource = (ActionSource2) source;

         Object invokeResult = null;
         String outcome = null;
         MethodExpression expression = null;
         expression = actionSource.getActionExpression();

         if (expression != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Application application = context.getApplication();

            if (null != (invokeResult = expression.invoke(context.getELContext(), null))) {
               outcome = invokeResult.toString();
            }

            NavigationHandler navHandler = application.getNavigationHandler();
            navHandler.handleNavigation(context, (null != expression) ? expression.getExpressionString() : null, outcome);

            context.renderResponse();
         }
      }
      catch (Exception exception) {
         String errorMessage = exception.getMessage();
         Throwable cause = exception.getCause();
         fm = new FacesMessage(cause.getClass().getSimpleName(), null);
		logger.log(Level.ERROR, "Exception occured in UI", cause);
         exceptionMessage(fm);
      }
      finally {
      }
   }

   /**
    * Recursively look for Exceptions in the given cause
    * 
    * @param cause
    * @return true if a Exceptions was found
    */
   private void exceptionMessage(FacesMessage fm) {
      // set the message in facesContext
      FacesContext facesContext = FacesContext.getCurrentInstance();
      fm.setSeverity(FacesMessage.SEVERITY_ERROR);
      facesContext.addMessage(null, fm);
      facesContext.renderResponse();
   }
}
