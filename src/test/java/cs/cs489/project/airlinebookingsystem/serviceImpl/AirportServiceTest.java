package cs.cs489.project.airlinebookingsystem.serviceImpl;

import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordAlreadyPresentException;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.Airport;
import cs.cs489.project.airlinebookingsystem.repository.AirportRepository;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AirportServiceTest {
    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

//    @BeforeEach
//    public void setUp() {
//        AirportDTO airport = new AirportDTO().builder()
//                .code("CDA")
//                .name("Airport1")
//                .location("Antlanta").build();
//        airportService.addAirport(airport);
//    }


    @Test
    void viewAllAirport() {
        Airport airport1 = Airport.builder().code("CODE1").name("Airport 1").build();
        Airport airport2 = Airport.builder().code("CODE2").name("Airport 2").build();
        when(airportRepository.findAll()).thenReturn(List.of(airport1, airport2));

        Collection<AirportDTO> airportDTOs = airportService.viewAllAirport();

        assertThat(airportDTOs, hasSize(2));
        assertThat(airportDTOs, containsInAnyOrder(
                AirportDTO.builder().code("CODE1").name("Airport 1").build(),
                AirportDTO.builder().code("CODE2").name("Airport 2").build()
        ));

    }

    @Test
    void viewAirport() {
        String airportCode = "DES";
        Airport airport = Airport.builder()
                .code(airportCode)
                .name("Desmoine Airport")
                .build();

        when(airportRepository.findById(airportCode)).thenReturn(Optional.of(airport));

        Airport result = airportService.viewAirport(airportCode);

        // Assert
        assertThat(result.getCode(), equalTo(airportCode));
        assertThat(result.getName(), equalTo("Desmoine Airport"));
    }


    @Test
    void testAddAirportThrowsExceptionWhenAirportExists() {

        AirportDTO airportDTO = AirportDTO.builder().code("CODE1").name("Airport 1").build();
        when(airportRepository.findById(airportDTO.getCode())).thenReturn(Optional.of(Airport.builder().build()));

        assertThrows(RecordAlreadyPresentException.class, () -> airportService.addAirport(airportDTO));
    }

    @Test
    void testRemoveAirportThrowsExceptionWhenAirportNotExists() {

        String airportCode = "CDA";
        when(airportRepository.findById(airportCode)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> airportService.removeAirport(airportCode));
    }

    @Test
    public void testViewAllAirportsIntegration() {

        Collection<AirportDTO> airports = airportService.viewAllAirport();

        assertNotNull(airports);
        assertTrue(airports.isEmpty());

    }

//    @Test
//    void testAddAirportIntegration() {
//
//        String airportCode = "CODE1";
//        AirportDTO airportDTO = AirportDTO.builder()
//                .code(airportCode)
//                .name("Airport 1")
//                .location("chicago")
//                .build();
//        airportService.addAirport(airportDTO);
//
//        Airport savedAirport = airportRepository.findById(airportCode)
//             .orElseThrow(() -> new RuntimeException("Airport not found in the database"));
//
//        assertNotNull(savedAirport);
//        assertEquals(airportCode, savedAirport.getCode());
//        assertEquals("Airport 1", savedAirport.getName());
//    }
////
//    @Test
////    @Transactional
////    @DirtiesContext
//    public void testRemoveAirportIntegration() {
//
//        String airportCode = "CDA";
//
//
//        airportService.removeAirport(airportCode);
//
//        // Verify the airport is removed from the database
//        Optional<Airport> removedAirportOptional = airportRepository.findById(airportCode);
//        assertFalse(removedAirportOptional.isPresent());
//    }


}