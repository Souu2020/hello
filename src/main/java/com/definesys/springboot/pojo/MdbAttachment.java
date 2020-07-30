package com.definesys.springboot.pojo;

import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@Table(value = "MDB_ATTACHMENT")
public class MdbAttachment extends MpaasBasePojo {
  @RowID(sequence = "MDB_ATTACHMENT_S", type = RowIDType.AUTO)
  private String id;
  private String uuid;
  private String attachmentName;
  private String tag;
  private String docId;
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


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  public String getAttachmentName() {
    return attachmentName;
  }

  public void setAttachmentName(String attachmentName) {
    this.attachmentName = attachmentName;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public String getDocId() {
    return docId;
  }

  public void setDocId(String docId) {
    this.docId = docId;
  }



}
