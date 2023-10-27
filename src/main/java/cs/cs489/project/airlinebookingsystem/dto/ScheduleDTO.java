package cs.cs489.project.airlinebookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private AirportDTO srcAirport;

    private AirportDTO dstnAirport;

    private OffsetDateTime deptDateTime;

    private OffsetDateTime arrDateTime;
}
