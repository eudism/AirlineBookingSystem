package cs.cs489.project.airlinebookingsystem.adapterObjects;


import cs.cs489.project.airlinebookingsystem.dto.ScheduledFlightDTO;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import lombok.experimental.UtilityClass;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@UtilityClass
public class ScheduledAdapter {

    public static ScheduledFlightDTO toScheduledFlightDTO(final ScheduledFlight scheduledFlight) {
        return ScheduledFlightDTO.builder()
                .scheduleFlightId(scheduledFlight.getScheduleFlightId())
                .availableSeats(scheduledFlight.getTemporaryAvailableSeats())
                .schedule(
                        Optional.ofNullable(scheduledFlight.getSchedule())
                                        .map(schedule -> ScheduleAdapter.toScheduleDTO(schedule))
                                .orElse(null)
                )
                .economicPrice(Optional.ofNullable(scheduledFlight.getEconomicPrice()).map(BigDecimal::toString).orElse(null))
                .flight(Optional.ofNullable(scheduledFlight.getFlight()).map(FlightAdapter::toFlightDTO).orElse(null)
                )
                .build();
    }

    public static Collection<ScheduledFlightDTO> toScheduledFlightDTOs(final Iterable<ScheduledFlight> scheduledFlights) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(scheduledFlights.iterator(), Spliterator.ORDERED), false)
                .map(ScheduledAdapter::toScheduledFlightDTO)
                .toList();
    }

    public static ScheduledFlight transferDtoToEntity(@NonNull final ScheduledFlightDTO scheduledFlightDTO, @NonNull final ScheduledFlight scheduledFlight) {
        scheduledFlight.getSchedule().setArrDateTime(scheduledFlightDTO.getSchedule().getArrDateTime());
        scheduledFlight.getSchedule().setDeptDateTime(scheduledFlightDTO.getSchedule().getDeptDateTime());
//        scheduledFlight.getSchedule().getSrcAirport().setCode(scheduledFlightDTO.getSchedule().getSrcAirport().getCode());
//        scheduledFlight.getSchedule().getDstnAirport().setCode(scheduledFlightDTO.getSchedule().getSrcAirport().getCode());
//
//        scheduledFlight.getFlight().setFlightNo(scheduledFlightDTO.getFlight().getFlightNo());

        scheduledFlight.setEconomicPrice(NumberUtils.createBigDecimal(scheduledFlightDTO.getEconomicPrice()));

        return scheduledFlight;
    }
}
