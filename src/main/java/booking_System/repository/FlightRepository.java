package booking_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import booking_System.entity.Flight;


public interface FlightRepository extends JpaRepository<Flight, Integer> {
	
	
	List<Flight> findByAirline(String airline);

    List<Flight> findBySourceAndDestination(String source, String destination);

}
