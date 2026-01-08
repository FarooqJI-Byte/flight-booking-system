package booking_System.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import booking_System.dao.FlightDao;
import booking_System.dto.ResponseStructure;
import booking_System.entity.Flight;

@Service
public class FlightService {
	@Autowired
	private FlightDao flightDao;
	
	//add flight
	public ResponseEntity<ResponseStructure<Flight>> addFlight(Flight flight) {
		 ResponseStructure<Flight> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.CREATED.value());
	        response.setMessage("Flight Added Successfully");
	        response.setData(flightDao.addFlight(flight));
	        return new  ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	//get all flights
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlights() {

        List<Flight> flights = flightDao.getAllFlights();

        ResponseStructure<List<Flight>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Flights Retrieved Successfully");
        response.setData(flights);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	//get by Id
	 public ResponseEntity<ResponseStructure<Flight>> getFlightById(int id) {
	        
	        Flight flight = flightDao.getFlightById(id);  
	        ResponseStructure<Flight> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Flight Retrieved Successfully");
	        response.setData(flight);

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(String source, String destination) {
		 List<Flight> flights = flightDao.getFlightBySourceAndDestination(source, destination);
		 ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		 response.setStatusCode(HttpStatus.OK.value());
		 response.setMessage("Flights Retrieved Successfully");
		 response.setData(flights);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirline(String airline) {

		    List<Flight> flights = flightDao.getFlightByAirline(airline);

		    ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		    response.setStatusCode(HttpStatus.OK.value());
		    response.setMessage("Flights Retrieved Successfully");
		    response.setData(flights);

		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 public ResponseEntity<ResponseStructure<Flight>> updateFlight(int id, Flight flight) {

		    Flight updated = flightDao.updateFlight(id, flight);

		    ResponseStructure<Flight> response = new ResponseStructure<>();
		    response.setStatusCode(HttpStatus.OK.value());
		    response.setMessage("Flight Updated Successfully");
		    response.setData(updated);

		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 public ResponseEntity<ResponseStructure<Flight>> deleteFlight(int id) {

		    Flight deleted = flightDao.deleteFlight(id);

		    ResponseStructure<Flight> response = new ResponseStructure<>();
		    response.setStatusCode(HttpStatus.OK.value());
		    response.setMessage("Flight Deleted Successfully");
		    response.setData(deleted);

		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 
	 //p&s
	 public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightsWithPagination(
	            int page,
	            int size,
	            String sortBy
	    ) {

	        Pageable pageable = PageRequest.of(
	                page,
	                size,
	                Sort.by(sortBy).ascending()
	        );

	        Page<Flight> flights = flightDao.getAllFlights(pageable);

	        ResponseStructure<Page<Flight>> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Flights Retrieved Successfully");
	        response.setData(flights);

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
}
