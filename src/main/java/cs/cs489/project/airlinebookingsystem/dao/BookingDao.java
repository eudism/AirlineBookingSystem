package cs.cs489.project.airlinebookingsystem.dao;


import cs.cs489.project.airlinebookingsystem.model.Booking;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {

    Booking findBookingByBookingCode(final String bookingCode);

    Boolean existsBookingByScheduledFlight(final ScheduledFlight scheduledFlight);
}
