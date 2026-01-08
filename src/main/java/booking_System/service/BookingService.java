package booking_System.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import booking_System.dao.BookingDao;
import booking_System.dto.ResponseStructure;
import booking_System.entity.Booking;
import booking_System.entity.BookingStatus;
import booking_System.entity.Passenger;
import booking_System.entity.Payment;

@Service
public class BookingService {

    @Autowired
    private BookingDao bookingDao;

    public ResponseEntity<ResponseStructure<Booking>> createBooking(Booking booking) {

        Booking saved = bookingDao.createBooking(booking);

        ResponseStructure<Booking> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Booking Created Successfully");
        response.setData(mapFlightId(saved));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings() {

        List<Booking> bookings = bookingDao.getAllBookings();
        bookings.forEach(this::mapFlightId);

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings Retrieved Successfully");
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Booking>> getBookingById(int id) {

        Booking booking = bookingDao.getBookingById(id);
        mapFlightId(booking);

        ResponseStructure<Booking> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Booking Retrieved Successfully");
        response.setData(booking);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByFlight(int flightId) {

        List<Booking> bookings = bookingDao.getBookingsByFlight(flightId);
        bookings.forEach(this::mapFlightId);

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings Retrieved Successfully");
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByStatus(BookingStatus status) {

        List<Booking> bookings = bookingDao.getBookingsByStatus(status);
        bookings.forEach(this::mapFlightId);

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings Retrieved Successfully");
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByBookingId(int bookingId) {

        List<Passenger> passengers = bookingDao.getPassengersByBookingId(bookingId);

        ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Passengers Retrieved Successfully");
        response.setData(passengers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(int bookingId) {

        Payment payment = bookingDao.getPaymentByBookingId(bookingId);

        ResponseStructure<Payment> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payment Details Retrieved Successfully");
        response.setData(payment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(int id, BookingStatus status) {

        Booking updated = bookingDao.updateBookingStatus(id, status);
        mapFlightId(updated);

        ResponseStructure<Booking> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Booking Status Updated Successfully");
        response.setData(updated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<Booking>> deleteBooking(int id) {

        Booking deleted = bookingDao.deleteBooking(id);
        mapFlightId(deleted);

        ResponseStructure<Booking> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Booking Deleted Successfully");
        response.setData(deleted);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByDate(LocalDate date) {

        List<Booking> bookings = bookingDao.getBookingsByDate(date);
        bookings.forEach(this::mapFlightId);

        ResponseStructure<List<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings Retrieved Successfully");
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    private Booking mapFlightId(Booking booking) {
        if (booking.getFlight() != null) {
            booking.setFlightId(booking.getFlight().getId());
        }
        return booking;
    }
    
    public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingsWithPagination(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        Page<Booking> bookings = bookingDao.getAllBookings(pageable);
        bookings.forEach(this::mapFlightId);

        ResponseStructure<Page<Booking>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Bookings Retrieved Successfully");
        response.setData(bookings);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
