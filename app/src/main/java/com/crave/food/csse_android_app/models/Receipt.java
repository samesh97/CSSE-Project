package com.crave.food.csse_android_app.models;

public class Receipt {

    private String receiptID;
    private String companyName;
    private String RefNo;
    private String prduct;
    private String qty;
    private String Description;

    public Receipt() {

    }

    public void Receipt(){

    }

    public Receipt(String receiptID, String companyName, String refNo, String prduct, String qty, String description) {
        this.receiptID = receiptID;
        this.companyName = companyName;
        RefNo = refNo;
        this.prduct = prduct;
        this.qty = qty;
        Description = description;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public String getPrduct() {
        return prduct;
    }

    public void setPrduct(String prduct) {
        this.prduct = prduct;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
