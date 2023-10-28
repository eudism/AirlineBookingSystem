package cs.cs489.project.airlinebookingsystem.adapterObjects;


import cs.cs489.project.airlinebookingsystem.dto.ScheduleDTO;
import cs.cs489.project.airlinebookingsystem.model.Airport;
import cs.cs489.project.airlinebookingsystem.model.Schedule;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

@UtilityClass
public class ScheduleAdapter {

    public static ScheduleDTO toScheduleDTO(@NonNull final Schedule schedule) {
        return ScheduleDTO.builder()
                .arrDateTime(schedule.getArrDateTime())
                .deptDateTime(schedule.getDeptDateTime())
                .dstnAirport(AirportAdapter.toAirportDTO(schedule.getDstnAirport()))
                .srcAirport(AirportAdapter.toAirportDTO(schedule.getSrcAirport()))
                .build();
    }

    public static Schedule toSchedule(final ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .arrDateTime(scheduleDTO.getArrDateTime())
                .deptDateTime(scheduleDTO.getDeptDateTime())
                .dstnAirport(Airport.builder().code(scheduleDTO.getDstnAirport().getCode()).build())
                .srcAirport(Airport.builder().code(scheduleDTO.getSrcAirport().getCode()).build())
                .build();
    }
}
