package com.ancun.chain_storage.history.beans;

import com.ancun.chain_storage.history.event_callback.NodeActionEventCallback;
import com.ancun.chain_storage.history.event_callback.UserActionEventCallback;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanTables {

  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Bean
  public UserActionEventCallback getUserActionEventCallback() {
    return new UserActionEventCallback();
  }

  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Bean
  public NodeActionEventCallback getNodeActionEventCallback() {
    return new NodeActionEventCallback();
  }
}
