package cs.cs489.project.airlinebookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private String flightNo;
    private String carrierName;
    private String flightModel;
    private int seatCapacity;
}
