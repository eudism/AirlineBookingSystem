package cs.cs489.project.airlinebookingsystem.repository;


import cs.cs489.project.airlinebookingsystem.model.Booking;
import cs.cs489.project.airlinebookingsystem.model.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findBookingByBookingCode(final String bookingCode);

    Boolean existsBookingByScheduledFlight(final ScheduledFlight scheduledFlight);
}
