package com.definesys.springboot.pojo.pojo;

import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: 10976
 * @since: 2020-07-30
 * @history: 1.2020-07-30 created by 10976
 */
@Table(value = "ADDRESS_LIST")
public class AddressList extends MpaasBasePojo {

    @RowID(sequence = "ADDRESS_LIST_S", type = RowIDType.AUTO)
    private Long userid;

    private String username;

    private String userpwd;

    private String phone;

    private String email;

    private String address;

    private String mtext;


    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return this.userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMtext() {
        return this.mtext;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }
}