package com.ancun.chain_storage.history.beans;

import com.ancun.chain_storage.history.contracts.UserManager;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanUserManager {
  @Value("${app.UserManagerAddress}")
  private String userManagerAddress;

  @Autowired private Client client;

  @Bean
  public UserManager getUserManager() {
    return UserManager.load(userManagerAddress, client, client.getCryptoSuite().getCryptoKeyPair());
  }
}
