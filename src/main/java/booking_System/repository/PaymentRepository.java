package booking_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import booking_System.entity.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	
	List<Payment> findByStatus(String status);

    List<Payment> findByModeOfTransaction(String modeOfTransaction);

    Payment findByBookingId(Integer bookingId);

    List<Payment> findByAmountGreaterThan(double amount);

}
