package com.definesys.springboot.pojo;


import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.RowId;
import java.util.Date;
@Table(value = "mdb_user")
public class MdbUser extends MpaasBasePojo {
  @RowID(sequence = "MDB_USER_S", type = RowIDType.AUTO)
  private String id;

  private String account;
  private String phone;
  private String nickname;
  private String password;
  private String role;
  private String avatar;

  @SystemColumn(SystemColumnType.OBJECT_VERSION)
  @Column(value = "object_version_number")
  private Integer objectVersionNumber;

  @SystemColumn(SystemColumnType.CREATE_BY)
  @Column(value = "created_by")
  private String createdBy;

  @JsonSerialize(using = MpaasDateSerializer.class)
  @JsonDeserialize(using = MpaasDateDeserializer.class)
  @SystemColumn(SystemColumnType.CREATE_ON)
  @Column(value = "creation_date")
  private Date creationDate;

  @SystemColumn(SystemColumnType.LASTUPDATE_BY)
  @Column(value = "last_updated_by")
  private String lastUpdatedBy;

  @JsonSerialize(using = MpaasDateSerializer.class)
  @JsonDeserialize(using = MpaasDateDeserializer.class)
  @SystemColumn(SystemColumnType.LASTUPDATE_ON)
  @Column(value = "last_update_date")
  private Date lastUpdateDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }


  public Integer getObjectVersionNumber() {
    return objectVersionNumber;
  }

  public void setObjectVersionNumber(Integer objectVersionNumber) {
    this.objectVersionNumber = objectVersionNumber;
  }


  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }


  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(java.sql.Date creationDate) {
    this.creationDate = creationDate;
  }


  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }


  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public void setLastUpdateDate(java.sql.Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

}
