package cs.cs489.project.airlinebookingsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class ScheduledFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleFlightId;

    @ManyToOne
    private Flight flight;

    private Integer availableSeats;

    private Integer temporaryAvailableSeats;

    private BigDecimal economicPrice;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    private Schedule schedule;
}
