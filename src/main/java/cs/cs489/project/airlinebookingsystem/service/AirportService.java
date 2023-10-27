package cs.cs489.project.airlinebookingsystem.service;



import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.model.Airport;

import java.util.Collection;

public interface AirportService {
	public Collection<AirportDTO> viewAllAirport();

	public Airport viewAirport(String airportCode);

	public void addAirport(AirportDTO airport);

	public Airport modifyAirport(AirportDTO airport, String code);

	public void removeAirport(String airportCode);
}
