package cs.cs489.project.airlinebookingsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_code", unique = true, nullable = false)
    private String bookingCode;
    private OffsetDateTime bookingDate;
    private int noOfPassengers;

    @OneToMany(mappedBy = "booking", cascade = {CascadeType.PERSIST})
    @NotEmpty
    private Collection<Passenger> passengers;

    @ManyToOne
    private ScheduledFlight scheduledFlight;

}
