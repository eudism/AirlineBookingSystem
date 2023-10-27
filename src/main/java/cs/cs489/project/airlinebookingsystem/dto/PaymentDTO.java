package cs.cs489.project.airlinebookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String paymentCode;

    private String last4DigitPaymentCard;

    private String bookingUserFullname;

    private String bookingUserEmail;

    private BigDecimal totalPrice;

    private BookingDTO booking;
}
