package com.crave.food.csse_android_app.models;

public class Supplier extends User
{
    private String supplierId,supplierName, supplierEmail,  supplierAddress, supplierPhone,supplierType,supplierImage,password;

    public Supplier()
    {

    }
    public Supplier(String supplierId,String supplierName,String supplierEmail,String supplierAddress,String supplierPhone,String supplierType,String supplierImage,String password)
    {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierAddress = supplierAddress;
        this.supplierPhone = supplierPhone;
        this.supplierType = supplierType;
        this.supplierImage = supplierImage;
        this.password = password;
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierImage() {
        return supplierImage;
    }

    public void setSupplierImage(String supplierImage) {
        this.supplierImage = supplierImage;
    }

    public String getSuppliers() {
        return supplierName;
    }
}
