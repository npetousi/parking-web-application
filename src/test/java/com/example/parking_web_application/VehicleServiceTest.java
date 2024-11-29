package com.example.parking_web_application;

import com.example.parking_web_application.dtos.ParkingTicketDto;
import com.example.parking_web_application.dtos.PaymentDto;
import com.example.parking_web_application.dtos.PaymentRequestDto;
import com.example.parking_web_application.dtos.VehicleChangesDto;
import com.example.parking_web_application.exceptions.VehicleNotFoundException;
import com.example.parking_web_application.model.Vehicle;
import com.example.parking_web_application.repository.VehicleRepo;
import com.example.parking_web_application.service.VehicleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VehicleServiceTest {
    @Mock
    private VehicleRepo vehicleRepo;

    @InjectMocks
    private VehicleServiceImpl vehicleServiceImpl;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void calculateParkingTicketTest_SuccessCase(){

        ParkingTicketDto parkingTicketDto = new ParkingTicketDto();
        parkingTicketDto.setPlateNumber("ABC-1234");
        parkingTicketDto.setParkingTicket(new BigDecimal("10.00"));
        parkingTicketDto.setExactTimeOfCalculatingParkingTicket(LocalDateTime.now());

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaidAmount(new BigDecimal("15.00"));

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setParkingTicketDto(parkingTicketDto);
        paymentRequestDto.setPaymentDto(paymentDto);

        VehicleChangesDto result = vehicleServiceImpl.processPayment(paymentRequestDto);

        assertTrue(result.isCanProceed());
        assertEquals("Payment successful. Change amount: 5.00 euros.", result.getMessage());
        assertEquals(new BigDecimal("5.00"), result.getChanges());
        verify(vehicleRepo, times(1)).deleteById("ABC-1234");
    }

    @Test
    void calculateParkingTicketTest_ThrowException_WhenPaymentExceedsTimeLimit() {
        ParkingTicketDto parkingTicketDto = new ParkingTicketDto();
        parkingTicketDto.setPlateNumber("ABC-1234");
        parkingTicketDto.setParkingTicket(new BigDecimal("10.00"));
        parkingTicketDto.setExactTimeOfCalculatingParkingTicket(LocalDateTime.now().minusMinutes(2));

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaidAmount(new BigDecimal("15.00"));

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setParkingTicketDto(parkingTicketDto);
        paymentRequestDto.setPaymentDto(paymentDto);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                vehicleServiceImpl.processPayment(paymentRequestDto)
        );

        assertEquals("Payment must be made within 1 minute of calculating the parking ticket.", exception.getMessage());
        verify(vehicleRepo, never()).deleteById(anyString());
    }

    @Test
    void calculateParkingTicketTest_ThrowInsufficientPaymentException() {

        ParkingTicketDto parkingTicketDto = new ParkingTicketDto();
        parkingTicketDto.setPlateNumber("ABC-1234");
        parkingTicketDto.setParkingTicket(new BigDecimal("10.00"));
        parkingTicketDto.setExactTimeOfCalculatingParkingTicket(LocalDateTime.now());

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaidAmount(new BigDecimal("5.00"));

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setParkingTicketDto(parkingTicketDto);
        paymentRequestDto.setPaymentDto(paymentDto);



        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                vehicleServiceImpl.processPayment(paymentRequestDto)
        );

        assertEquals("Insufficient payment amount. Please provide more funds.", exception.getMessage());
        verify(vehicleRepo, never()).deleteById(anyString());
    }



}
