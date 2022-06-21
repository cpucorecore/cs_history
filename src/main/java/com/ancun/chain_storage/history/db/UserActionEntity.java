package com.ancun.chain_storage.history.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_action")
public class UserActionEntity {
  public UserActionEntity(String address, int action, String cid, String tx, long timestamp) {
    this.address = address;
    this.action = action;
    this.cid = cid;
    this.tx = tx;
    this.timestamp = timestamp;
  }

  public UserActionEntity() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public String getTx() {
    return tx;
  }

  public void setTx(String tx) {
    this.tx = tx;
  }

  public int getAction() {
    return this.action;
  }

  public void setAction(int action) {
    this.action = action;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 42)
  private String address;

  private int action;

  private String cid;

  @Column(unique = true, length = 66)
  private String tx;

  private long timestamp;
}
