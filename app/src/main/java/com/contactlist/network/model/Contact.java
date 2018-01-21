package com.contactlist.network.model;

import android.text.TextUtils;

/**
 * Created by ankit on 21/01/18.
 */

public class Contact {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressString() {
        StringBuilder stringBuilder = new StringBuilder();
        Address address = getAddress();
        if (!TextUtils.isEmpty(address.getSuite())) {
            stringBuilder.append(address.getSuite());
            if (!TextUtils.isEmpty(address.getStreet())) {
                stringBuilder.append(", ");
            }
        }
        if (!TextUtils.isEmpty(address.getStreet())) {
            stringBuilder.append(address.getStreet());
            if (!TextUtils.isEmpty(address.getCity())) {
                stringBuilder.append(", ");
            }
        }
        if (!TextUtils.isEmpty(address.getCity())) {
            stringBuilder.append(address.getCity());
        }
        if (!TextUtils.isEmpty(address.getZipcode())) {
            stringBuilder.append("- ");
            stringBuilder.append(address.getZipcode());
        }
        stringBuilder.append(".");
        return stringBuilder.toString();
    }

    public String getCompanyString() {
        StringBuilder stringBuilder = new StringBuilder();
        Company company = getCompany();
        if (!TextUtils.isEmpty(company.getName())) {
            stringBuilder.append(company.getName());
            if (!TextUtils.isEmpty(company.getCatchPhrase())) {
                stringBuilder.append(", ");
            }
        }
        if (!TextUtils.isEmpty(company.getCatchPhrase())) {
            stringBuilder.append(company.getCatchPhrase());
            if (!TextUtils.isEmpty(company.getBs())) {
                stringBuilder.append(", ");
            }
        }
        if (!TextUtils.isEmpty(company.getBs())) {
            stringBuilder.append(company.getBs());
        }
        stringBuilder.append(".");
        return stringBuilder.toString();
    }
}
