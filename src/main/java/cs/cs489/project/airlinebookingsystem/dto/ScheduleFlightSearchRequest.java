package cs.cs489.project.airlinebookingsystem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleFlightSearchRequest {
    private String departureAirportCode;
    private String arrivalAirportCode;
    private String departureDate;
    private Short noOfPassengers;
}
