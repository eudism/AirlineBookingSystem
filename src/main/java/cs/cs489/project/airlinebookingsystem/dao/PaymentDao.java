package cs.cs489.project.airlinebookingsystem.dao;


import cs.cs489.project.airlinebookingsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaymentDao extends JpaRepository<Payment, Long> {

}
