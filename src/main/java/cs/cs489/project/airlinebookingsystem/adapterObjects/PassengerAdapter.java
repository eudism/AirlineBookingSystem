package cs.cs489.project.airlinebookingsystem.adapterObjects;


import cs.cs489.project.airlinebookingsystem.dto.PassengerDTO;
import cs.cs489.project.airlinebookingsystem.model.Passenger;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@UtilityClass
public class PassengerAdapter {

    public static PassengerDTO toDTO(final Passenger passenger) {
        return PassengerDTO.builder()
                .passengerName(passenger.getPassengerName())
                .dateOfBirth(passenger.getDateOfBirth())
                .luggage(passenger.getLuggage())
                .build();
    }

    public static Collection<PassengerDTO> toDTOs(final Iterable<Passenger> passengers) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(passengers.iterator(), Spliterator.ORDERED), false)
                .map(PassengerAdapter::toDTO)
                .toList();
    }

    public static Passenger toEntity(final PassengerDTO passengerDTO) {
        return Passenger.builder()
                .passengerName(passengerDTO.getPassengerName())
                .luggage(passengerDTO.getLuggage())
                .dateOfBirth(passengerDTO.getDateOfBirth())
                .build();
    }

    public static Collection<Passenger> toEntities(final Collection<PassengerDTO> passengerDTOS) {
        return passengerDTOS.stream()
                .map(PassengerAdapter::toEntity)
                .toList();
    }

    public static void transferFromDTOtoEntity(final PassengerDTO passengerDTO, final Passenger passenger) {
        passenger.setPassengerName(passengerDTO.getPassengerName());
        passenger.setLuggage(passengerDTO.getLuggage());
        passenger.setDateOfBirth(passengerDTO.getDateOfBirth());
    }

}
