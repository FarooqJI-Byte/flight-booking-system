package booking_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import booking_System.dto.ResponseStructure;
import booking_System.entity.Flight;
import booking_System.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<Flight>> addFlight(@RequestBody Flight flight){
		return flightService.addFlight(flight);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlights() {
	    return flightService.getAllFlights();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(@PathVariable int id){
	    return flightService.getFlightById(id);
	}
	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirline(@PathVariable String airline) {
	    return flightService.getFlightByAirline(airline);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(@PathVariable int id,
	                                                              @RequestBody Flight flight) {
	    return flightService.updateFlight(id, flight);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Flight>> deleteFlight(@PathVariable int id) {
	    return flightService.deleteFlight(id);
	}
	@GetMapping("/page")
	public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightsWithPagination( @RequestParam int page, @RequestParam int size, @RequestParam String sortBy ) {
        return flightService.getFlightsWithPagination(page, size, sortBy);
    }
}
