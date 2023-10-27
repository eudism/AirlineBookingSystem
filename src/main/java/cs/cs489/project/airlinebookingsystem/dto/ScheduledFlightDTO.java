package cs.cs489.project.airlinebookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledFlightDTO {
    private Long scheduleFlightId;

    private FlightDTO flight;

    private Integer availableSeats;

    private ScheduleDTO schedule;

    private String economicPrice;
}
