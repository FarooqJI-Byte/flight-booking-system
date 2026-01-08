package booking_System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import booking_System.entity.Booking;
import booking_System.entity.Passenger;
import booking_System.exception.BookingNotFoundException;
import booking_System.exception.PassengerNotFoundException;
import booking_System.repository.BookingRepository;
import booking_System.repository.PassengerRepository;

@Repository
public class PassengerDao {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired 
    private BookingRepository bookingRepository;

    public Passenger addPassenger(int bookingId, Passenger passenger) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found with ID: " + bookingId));

        passenger.setBooking(booking);

        return passengerRepository.save(passenger);
    }
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(int id) {
        Optional<Passenger> optional = passengerRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new PassengerNotFoundException("Passenger not found for id: " + id);
        }
    }

    public Passenger getPassengerByContactNo(String contactNo) {
        Passenger passenger = passengerRepository.findByContactNo(contactNo);
        if (passenger != null) {
            return passenger;
        } else {
            throw new PassengerNotFoundException("Passenger not found for contact number: " + contactNo);
        }
    }

    public Passenger updatePassenger(int id, Passenger updatedPassenger) {
        Passenger existing = getPassengerById(id);
        existing.setName(updatedPassenger.getName());
        existing.setAge(updatedPassenger.getAge());
        existing.setGender(updatedPassenger.getGender());
        existing.setContactNo(updatedPassenger.getContactNo());
        existing.setSeatNumber(updatedPassenger.getSeatNumber());

        return passengerRepository.save(existing);
    }

    public List<Passenger> getPassengersByFlightId(int flightId) {
        return passengerRepository.findPassengersByFlightId(flightId);
    }
    public Page<Passenger> getAllPassengers(Pageable pageable) {

        Page<Passenger> page = passengerRepository.findAll(pageable);

        if (page.isEmpty()) {
            throw new PassengerNotFoundException("No passengers available");
        }

        return page;
    }

}
