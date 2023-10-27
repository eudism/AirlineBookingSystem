package cs.cs489.project.airlinebookingsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_code", unique = true, nullable = false)
    private String paymentCode;

    private String last4DigitPaymentCard;

    private String bookingUserFullname;

    private String bookingUserEmail;

    private BigDecimal totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    private Booking booking;
}
