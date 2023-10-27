package cs.cs489.project.airlinebookingsystem.util;


import cs.cs489.project.airlinebookingsystem.dto.BookingDTO;
import cs.cs489.project.airlinebookingsystem.model.Booking;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class BookingUtil {

    public static BookingDTO toDTO(final Booking booking) {
        return BookingDTO.builder()
                .bookingCode(booking.getBookingCode())
                .bookingDate(booking.getBookingDate().toString())
                .noOfPassengers(booking.getNoOfPassengers())
                .scheduledFlight(ScheduledFlightUtil.toScheduledFlightDTO(booking.getScheduledFlight()))
                .passengers(PassengerUtil.toDTOs(booking.getPassengers()))
                .build();
    }

    public static Booking toEntity(final BookingDTO bookingDTO) {
        return Booking.builder()
                .bookingCode(CodeUtil.buildRandomCode())
                .bookingDate(LocalDateTime.parse(bookingDTO.getBookingDate()).atOffset(ZoneOffset.UTC))
                .noOfPassengers(bookingDTO.getNoOfPassengers())
                .scheduledFlight(ScheduledFlight.builder().scheduleFlightId(bookingDTO.getScheduledFlight().getScheduleFlightId()).build())
                .passengers(PassengerUtil.toEntities(bookingDTO.getPassengers()))
                .build();
    }


}
