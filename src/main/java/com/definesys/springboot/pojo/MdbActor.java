package com.definesys.springboot.pojo;

import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@Table(value = "MDB_ACTOR")
public class MdbActor extends MpaasBasePojo {
  @RowID(sequence = "MDB_ACTOR_S", type = RowIDType.AUTO)
  private String id;

  private String name;
  private String pinyin;
  private Date birthday;
  private String town;
  private String sex;
  private String description;
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

  public void setCreationDate(Date creationDate) {
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

  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

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


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPinyin() {
    return pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }


  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }


  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }




}
