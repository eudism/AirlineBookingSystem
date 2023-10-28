package cs.cs489.project.airlinebookingsystem.repository;


import cs.cs489.project.airlinebookingsystem.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {

}
