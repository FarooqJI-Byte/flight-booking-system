package booking_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import booking_System.dao.PassengerDao;
import booking_System.dto.ResponseStructure;
import booking_System.entity.Passenger;
import booking_System.exception.PassengerNotFoundException;

@Service
public class PassengerService {

    @Autowired
    private PassengerDao passengerDao;

    public ResponseEntity<ResponseStructure<Passenger>> addPassenger(
            int bookingId, Passenger passenger) {

        Passenger savedPassenger = passengerDao.addPassenger(bookingId, passenger);

        ResponseStructure<Passenger> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Passenger Added Successfully");
        response.setData(savedPassenger);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengers() {
        List<Passenger> passengers = passengerDao.getAllPassengers();

        if (passengers.isEmpty()) {
            throw new PassengerNotFoundException("No passengers available");
        }

        ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All passengers retrieved successfully");
        response.setData(passengers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(int id) {
        Passenger passenger = passengerDao.getPassengerById(id);

        ResponseStructure<Passenger> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Passenger found");
        response.setData(passenger);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNo(String contactNo) {
        Passenger passenger = passengerDao.getPassengerByContactNo(contactNo);

        ResponseStructure<Passenger> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Passenger found");
        response.setData(passenger);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(int id, Passenger passenger) {
        Passenger updated = passengerDao.updatePassenger(id, passenger);

        ResponseStructure<Passenger> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Passenger updated successfully");
        response.setData(updated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByFlight(int flightId) {
        List<Passenger> passengers = passengerDao.getPassengersByFlightId(flightId);

        if (passengers.isEmpty()) {
            throw new PassengerNotFoundException("No passengers found for flight id: " + flightId);
        }

        ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Passengers fetched for flight");
        response.setData(passengers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengersWithPagination(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        Page<Passenger> passengers = passengerDao.getAllPassengers(pageable);

        ResponseStructure<Page<Passenger>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Passengers Retrieved Successfully");
        response.setData(passengers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
