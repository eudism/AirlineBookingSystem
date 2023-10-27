package cs.cs489.project.airlinebookingsystem.service;



import cs.cs489.project.airlinebookingsystem.dto.ScheduledFlightDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.exception.ScheduledFlightNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;

import java.time.LocalDate;
import java.util.Collection;

public interface ScheduledFlightService {
	ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	ScheduledFlight modifyScheduledFlight(ScheduledFlightDTO scheduledFlightDto);

	String removeScheduledFlight(Long id) throws RecordNotFoundException;

	Iterable<ScheduledFlight> viewAllScheduledFlights();

	Collection<ScheduledFlight> viewScheduledFlights(
			final LocalDate deptDateTime,
			final String srcAirport,
			final String dstnAirport,
			final Short noOfPassengers
	) throws ScheduledFlightNotFoundException;

	void reserveSeats(final Long scheduledFlightId, final Short numberOfSeats);

}
