package cs.cs489.project.airlinebookingsystem.dao;


import cs.cs489.project.airlinebookingsystem.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDao extends CrudRepository<Airport, String> {

}
