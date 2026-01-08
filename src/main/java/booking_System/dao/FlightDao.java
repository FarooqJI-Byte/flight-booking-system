package booking_System.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import booking_System.entity.Flight;
import booking_System.exception.FlightNotFoundException;
import booking_System.repository.FlightRepository;

import java.util.List;
import java.util.Optional;
@Repository
public class FlightDao {

    @Autowired
    private FlightRepository flightRepository;

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {

        List<Flight> flights = flightRepository.findAll();

        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights available in database");
        } else {
            return flights;
        }
    }
    public Flight getFlightById(int id) {

        Optional<Flight> optional = flightRepository.findById(id);

        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new FlightNotFoundException("Flight not found for ID: " + id);
        }
    }
    public List<Flight> getFlightBySourceAndDestination(String source, String destination) {

        List<Flight> flights = flightRepository.findBySourceAndDestination(source, destination);

        if (flights.isEmpty()) {
            throw new FlightNotFoundException(
                "No flights found from " + source + " to " + destination
            );
        } else {
            return flights;
        }
    }
    public List<Flight> getFlightByAirline(String airline) {

        List<Flight> flights = flightRepository.findByAirline(airline);

        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights found for airline: " + airline);
        } else {
            return flights;
        }
    }
    public Flight updateFlight(int id, Flight flight) {

        Optional<Flight> existing = flightRepository.findById(id);

        if (existing.isPresent()) {

            Flight dbFlight = existing.get();

            dbFlight.setAirline(flight.getAirline());
            dbFlight.setSource(flight.getSource());
            dbFlight.setDestination(flight.getDestination());
            dbFlight.setDepartureDateTime(flight.getDepartureDateTime());
            dbFlight.setArrivalDateTime(flight.getArrivalDateTime());
            dbFlight.setTotalSeats(flight.getTotalSeats());
            dbFlight.setPrice(flight.getPrice());

            return flightRepository.save(dbFlight);

        } else {
            throw new FlightNotFoundException("Cannot update. Flight ID not found: " + id);
        }
    }
    
    public Flight deleteFlight(int id) {

        Optional<Flight> optional = flightRepository.findById(id);

        if (optional.isPresent()) {
            Flight flight = optional.get();
            flightRepository.delete(flight);
            return flight;
        } else {
            throw new FlightNotFoundException("Cannot delete. Flight ID not found: " + id);
        }
    }
    
    //pagination and sorting
    public Page<Flight> getAllFlights(Pageable pageable) {

        Page<Flight> page = flightRepository.findAll(pageable);

        if (page.isEmpty()) {
            throw new FlightNotFoundException("No flights available");
        }

        return page;
    }




    
    
}
