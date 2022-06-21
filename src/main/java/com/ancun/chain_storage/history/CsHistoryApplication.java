package com.ancun.chain_storage.history;

import com.ancun.chain_storage.history.contracts.NodeManager;
import com.ancun.chain_storage.history.contracts.UserManager;
import com.ancun.chain_storage.history.event_callback.NodeActionEventCallback;
import com.ancun.chain_storage.history.event_callback.UserActionEventCallback;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsHistoryApplication implements CommandLineRunner {
  @Value("${app.FromBlock}")
  private String fromBlock;

  @Value("${app.ToBlock}")
  private String toBlock;

  @Autowired private UserManager userManager;
  @Autowired private NodeManager nodeManager;

  @Resource private UserActionEventCallback userActionEventCallback;

  @Resource private NodeActionEventCallback nodeActionEventCallback;

  public static void main(String[] args) {
    SpringApplication.run(CsHistoryApplication.class, args);
  }

  @Override
  public void run(String... args) {
    userManager.subscribeUserActionEvent(fromBlock, toBlock, null, userActionEventCallback);
    nodeManager.subscribeNodeActionEvent(fromBlock, toBlock, null, nodeActionEventCallback);
  }
}
