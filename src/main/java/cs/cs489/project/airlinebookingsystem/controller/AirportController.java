package cs.cs489.project.airlinebookingsystem.controller;


import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.dto.AirportsDTO;
import cs.cs489.project.airlinebookingsystem.service.AirportService;
import cs.cs489.project.airlinebookingsystem.util.AirportUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/airport")
@AllArgsConstructor
public class AirportController {
	private final AirportService airportService;

	@GetMapping("/{code}")
	public ResponseEntity<AirportDTO> getAirport(@PathVariable String code) {
		return ResponseEntity.ok(AirportUtil.toAirportDTO(airportService.viewAirport(code)));
	}

	@GetMapping
	public ResponseEntity<?> viewAllAirport() {
		return ResponseEntity.ok(AirportsDTO.builder()
				.airports(airportService.viewAllAirport())
				.build());
	}

	@PostMapping
	public ResponseEntity<?> addAirport(@RequestBody AirportDTO airport) {
		airportService.addAirport(airport);
		return ResponseEntity.ok(airport);
	}

	@PutMapping("/{code}")
	public ResponseEntity<?> modifyAirport(@RequestBody AirportDTO airport, @PathVariable String code) {
		airportService.modifyAirport(airport, code);
		return ResponseEntity.ok(airport);
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<?> removeAirport(@PathVariable String code) {
		airportService.removeAirport(code);
		return ResponseEntity.ok().build();
	}
}
