package cs.cs489.project.airlinebookingsystem.util;


import cs.cs489.project.airlinebookingsystem.dto.PaymentDTO;
import cs.cs489.project.airlinebookingsystem.model.Payment;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@UtilityClass
public class PaymentUtil {

    public static PaymentDTO toDTO(final Payment payment) {
        return PaymentDTO.builder()
                .bookingUserEmail(payment.getBookingUserEmail())
                .bookingUserFullname(payment.getBookingUserFullname())
                .last4DigitPaymentCard(payment.getLast4DigitPaymentCard())
                .paymentCode(payment.getPaymentCode())
                .totalPrice(payment.getTotalPrice())
                .build();
    }

    public static Collection<PaymentDTO> toDTOs(final Iterable<Payment> payments) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(payments.iterator(), Spliterator.ORDERED), false)
                .map(PaymentUtil::toDTO)
                .toList();
    }

    public static Payment toEntity(final PaymentDTO paymentDTO) {
        return Payment.builder()
                .paymentCode(CodeUtil.buildRandomCode())
                .bookingUserFullname(paymentDTO.getBookingUserFullname())
                .bookingUserEmail(paymentDTO.getBookingUserEmail())
                .last4DigitPaymentCard(paymentDTO.getLast4DigitPaymentCard())
                .totalPrice(paymentDTO.getTotalPrice())
                .build();
    }

}
