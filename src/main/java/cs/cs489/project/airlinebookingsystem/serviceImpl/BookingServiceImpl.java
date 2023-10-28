package cs.cs489.project.airlinebookingsystem.serviceImpl;


import cs.cs489.project.airlinebookingsystem.repository.BookingRepository;
import cs.cs489.project.airlinebookingsystem.repository.PaymentRepository;
import cs.cs489.project.airlinebookingsystem.dto.BookingDTO;
import cs.cs489.project.airlinebookingsystem.dto.BookingWithPaymentRequest;
import cs.cs489.project.airlinebookingsystem.model.Booking;
import cs.cs489.project.airlinebookingsystem.model.Payment;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import cs.cs489.project.airlinebookingsystem.service.BookingService;
import cs.cs489.project.airlinebookingsystem.adapterObjects.BookingAdapter;
import cs.cs489.project.airlinebookingsystem.adapterObjects.PaymentAdapter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public BookingDTO bookTicketsWithPayment(final BookingWithPaymentRequest bookingRequest) {
        Booking booking = BookingAdapter.toEntity(bookingRequest.getBooking());
        booking = bookingRepository.save(booking);
        final Payment payment = PaymentAdapter.toEntity(bookingRequest.getPayment());
        payment.setBooking(booking);
        paymentRepository.save(payment);
        log.info("BOOKING {}", booking);
        return BookingAdapter.toDTO(booking);
    }

    @Override
    public BookingDTO findBooking(String bookingCode) {
        return BookingAdapter.toDTO(bookingRepository.findBookingByBookingCode(bookingCode));
    }

    @Override
    public boolean hasBooking(Long scheduledFlightId) {
        return bookingRepository.existsBookingByScheduledFlight(ScheduledFlight.builder().scheduleFlightId(scheduledFlightId).build());
    }
}
