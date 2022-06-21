package com.ancun.chain_storage.history.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeActionRepository extends JpaRepository<NodeActionEntity, Long> {
  Boolean existsByTx(String tx);
}
