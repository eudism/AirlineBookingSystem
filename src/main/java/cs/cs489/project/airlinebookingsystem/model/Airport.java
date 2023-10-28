package cs.cs489.project.airlinebookingsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    private String code;
    private String location;
    private String name;

}
