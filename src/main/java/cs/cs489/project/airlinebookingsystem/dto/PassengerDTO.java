package cs.cs489.project.airlinebookingsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerDTO {
    private String passengerName;

    private Date dateOfBirth;

    private Short luggage;

   // private BookingDTO booking;
}
