package cs.cs489.project.airlinebookingsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;//change date from date to local

    private Short luggage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Booking booking;
}
