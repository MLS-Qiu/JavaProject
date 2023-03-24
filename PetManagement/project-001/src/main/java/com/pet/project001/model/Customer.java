package com.pet.project001.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class Customer {

    @ExcelProperty("客户编号")
    private String cusId;
    @ExcelProperty("客户姓名")
    private String cusName;
    @ExcelProperty("客户性别")
    private String cusSex;
    @ExcelProperty("客户年龄")
    private String cusAge;
    @ExcelProperty("客户电话")
    private String cusPhone;
    @ExcelProperty("客户邮箱")
    private String cusEmail;
    @ExcelProperty("客户生日")
    private LocalDate cusBirthday;
    @ExcelProperty("客户地址")
    private String cusAddress;
    @ExcelProperty("备注")
    private String note;

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusSex() {
        return cusSex;
    }

    public void setCusSex(String cusSex) {
        this.cusSex = cusSex;
    }

    public String getCusAge() {
        return cusAge;
    }

    public void setCusAge(String cusAge) {
        this.cusAge = cusAge;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public LocalDate getCusBirthday() {
        return cusBirthday;
    }

    public void setCusBirthday(LocalDate cusBirthday) {
        this.cusBirthday = cusBirthday;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
