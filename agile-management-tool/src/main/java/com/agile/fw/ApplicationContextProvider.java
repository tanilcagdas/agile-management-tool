package com.agile.fw;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextProvider implements ApplicationContextAware {
  private static ApplicationContext ctx = null;

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
     this.ctx = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
     return ctx;
  }
}