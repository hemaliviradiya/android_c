package com.example.spinner;

public class mobile {
    public int mid;
    public String name,company;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public mobile() {
    }

    public mobile(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public mobile(int mid, String name, String company) {
        this.mid = mid;
        this.name = name;
        this.company = company;
    }
}
