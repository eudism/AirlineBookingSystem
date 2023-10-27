package cs.cs489.project.airlinebookingsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorResponse {

    private String message;
    private String errorStatus;
}
