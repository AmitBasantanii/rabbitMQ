package com.rabbitmq.two.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class InvoiceCancelledMessage {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelDate;

    private String invoiceNumber;

    private String reason;

    // default constructor
    public InvoiceCancelledMessage() {

    }

    // constructor
    public InvoiceCancelledMessage(LocalDate cancelDate, String invoiceNumber, String reason) {
        this.cancelDate = cancelDate;
        this.invoiceNumber = invoiceNumber;
        this.reason = reason;
    }

    //getter and setters


    public LocalDate getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDate cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // toString method

    @Override
    public String toString() {
        return "InvoiceCancelledMessage{" +
                "cancelDate=" + cancelDate +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }


}
