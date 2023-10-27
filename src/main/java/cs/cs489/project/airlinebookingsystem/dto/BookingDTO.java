package cs.cs489.project.airlinebookingsystem.dto;

import lombok.*;


import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private String bookingCode;
    private String bookingDate;
    private Integer noOfPassengers;
    private Collection<PassengerDTO> passengers;
    private ScheduledFlightDTO scheduledFlight;
}
