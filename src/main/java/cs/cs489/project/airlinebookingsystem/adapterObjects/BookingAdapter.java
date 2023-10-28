package cs.cs489.project.airlinebookingsystem.adapterObjects;


import cs.cs489.project.airlinebookingsystem.dto.BookingDTO;
import cs.cs489.project.airlinebookingsystem.model.Booking;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class BookingAdapter {

    public static BookingDTO toDTO(final Booking booking) {
        return BookingDTO.builder()
                .bookingCode(booking.getBookingCode())
                .bookingDate(booking.getBookingDate().toString())
                .noOfPassengers(booking.getNoOfPassengers())
                .scheduledFlight(ScheduledAdapter.toScheduledFlightDTO(booking.getScheduledFlight()))
                .passengers(PassengerAdapter.toDTOs(booking.getPassengers()))
                .build();
    }

    public static Booking toEntity(final BookingDTO bookingDTO) {
        return Booking.builder()
                .bookingCode(CodeAdapter.buildRandomCode())
                .bookingDate(LocalDateTime.parse(bookingDTO.getBookingDate()).atOffset(ZoneOffset.UTC))
                .noOfPassengers(bookingDTO.getNoOfPassengers())
                .scheduledFlight(ScheduledFlight.builder().scheduleFlightId(bookingDTO.getScheduledFlight().getScheduleFlightId()).build())
                .passengers(PassengerAdapter.toEntities(bookingDTO.getPassengers()))
                .build();
    }


}
