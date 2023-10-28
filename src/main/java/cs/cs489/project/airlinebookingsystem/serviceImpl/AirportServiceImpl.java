package cs.cs489.project.airlinebookingsystem.serviceImpl;


import cs.cs489.project.airlinebookingsystem.repository.AirportRepository;
import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordAlreadyPresentException;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.Airport;
import cs.cs489.project.airlinebookingsystem.service.AirportService;
import cs.cs489.project.airlinebookingsystem.adapterObjects.AirportAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {

	private final AirportRepository airportRepository;

	public Collection<AirportDTO> viewAllAirport() {
		return AirportAdapter.airportDTOs(airportRepository.findAll());
	}

	public Airport viewAirport(String airportCode) {
		Optional<Airport> findById = airportRepository.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		} else
			throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	}

	@Override
	public void addAirport(AirportDTO airportDTO) {
		Optional<Airport> maybeAirport = airportRepository.findById(airportDTO.getCode());
		if (maybeAirport.isPresent()) {
			throw new RecordAlreadyPresentException(
					"Airport with code : " + airportDTO.getCode() + " already present");
		} else {
			airportRepository.save(AirportAdapter.toAirport(airportDTO));
		}
	}

	@Override
	public Airport modifyAirport(AirportDTO airportDTO, String code) {
		Optional<Airport> maybeAirport = airportRepository.findById(airportDTO.getCode());
		return maybeAirport.map(airport -> airportRepository.save(AirportAdapter.transferFromDTOtoEntity(airportDTO, airport)))
				.orElseThrow(() -> new RecordNotFoundException("Airport with code: " + airportDTO.getCode() + " not exists"));
	}

	public void removeAirport(String airportCode) {
		Optional<Airport> findById = airportRepository.findById(airportCode);
		if (findById.isPresent()) {
			airportRepository.deleteById(airportCode);
		} else
			throw new RecordNotFoundException("Airport with code: " + airportCode + " not exists");

	}
}
