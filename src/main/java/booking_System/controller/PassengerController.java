package booking_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import booking_System.dto.ResponseStructure;
import booking_System.entity.Passenger;
import booking_System.service.PassengerService;
@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/add/{bookingId}")
    public ResponseEntity<ResponseStructure<Passenger>> addPassenger(
            @PathVariable int bookingId,
            @RequestBody Passenger passenger) {

        return passengerService.addPassenger(bookingId, passenger);
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable int id) {
        return passengerService.getPassengerById(id);
    }

    @GetMapping("/contact/{contactNo}")
    public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContact(@PathVariable String contactNo) {
        return passengerService.getPassengerByContactNo(contactNo);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(
            @PathVariable int id,
            @RequestBody Passenger passenger) {
        return passengerService.updatePassenger(id, passenger);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByFlight(@PathVariable int flightId) {
        return passengerService.getPassengersByFlight(flightId);
    }
    @GetMapping("/page")
    public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengersWithPagination(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy
    ) {
        return passengerService.getPassengersWithPagination(page, size, sortBy);
    }

    
}
