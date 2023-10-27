package cs.cs489.project.airlinebookingsystem.service;


import cs.cs489.project.airlinebookingsystem.dto.BookingDTO;
import cs.cs489.project.airlinebookingsystem.dto.BookingWithPaymentRequest;

public interface BookingService {

	BookingDTO bookTicketsWithPayment(final BookingWithPaymentRequest bookingRequest);

	BookingDTO findBooking(final String bookingCode);

	boolean hasBooking(final Long scheduledFlightId);

}
