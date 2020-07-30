package com.definesys.springboot.pojo;

import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@Table(value = "MDB_MOVIE")
public class MdbMovie extends MpaasBasePojo {
  @RowID(sequence = "MDB_MOVIE_S", type = RowIDType.AUTO)
  private String id;
  private String name;
  private Date showDate;
  private String star;
  private String type;
  private String national;
  private String language;
  private String avatar;

  @SystemColumn(SystemColumnType.OBJECT_VERSION)
  @Column(value = "object_version_number")
  private Integer objectVersionNumber;

  @SystemColumn(SystemColumnType.CREATE_BY)
  @Column(value = "created_by")
  private String createdBy;

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

  @JsonSerialize(using = MpaasDateSerializer.class)
  @JsonDeserialize(using = MpaasDateDeserializer.class)
  @SystemColumn(SystemColumnType.CREATE_ON)
  @Column(value = "creation_date")
  private Date creationDate;

  @SystemColumn(SystemColumnType.LASTUPDATE_BY)
  @Column(value = "last_updated_by")
  private String lastUpdatedBy;

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


  public Date getShowDate() {
    return showDate;
  }

  public void setShowDate(Date showDate) {
    this.showDate = showDate;
  }


  public String getStar() {
    return star;
  }

  public void setStar(String star) {
    this.star = star;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getNational() {
    return national;
  }

  public void setNational(String national) {
    this.national = national;
  }


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }



}
