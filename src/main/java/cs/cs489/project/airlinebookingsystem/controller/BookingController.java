package cs.cs489.project.airlinebookingsystem.controller;


import cs.cs489.project.airlinebookingsystem.dto.BookingWithPaymentRequest;
import cs.cs489.project.airlinebookingsystem.dto.SeatReservation;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.service.BookingService;
import cs.cs489.project.airlinebookingsystem.service.ScheduledFlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
public class BookingController {

	private final BookingService bookingService;
	private final ScheduledFlightService scheduledFlightService;
	@PostMapping
	public ResponseEntity<?> addBooking(@RequestBody BookingWithPaymentRequest newBooking) {
		return ResponseEntity.ok(bookingService.bookTicketsWithPayment(newBooking));
	}

	@GetMapping("/search")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchBookingByCode(@RequestParam("code") String bookingCode) {
		return ResponseEntity.ok(bookingService.findBooking(bookingCode));
	}

	@PostMapping("/reserveSeats")
	public ResponseEntity<?> reserveSeats(@RequestBody SeatReservation seatReservation) {
		scheduledFlightService.reserveSeats(seatReservation.getScheduledFlightId(), seatReservation.getNumberOfSeats());
		return ResponseEntity.ok().build();
	}

}
