package cs.cs489.project.airlinebookingsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private String flightNo;
    private String carrierName;
    private String flightModel;
    private int seatCapacity;

}
