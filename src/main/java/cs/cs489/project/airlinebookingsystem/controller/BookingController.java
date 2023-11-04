package cs.cs489.project.airlinebookingsystem.controller;


import cs.cs489.project.airlinebookingsystem.adapterObjects.AirportAdapter;
import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.dto.BookingDTO;
import cs.cs489.project.airlinebookingsystem.dto.BookingWithPaymentRequest;
import cs.cs489.project.airlinebookingsystem.dto.SeatReservation;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.service.BookingService;
import cs.cs489.project.airlinebookingsystem.service.ScheduledFlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/airline/booking")
@AllArgsConstructor
public class BookingController {

	private final BookingService bookingService;
	private final ScheduledFlightService scheduledFlightService;
	@PostMapping
	public ResponseEntity<?> addBooking(@RequestBody BookingWithPaymentRequest newBooking) {
		return ResponseEntity.ok(bookingService.bookTicketsWithPayment(newBooking));
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> searchBookingByCode(@PathVariable String code) {
		try {
			BookingDTO bookingDTO = bookingService.findBooking(code);
			if (bookingDTO != null) {
				return ResponseEntity.ok(bookingDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking code does not exist");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred: " + e.getMessage());
		}
	}


	@PostMapping("/reserveSeats")
	public ResponseEntity<String> reserveSeats(@RequestBody SeatReservation seatReservation) {
		try {
			scheduledFlightService.reserveSeats(seatReservation.getScheduledFlightId(), seatReservation.getNumberOfSeats());
			return ResponseEntity.ok("Seats successfully reserved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reserve seats: " + e.getMessage());
		}
	}
}
