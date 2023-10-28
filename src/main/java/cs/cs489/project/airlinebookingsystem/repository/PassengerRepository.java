package cs.cs489.project.airlinebookingsystem.repository;


import cs.cs489.project.airlinebookingsystem.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
