package cs.cs489.project.airlinebookingsystem.serviceImpl;

import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.exception.RecordAlreadyPresentException;
import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
import cs.cs489.project.airlinebookingsystem.model.Airport;
import cs.cs489.project.airlinebookingsystem.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AirportServiceImplTest {
    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportServiceImpl airportService;

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
    @Transactional
    public void testViewAllAirportsIntegration() {

        // Act
        Collection<AirportDTO> airports = airportService.viewAllAirport();

        // Assert
        assertNotNull(airports);
        assertTrue(airports.isEmpty());
        // Add more specific assertions based on your expected data
    }


    @Test
    void viewAirport() {
        String airportCode = "DES";
        Airport airport = Airport.builder()
                .code(airportCode)
                .name("Desmoine Airport")
                .build();

        when(airportRepository.findById(airportCode)).thenReturn(Optional.of(airport));

        // Act
        Airport result = airportService.viewAirport(airportCode);

        // Assert
        assertThat(result.getCode(), equalTo(airportCode));
        assertThat(result.getName(), equalTo("Desmoine Airport"));
    }


    @Test
    void testAddAirportThrowsExceptionWhenAirportExists() {
        // Arrange

        AirportDTO airportDTO = AirportDTO.builder().code("CODE1").name("Airport 1").build();
        when(airportRepository.findById(airportDTO.getCode())).thenReturn(Optional.of(Airport.builder().build()));

        // Act & Assert
        assertThrows(RecordAlreadyPresentException.class, () -> airportService.addAirport(airportDTO));
    }

    @Test
    void modifyAirport() {
    }

    @Test
    void removeAirport() {
        String airportCode = "CODE1";
        Airport existingAirport = Airport.builder().code(airportCode).name("Airport 1").build();
        when(airportRepository.findById(airportCode)).thenReturn(Optional.of(existingAirport));

        // Act
        airportService.removeAirport(airportCode);

        // Assert
        verify(airportRepository, times(1)).deleteById(airportCode);
    }

    @Test
    @Transactional
    @DirtiesContext
    public void testRemoveAirportIntegration() {
        // Arrange
        String airportCode = "CDA";
        // Assuming an airport with CODE1 exists in the database
        // You may need to save an airport with CODE1 in the setup

        airportService.removeAirport(airportCode);

        // Verify the airport is removed from the database
        Optional<Airport> removedAirportOptional = airportRepository.findById(airportCode);
        assertFalse(removedAirportOptional.isPresent());
    }



    @Test
    void testRemoveAirportThrowsExceptionWhenAirportNotExists() {
        // Arrange
        String airportCode = "CDA";
        when(airportRepository.findById(airportCode)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> airportService.removeAirport(airportCode));
    }

    @Test
    void testAddAirportIntegration() {
        // Arrange
        String airportCode = "CODE1";
        AirportDTO airportDTO = AirportDTO.builder()
                .code("SEA")
                .name("Airport 1")
                .location("chicago")
                .build();

        airportService.addAirport(airportDTO);

        Airport savedAirport = airportRepository.findById(airportCode)
                .orElseThrow(() -> new RuntimeException("Airport not found in the database"));

        assertNotNull(savedAirport);
        assertEquals(airportCode, savedAirport.getCode());
        assertEquals("Airport 1", savedAirport.getName());
    }


}