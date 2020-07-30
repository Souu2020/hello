package com.definesys.springboot.pojo;


import com.definesys.mpaas.query.annotation.Column;
import com.definesys.mpaas.query.annotation.SystemColumn;
import com.definesys.mpaas.query.annotation.SystemColumnType;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class Employee {

  private String employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private Date hireDate;
  private String jobId;
  private String salary;
  private String commissionPct;
  private String managerId;
  private String departmentId;

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

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }


  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }


  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }


  public String getCommissionPct() {
    return commissionPct;
  }

  public void setCommissionPct(String commissionPct) {
    this.commissionPct = commissionPct;
  }


  public String getManagerId() {
    return managerId;
  }

  public void setManagerId(String managerId) {
    this.managerId = managerId;
  }


  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }

}
