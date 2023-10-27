package cs.cs489.project.airlinebookingsystem.serviceImpl;


import cs.cs489.project.airlinebookingsystem.dao.AirportDao;
import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordAlreadyPresentException;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.Airport;
import cs.cs489.project.airlinebookingsystem.service.AirportService;
import cs.cs489.project.airlinebookingsystem.util.AirportUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {

	private final AirportDao airportDao;

	public Collection<AirportDTO> viewAllAirport() {
		return AirportUtil.airportDTOs(airportDao.findAll());
	}

	public Airport viewAirport(String airportCode) {
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		} else
			throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	}

	@Override
	public void addAirport(AirportDTO airportDTO) {
		Optional<Airport> maybeAirport = airportDao.findById(airportDTO.getCode());
		if (maybeAirport.isPresent()) {
			throw new RecordAlreadyPresentException(
					"Airport with code : " + airportDTO.getCode() + " already present");
		} else {
			airportDao.save(AirportUtil.toAirport(airportDTO));
		}
	}

	@Override
	public Airport modifyAirport(AirportDTO airportDTO, String code) {
		Optional<Airport> maybeAirport = airportDao.findById(airportDTO.getCode());
		return maybeAirport.map(airport -> airportDao.save(AirportUtil.transferFromDTOtoEntity(airportDTO, airport)))
				.orElseThrow(() -> new RecordNotFoundException("Airport with code: " + airportDTO.getCode() + " not exists"));
	}

	public void removeAirport(String airportCode) {
		Optional<Airport> findById = airportDao.findById(airportCode);
		if (findById.isPresent()) {
			airportDao.deleteById(airportCode);
		} else
			throw new RecordNotFoundException("Airport with code: " + airportCode + " not exists");

	}
}
