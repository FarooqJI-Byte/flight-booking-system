package booking_System.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import booking_System.dto.ResponseStructure;
import booking_System.entity.Booking;
import booking_System.entity.BookingStatus;
import booking_System.entity.Passenger;
import booking_System.entity.Payment;
import booking_System.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	//create booking
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody Booking booking) {
	    return bookingService.createBooking(booking);
	}
	//get all bookings
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings() {
	    return bookingService.getAllBookings();
	}
	//get by bookings
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable int id) {
	    return bookingService.getBookingById(id);
	}
	@GetMapping("/flight/{flightId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByFlight(@PathVariable int flightId) {
	    return bookingService.getBookingsByFlight(flightId);
	}
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByStatus(@PathVariable BookingStatus status) {
	    return bookingService.getBookingsByStatus(status);
	}
	@GetMapping("/passengers/{bookingId}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByBookingId(@PathVariable int bookingId) {
	    return bookingService.getPassengersByBookingId(bookingId);
	}
	@GetMapping("/payment/{bookingId}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(@PathVariable int bookingId) {
	    return bookingService.getPaymentByBookingId(bookingId);
	}
	@PutMapping("/update/status/{id}/{status}")
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(
	        @PathVariable int id,
	        @PathVariable BookingStatus status) {

	    return bookingService.updateBookingStatus(id, status);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(@PathVariable int id) {
	    return bookingService.deleteBooking(id);
	}
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByDate(@PathVariable LocalDate date) {
	    return bookingService.getBookingsByDate(date);
	}
	@GetMapping("/page")
	public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingsWithPagination(
	        @RequestParam int page,
	        @RequestParam int size,
	        @RequestParam String sortBy
	) {
	    return bookingService.getBookingsWithPagination(page, size, sortBy);
	}

}
