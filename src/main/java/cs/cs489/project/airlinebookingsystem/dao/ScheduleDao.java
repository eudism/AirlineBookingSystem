package cs.cs489.project.airlinebookingsystem.dao;


import cs.cs489.project.airlinebookingsystem.model.Airport;
import cs.cs489.project.airlinebookingsystem.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ScheduleDao extends CrudRepository<Schedule, Long> {

    List<Schedule> findByDeptDateTimeAndArrDateTimeAndDstnAirportAndSrcAirport(
            final OffsetDateTime deptDateTime,
            final OffsetDateTime arrDateTime,
            final Airport dstnAirport,
            final Airport srcAirport
            );

}
