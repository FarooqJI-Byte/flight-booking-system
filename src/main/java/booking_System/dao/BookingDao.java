package booking_System.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import booking_System.entity.Booking;
import booking_System.entity.BookingStatus;
import booking_System.entity.Flight;
import booking_System.entity.Passenger;
import booking_System.entity.Payment;
import booking_System.exception.BookingNotFoundException;
import booking_System.exception.FlightNotFoundException;
import booking_System.repository.BookingRepository;
import booking_System.repository.FlightRepository;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private FlightRepository flightRepository;

	  public Booking createBooking(Booking booking) {

	        if (booking.getFlightId() == null) {
	            throw new IllegalArgumentException("Flight ID is mandatory");
	        }

	        Flight flight = flightRepository.findById(booking.getFlightId())
	                .orElseThrow(() ->
	                        new FlightNotFoundException(
	                                "Flight not found for ID: " + booking.getFlightId()));

	        booking.setFlight(flight);

	        if (booking.getPassengers() == null || booking.getPassengers().isEmpty()) {
	            throw new IllegalArgumentException("At least one passenger is required");
	        }

	        booking.getPassengers().forEach(p -> p.setBooking(booking));

	        if (booking.getPayment() == null) {
	            throw new IllegalArgumentException("Payment is mandatory");
	        }

	        booking.getPayment().setBooking(booking);

	        return bookingRepository.save(booking);
	    }

	// get all booking
	public List<Booking> getAllBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		if (bookings.isEmpty()) {
			throw new BookingNotFoundException("Currently there are no bookings !!!");
		} else {
			return bookings;
		}

	}

	// by ID
	public Booking getBookingById(int id) {

		Optional<Booking> optional = bookingRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new BookingNotFoundException("Booking not found for ID: " + id);
		}
	}

	// by flight
	public List<Booking> getBookingsByFlight(int flightId) {

		Optional<Flight> optional = flightRepository.findById(flightId);

		if (optional.isPresent()) {

			Flight flight = optional.get();
			List<Booking> bookings = bookingRepository.findByFlight(flight);

			if (bookings.isEmpty()) {
				throw new BookingNotFoundException("No bookings found for flight ID: " + flightId);
			}

			return bookings;

		} else {
			throw new FlightNotFoundException("Flight not found for ID: " + flightId);
		}
	}

	public List<Booking> getBookingsByStatus(BookingStatus status) {

		List<Booking> bookings = bookingRepository.findByStatus(status);

		if (bookings.isEmpty()) {
			throw new BookingNotFoundException("No bookings found with status: " + status);
		} else {
			return bookings;
		}
	}

	public List<Passenger> getPassengersByBookingId(int bookingId) {

		Optional<Booking> optional = bookingRepository.findById(bookingId);

		if (optional.isPresent()) {

			Booking booking = optional.get();
			List<Passenger> passengers = booking.getPassengers();

			if (passengers == null || passengers.isEmpty()) {
				throw new BookingNotFoundException("No passengers found for booking ID: " + bookingId);
			}

			return passengers;

		} else {
			throw new BookingNotFoundException("Booking not found for ID: " + bookingId);
		}
	}

	public Payment getPaymentByBookingId(int bookingId) {

		Optional<Booking> optional = bookingRepository.findById(bookingId);

		if (optional.isPresent()) {

			Booking booking = optional.get();
			Payment payment = booking.getPayment();

			if (payment == null) {
				throw new BookingNotFoundException("No payment found for booking ID: " + bookingId);
			}

			return payment;

		} else {
			throw new BookingNotFoundException("Booking not found for ID: " + bookingId);
		}
	}

	public Booking updateBookingStatus(int id, BookingStatus status) {

		Optional<Booking> optional = bookingRepository.findById(id);

		if (optional.isPresent()) {

			Booking booking = optional.get();
			booking.setStatus(status);

			return bookingRepository.save(booking);

		} else {
			throw new BookingNotFoundException("Cannot update. Booking ID not found: " + id);
		}
	}

	public Booking deleteBooking(int id) {

		Optional<Booking> optional = bookingRepository.findById(id);

		if (optional.isPresent()) {

			Booking booking = optional.get();
			bookingRepository.delete(booking);
			return booking;

		} else {
			throw new BookingNotFoundException("Cannot delete. Booking ID not found: " + id);
		}
	}

	public List<Booking> getBookingsByDate(LocalDate date) {

		LocalDateTime startOfDay = date.atStartOfDay();
		LocalDateTime endOfDay = date.atTime(23, 59, 59);

		List<Booking> bookings = bookingRepository.findByBookingDateBetween(startOfDay, endOfDay);

		if (bookings.isEmpty()) {
			throw new BookingNotFoundException("No bookings found on date: " + date);
		}

		return bookings;
	}

	public Page<Booking> getAllBookings(Pageable pageable) {

		Page<Booking> page = bookingRepository.findAll(pageable);

		if (page.isEmpty()) {
			throw new BookingNotFoundException("No bookings available");
		}

		return page;
	}

}
