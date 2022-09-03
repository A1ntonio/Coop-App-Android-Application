package com.puulapp.coop_agent;

public class CustomerSpacecraft {
    String fname, address, phone, account, amount, branch, sex;

    public CustomerSpacecraft(String fname, String address, String phone, String account, String amount, String branch, String sex) {
        this.fname = fname;
        this.address = address;
        this.phone = phone;
        this.account = account;
        this.amount = amount;
        this.branch = branch;
        this.sex = sex;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
