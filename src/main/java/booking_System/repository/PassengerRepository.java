package booking_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking_System.entity.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	
	
		Passenger findByContactNo(String contactNo);

	    @Query("SELECT p FROM Passenger p WHERE p.booking.flight.id = :flightId")
	    List<Passenger> findPassengersByFlightId(int flightId);

}
