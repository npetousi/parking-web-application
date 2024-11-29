package com.example.parking_web_application.dtos;

import java.math.BigDecimal;

public class PaymentDto {
    private BigDecimal paidAmount;

    public PaymentDto(){}

    public PaymentDto(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }


}
