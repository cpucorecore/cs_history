package com.ancun.chain_storage.history.event_callback;

import static org.fisco.bcos.sdk.model.CryptoType.SM_TYPE;

import com.ancun.chain_storage.history.contracts.UserManager;
import com.ancun.chain_storage.history.db.NodeActionEntity;
import com.ancun.chain_storage.history.db.NodeActionRepository;
import java.util.List;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.ABICodec;
import org.fisco.bcos.sdk.abi.ABICodecException;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.EventLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class NodeActionEventCallback implements EventCallback {
  Logger logger = LoggerFactory.getLogger(NodeActionEventCallback.class);
  private ABICodec abiCodec = new ABICodec(new CryptoSuite(SM_TYPE));

  @Value("${app.GroupId}")
  Integer groupId;

  @Autowired private BcosSDK bcosSDK;
  @Autowired private NodeActionRepository nodeActionRepository;

  @Override
  public void onReceiveLog(int status, List<EventLog> logs) {
    Client queryClient = bcosSDK.getClient(groupId);
    if (0 == status && null != logs) {
      for (EventLog log : logs) {
        try {
          List<Object> event = abiCodec.decodeEvent(UserManager.ABI, "UserAction", log);
          if (3 != event.size()) {
            logger.error("wrong log: {}", log);
            continue;
          }

          String txHash = log.getTransactionHash();
          if (!nodeActionRepository.existsByTx(txHash)) {
            String timestamp =
                queryClient.getBlockByHash(log.getBlockHash(), false).getBlock().getTimestamp();
            NodeActionEntity nodeAction =
                new NodeActionEntity(
                    "0x" + event.get(0).toString().substring(26),
                    Integer.valueOf(event.get(1).toString()).intValue(),
                    event.get(2).toString(),
                    log.getTransactionHash(),
                    Long.valueOf(timestamp.substring(2), 16).longValue());
            nodeActionRepository.save(nodeAction);
          }
        } catch (ABICodecException e) {
          logger.error("ABICodecException: {}", e);
        }
      }
    }
  }
}
