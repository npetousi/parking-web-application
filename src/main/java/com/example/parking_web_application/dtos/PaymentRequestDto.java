package com.example.parking_web_application.dtos;

public class PaymentRequestDto {
    private ParkingTicketDto parkingTicketDto;
    private PaymentDto paymentDto;

    public PaymentRequestDto(){}

    public PaymentRequestDto(ParkingTicketDto parkingTicketDto, PaymentDto paymentDto) {
        this.parkingTicketDto = parkingTicketDto;
        this.paymentDto = paymentDto;
    }

    public ParkingTicketDto getParkingTicketDto() {
        return parkingTicketDto;
    }

    public void setParkingTicketDto(ParkingTicketDto parkingTicketDto) {
        this.parkingTicketDto = parkingTicketDto;
    }

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }

    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }
}
