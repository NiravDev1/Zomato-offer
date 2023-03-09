package com.example.task2pizza.Auth;

public class CustomerModel {
    private String  CustomerName,CustomerEmail,CustomerPassword,CutstomerId;

    public CustomerModel(String customerName, String customerEmail, String customerPassword, String cutstomerId) {
        CustomerName = customerName;
        CustomerEmail = customerEmail;
        CustomerPassword = customerPassword;
        CutstomerId = cutstomerId;
    }

    public CustomerModel() {
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return CustomerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        CustomerPassword = customerPassword;
    }

    public String getCutstomerId() {
        return CutstomerId;
    }

    public void setCutstomerId(String cutstomerId) {
        CutstomerId = cutstomerId;
    }
}
