package cs.cs489.project.airlinebookingsystem.serviceImpl;


import cs.cs489.project.airlinebookingsystem.dao.BookingDao;
import cs.cs489.project.airlinebookingsystem.dao.PaymentDao;
import cs.cs489.project.airlinebookingsystem.dto.BookingDTO;
import cs.cs489.project.airlinebookingsystem.dto.BookingWithPaymentRequest;
import cs.cs489.project.airlinebookingsystem.model.Booking;
import cs.cs489.project.airlinebookingsystem.model.Payment;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import cs.cs489.project.airlinebookingsystem.service.BookingService;
import cs.cs489.project.airlinebookingsystem.util.BookingUtil;
import cs.cs489.project.airlinebookingsystem.util.PaymentUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;

    private final PaymentDao paymentDao;

    @Override
    @Transactional
    public BookingDTO bookTicketsWithPayment(final BookingWithPaymentRequest bookingRequest) {
        Booking booking = BookingUtil.toEntity(bookingRequest.getBooking());
        booking = bookingDao.save(booking);
        final Payment payment = PaymentUtil.toEntity(bookingRequest.getPayment());
        payment.setBooking(booking);
        paymentDao.save(payment);
        log.info("BOOKING {}", booking);
        return BookingUtil.toDTO(booking);
    }

    @Override
    public BookingDTO findBooking(String bookingCode) {
        return BookingUtil.toDTO(bookingDao.findBookingByBookingCode(bookingCode));
    }

    @Override
    public boolean hasBooking(Long scheduledFlightId) {
        return bookingDao.existsBookingByScheduledFlight(ScheduledFlight.builder().scheduleFlightId(scheduledFlightId).build());
    }
}
