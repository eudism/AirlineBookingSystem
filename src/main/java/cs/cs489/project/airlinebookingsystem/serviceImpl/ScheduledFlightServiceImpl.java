package cs.cs489.project.airlinebookingsystem.serviceImpl;


import cs.cs489.project.airlinebookingsystem.dao.AirportDao;
import cs.cs489.project.airlinebookingsystem.dao.FlightDao;
import cs.cs489.project.airlinebookingsystem.dao.ScheduleDao;
import cs.cs489.project.airlinebookingsystem.dao.ScheduledFlightDao;
import cs.cs489.project.airlinebookingsystem.dto.ScheduledFlightDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.exception.ScheduledFlightAlreadyBookedException;
import cs.cs489.project.airlinebookingsystem.exception.ScheduledFlightNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import cs.cs489.project.airlinebookingsystem.service.BookingService;
import cs.cs489.project.airlinebookingsystem.service.ScheduledFlightService;
import cs.cs489.project.airlinebookingsystem.util.ScheduledFlightUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class ScheduledFlightServiceImpl implements ScheduledFlightService {

	/*
	 * Creating DAO object
	 */
	@Autowired
	ScheduledFlightDao dao;

	@Autowired
	ScheduleDao scheduleDao;

	@Autowired
	FlightDao flightDao;

	@Autowired
	AirportDao airportDao;

	@Autowired
	BookingService bookingService;

	/*
	 * Service method to add new Scheduled flight to database
	 */
	@Override
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
		return dao.save(scheduledFlight);
	}


	@Override
	@Transactional
	public ScheduledFlight modifyScheduledFlight(@NonNull final ScheduledFlightDTO scheduledFlightDto) {
		if (this.bookingService.hasBooking(scheduledFlightDto.getScheduleFlightId())) {
			throw new ScheduledFlightAlreadyBookedException("Scheduled flight has been booked");
		}
		if (!this.scheduleDao.existsById(scheduledFlightDto.getScheduleFlightId())) {
			throw new ScheduledFlightNotFoundException("No scheduled flight found for modification");
		}
		return dao.findById(scheduledFlightDto.getScheduleFlightId())
				.map(scheduledFlight -> {
					ScheduledFlightUtil.transferDtoToEntity(scheduledFlightDto, scheduledFlight);
					scheduledFlight.setFlight(flightDao.findById(scheduledFlightDto.getFlight().getFlightNo()).orElse(null));
					scheduledFlight.getSchedule().setSrcAirport(airportDao.findById(scheduledFlightDto.getSchedule().getSrcAirport().getCode()).orElse(null));
					scheduledFlight.getSchedule().setDstnAirport(airportDao.findById(scheduledFlightDto.getSchedule().getDstnAirport().getCode()).orElse(null));
					scheduledFlight.setAvailableSeats(scheduledFlight.getFlight().getSeatCapacity());
					scheduledFlight.setTemporaryAvailableSeats(scheduledFlight.getAvailableSeats());

					return dao.save(scheduledFlight);
				}).orElse(null);

	}

	/*
	 * Service method to remove existing Scheduled flight from database
	 */
	@Override
	public String removeScheduledFlight(Long flightId) throws RecordNotFoundException {
		if (flightId == null)
			throw new RecordNotFoundException("Flight not found with ID="+ flightId);
		Optional<ScheduledFlight> scheduleFlight = dao.findById(flightId);
		if (!scheduleFlight.isPresent())
			throw new RecordNotFoundException("Flight not found with ID="+ flightId);
		else {
			dao.deleteById(flightId);
		}
		return "Scheduled flight with ID " + flightId + " is not found";
	}

	@Override
	public Iterable<ScheduledFlight> viewAllScheduledFlights() {
		return dao.findAll();
	}


	@Override
	public Collection<ScheduledFlight> viewScheduledFlights(
			LocalDate deptDateTime, String srcAirport, String dstnAirport, Short noOfPassengers) throws ScheduledFlightNotFoundException {

		Collection<ScheduledFlight> scheduledFlights = dao.fetchByTimeAndLocation(
				deptDateTime,
				dstnAirport,
				srcAirport,
				noOfPassengers

		);
		if (scheduledFlights == null || scheduledFlights.isEmpty()) {
			throw new ScheduledFlightNotFoundException("No flights being scheduled.");
		}
		return scheduledFlights;
	}

	@Override
	public void reserveSeats(final Long scheduledFlightId, final Short numberOfSeats) {
		dao.findById(scheduledFlightId)
				.ifPresent(scheduledFlight -> {
					final Integer availableSeats = scheduledFlight.getAvailableSeats() - numberOfSeats;
					if (availableSeats >= 0) {
						scheduledFlight.setTemporaryAvailableSeats(availableSeats);
						dao.save(scheduledFlight);
					}
				});
	}

}
