package cs.cs489.project.airlinebookingsystem.serviceImpl;


import cs.cs489.project.airlinebookingsystem.repository.FlightRepository;
import cs.cs489.project.airlinebookingsystem.dto.FlightDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordAlreadyPresentException;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.Flight;
import cs.cs489.project.airlinebookingsystem.service.FlightService;
import cs.cs489.project.airlinebookingsystem.adapterObjects.FlightAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository flightRepository;

	/*
	 * add a flight
	 */
	public void addFlight(FlightDTO flightDto) {
		Optional<Flight> maybeFlight = flightRepository.findById(flightDto.getFlightNo());
		if (maybeFlight.isPresent()) {
			throw new RecordAlreadyPresentException("Flight with number: " + flightDto.getFlightNo() + " already present");
		} else {
			flightRepository.save(FlightAdapter.toFlight(flightDto));
		}
	}

	/*
	 * view all flights
	 */
	public Iterable<Flight> viewAllFlight() {
		return flightRepository.findAll();
	}

	/*
	 * search a flight
	 */
	public Flight viewFlight(String flightNumber) {
		Optional<Flight> findById = flightRepository.findById(flightNumber);
		if (findById.isPresent()) {
			return findById.get();
		} else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");
	}

	/*
	 * modify a flight
	 */
	@Override
	public Flight modifyFlight(FlightDTO flightDto) {
		Optional<Flight> maybeFlight = flightRepository.findById(flightDto.getFlightNo());
		if (maybeFlight.isPresent()) {
			return flightRepository.save(FlightAdapter.transferFromDTOtoEntity(flightDto, maybeFlight.get()));
		} else
			throw new RecordNotFoundException("Flight with number: " + flightDto.getFlightNo() + " not exists");
	}



	/*
	 * remove a flight
	 */
	public void removeFlight(String flightNumber) {
		Optional<Flight> findById = flightRepository.findById(flightNumber);
		if (findById.isPresent()) {
			flightRepository.deleteById(flightNumber);
		} else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");

	}
}
