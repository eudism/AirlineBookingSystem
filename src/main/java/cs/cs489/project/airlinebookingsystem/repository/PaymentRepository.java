package cs.cs489.project.airlinebookingsystem.repository;


import cs.cs489.project.airlinebookingsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
