package cs.cs489.project.airlinebookingsystem.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class BookingWithPaymentRequest {

    private BookingDTO booking;
    private PaymentDTO payment;
}
