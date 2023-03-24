package com.pet.project001.model;

import com.alibaba.excel.annotation.ExcelProperty;

import java.time.LocalDate;

public class Kennels {
    @ExcelProperty("商户编号")
    private String kId;
    @ExcelProperty("商店名字")
    private String kName;
    @ExcelProperty("商户姓名")
    private String kPic;
    @ExcelProperty("商店创建日期")
    private LocalDate kDC;
    @ExcelProperty("商店类型")
    private String kType;
    @ExcelProperty("商户电话")
    private String kPhone;
    @ExcelProperty("商户邮箱")
    private String kEmail;
    @ExcelProperty("商户地址")
    private String kAddress;
    @ExcelProperty("备注")
    private String note;

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public String getkPic() {
        return kPic;
    }

    public void setkPic(String kPic) {
        this.kPic = kPic;
    }

    public LocalDate getkDC() {
        return kDC;
    }

    public void setkDC(LocalDate kDC) {
        this.kDC = kDC;
    }

    public String getkType() {
        return kType;
    }

    public void setkType(String kType) {
        this.kType = kType;
    }

    public String getkPhone() {
        return kPhone;
    }

    public void setkPhone(String kPhone) {
        this.kPhone = kPhone;
    }

    public String getkEmail() {
        return kEmail;
    }

    public void setkEmail(String kEmail) {
        this.kEmail = kEmail;
    }

    public String getkAddress() {
        return kAddress;
    }

    public void setkAddress(String kAddress) {
        this.kAddress = kAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
