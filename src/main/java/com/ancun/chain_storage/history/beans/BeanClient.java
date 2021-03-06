package com.ancun.chain_storage.history.beans;

import static org.fisco.bcos.sdk.model.CryptoType.SM_TYPE;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.Config;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanClient {
  Logger logger = LoggerFactory.getLogger(BeanClient.class);

  @Value("${app.ConfigFilePath}")
  private String configFilePath;

  @Value("${app.GroupId}")
  private Integer groupId;

  @Bean
  public Client client() {
    ConfigOption configOption = null;
    try {
      configOption = Config.load(configFilePath, SM_TYPE);
    } catch (ConfigException e) {
      logger.error("load configure failed, exception: {}", e);
    }

    BcosSDK bcosSDK = new BcosSDK(configOption);
    return bcosSDK.getClient(groupId);
  }

  @Bean
  public BcosSDK bcosSDK() {
    ConfigOption configOption = null;
    try {
      configOption = Config.load(configFilePath, SM_TYPE);
    } catch (ConfigException e) {
      logger.error("load configure failed, exception: {}", e);
    }

    return new BcosSDK(configOption);
  }
}
