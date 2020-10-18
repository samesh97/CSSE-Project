package com.crave.food.csse_android_app.models;

public class Order {

    private Product product;
    private Supplier supplier;
    private String orderId,companyName,phone,dateCurrent,refNo,dateRequired,siteAddress,notes,status;
    private float priceExpected;
    private int quantity;
    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    private String unit;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrderId(){return orderId;}
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }

    public String getCompanyName(){return companyName;}
    public void setCompanyName(String companyName){
        this.companyName=companyName;
    }

    public String getPhone(){return phone;}
    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getDateCurrent(){return dateCurrent;}
    public void setDateCurrent(String dateCurrent){
        this.dateCurrent=dateCurrent;
    }

    public String getRefNo(){return refNo;}
    public void setRefNo(String refNo){this.refNo=refNo;}




    public   int getQuantity(){return quantity;}
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateRequired(){return dateRequired;}
    public void setDateRequired(String dateRequired){this.dateRequired=dateRequired;}

    public String getSiteAddress(){return siteAddress;}
    public void setSiteAddress(String siteAddress){this.siteAddress=siteAddress;}


    public float getPriceExpected() {
        return priceExpected;
    }

    public void setPriceExpected(float priceExpected) {
        this.priceExpected = priceExpected;
    }


    public String getNotes(){return notes;}
    public void setNotes(String notes){this.notes=notes;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
