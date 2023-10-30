package cs.cs489.project.airlinebookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class AirlineBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineBookingSystemApplication.class, args);
    }

}
