package cs.cs489.project.airlinebookingsystem.adapterObjects;


import cs.cs489.project.airlinebookingsystem.dto.AirportDTO;
import cs.cs489.project.airlinebookingsystem.model.Airport;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;

import java.util.stream.StreamSupport;


@UtilityClass
public class AirportAdapter {

    public static AirportDTO toAirportDTO(@NonNull final Airport airport) {
        return AirportDTO.builder()
                .code(airport.getCode())
                .name(airport.getName())
                .location(airport.getLocation())
                .build();
    }

    public static Airport transferFromDTOtoEntity(@NonNull final AirportDTO airportDTO, @NonNull final Airport airport) {
        airport.setName(airportDTO.getName());
        airport.setLocation(airportDTO.getLocation());
        return airport;
    }

    public static Airport toAirport(@NonNull final AirportDTO airportDTO) {
        final Airport airport = new Airport();
        airport.setCode(airportDTO.getCode());
        airport.setName(airportDTO.getName());
        airport.setLocation(airportDTO.getLocation());

        return airport;
    }

    public static Collection<AirportDTO> airportDTOs(@NonNull Iterable<Airport> airports) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(airports.iterator(), Spliterator.ORDERED), false)
                .map(AirportAdapter::toAirportDTO)
                .toList();
    }
}
