package com.ancun.chain_storage.history.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActionRepository extends JpaRepository<UserActionEntity, Long> {
  Boolean existsByTx(String tx);
}
