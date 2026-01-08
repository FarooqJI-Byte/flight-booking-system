package booking_System.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import booking_System.entity.Booking;
import booking_System.entity.BookingStatus;
import booking_System.entity.Flight;


public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	//by flight
	List<Booking> findByFlight(Flight flight);
	
	//get by status
	List<Booking> findByStatus(BookingStatus status);
	//get by date
	List<Booking> findByBookingDateBetween(LocalDateTime start, LocalDateTime end);




}
