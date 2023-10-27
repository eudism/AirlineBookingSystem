package cs.cs489.project.airlinebookingsystem.service;


import cs.cs489.project.airlinebookingsystem.dto.FlightDTO;
import cs.cs489.project.airlinebookingsystem.model.Flight;

public interface FlightService {
	public void addFlight(FlightDTO flight);

	public Iterable<Flight> viewAllFlight();

	public Flight viewFlight(String flightNumber);

	public Flight modifyFlight(FlightDTO flight);

	public void removeFlight(String flightNumber);

}
