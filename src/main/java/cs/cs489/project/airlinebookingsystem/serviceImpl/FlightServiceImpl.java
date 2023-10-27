package cs.cs489.project.airlinebookingsystem.serviceImpl;


import cs.cs489.project.airlinebookingsystem.dao.FlightDao;
import cs.cs489.project.airlinebookingsystem.dto.FlightDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordAlreadyPresentException;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.Flight;
import cs.cs489.project.airlinebookingsystem.service.FlightService;
import cs.cs489.project.airlinebookingsystem.util.FlightUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightDao flightDao;

	/*
	 * add a flight
	 */
	public void addFlight(FlightDTO flightDto) {
		Optional<Flight> maybeFlight = flightDao.findById(flightDto.getFlightNo());
		if (maybeFlight.isPresent()) {
			throw new RecordAlreadyPresentException("Flight with number: " + flightDto.getFlightNo() + " already present");
		} else {
			flightDao.save(FlightUtil.toFlight(flightDto));
		}
	}

	/*
	 * view all flights
	 */
	public Iterable<Flight> viewAllFlight() {
		return flightDao.findAll();
	}

	/*
	 * search a flight
	 */
	public Flight viewFlight(String flightNumber) {
		Optional<Flight> findById = flightDao.findById(flightNumber);
		if (findById.isPresent()) {
			return findById.get();
		} else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");
	}

	@Override
	public Flight modifyFlight(FlightDTO flightDto) {
		Optional<Flight> maybeFlight = flightDao.findById(flightDto.getFlightNo());
		if (maybeFlight.isPresent()) {
			return flightDao.save(FlightUtil.transferFromDTOtoEntity(flightDto, maybeFlight.get()));
		} else
			throw new RecordNotFoundException("Flight with number: " + flightDto.getFlightNo() + " not exists");
	}

	/*
	 * modify a flight
	 */
	public void modifyFlight(Flight flight) {

	}

	/*
	 * remove a flight
	 */
	public void removeFlight(String flightNumber) {
		Optional<Flight> findById = flightDao.findById(flightNumber);
		if (findById.isPresent()) {
			flightDao.deleteById(flightNumber);
		} else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");

	}
}
