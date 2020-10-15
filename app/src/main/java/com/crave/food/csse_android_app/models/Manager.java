package com.crave.food.csse_android_app.models;

public class Manager extends User
{
    private String companyId,email,managerName,password;
    private long managerId;

    public Manager()
    {

    }
    public Manager(String companyId,String email,String managerName,String password,long managerId)
    {
        this.companyId = companyId;
        this.email = email;
        this.managerName = managerName;
        this.password = password;
        this.managerId = managerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }
}
