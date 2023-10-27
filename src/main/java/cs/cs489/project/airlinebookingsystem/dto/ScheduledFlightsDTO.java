package cs.cs489.project.airlinebookingsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ScheduledFlightsDTO {
    private Collection<ScheduledFlightDTO> scheduledFlights;
}
