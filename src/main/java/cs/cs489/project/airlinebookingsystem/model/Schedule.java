package cs.cs489.project.airlinebookingsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    private Airport srcAirport;

    @ManyToOne
    private Airport dstnAirport;

    @Column(name = "departure_date")
    private OffsetDateTime deptDateTime;

    @Column(name = "arrival_date")
    private OffsetDateTime arrDateTime;

}
