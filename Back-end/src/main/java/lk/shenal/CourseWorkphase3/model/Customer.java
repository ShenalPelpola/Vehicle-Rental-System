package lk.shenal.CourseWorkphase3.model;

import java.time.LocalDate;

public class Customer {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String telNo;
    private String licenceNo;
    private LocalDate pickUpDate;
    private LocalDate dropOfDate;
    private String plateNo;

    public Customer(String customerId, String customerName, String customerAddress, String customerEmail, String telNo, String licenceNo, LocalDate pickUpDate, LocalDate dropOfDate, String plateNo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.telNo = telNo;
        this.licenceNo = licenceNo;
        this.pickUpDate = pickUpDate;
        this.dropOfDate = dropOfDate;
        this.plateNo = plateNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOfDate() {
        return dropOfDate;
    }

    public void setDropOfDate(LocalDate dropOfDate) {
        this.dropOfDate = dropOfDate;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", telNo='" + telNo + '\'' +
                ", licenceNo='" + licenceNo + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", dropOfDate=" + dropOfDate +
                ", plateNo='" + plateNo + '\'' +
                '}';
    }
}
