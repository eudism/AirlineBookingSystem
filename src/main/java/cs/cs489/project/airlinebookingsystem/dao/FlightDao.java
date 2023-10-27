package cs.cs489.project.airlinebookingsystem.dao;


import cs.cs489.project.airlinebookingsystem.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FlightDao extends CrudRepository<Flight, String>{

}
