package booking_System.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import booking_System.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	 @ExceptionHandler(FlightNotFoundException.class)
	    public ResponseEntity<ResponseStructure<String>> handleFlightNotFound(FlightNotFoundException ex) {
	        ResponseStructure<String> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage(ex.getMessage());
	        response.setData(null);

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(BookingNotFoundException.class)
	    public ResponseEntity<ResponseStructure<String>> handleBookingNotFound(BookingNotFoundException ex) {
	        ResponseStructure<String> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage(ex.getMessage());
	        response.setData(null);

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	 @ExceptionHandler(PassengerNotFoundException.class)
	    public ResponseEntity<ResponseStructure<String>> handlePassengerNotFound(PassengerNotFoundException ex) {
	        ResponseStructure<String> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage(ex.getMessage());
	        response.setData(null);

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(PaymentNotFoundException.class)
	    public ResponseEntity<ResponseStructure<String>> handlePaymentNotFound(PaymentNotFoundException ex) {
	        ResponseStructure<String> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage(ex.getMessage());
	        response.setData(null);

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(PaymentAlreadyExistsException.class)
	    public ResponseEntity<ResponseStructure<String>> handlePaymentAlreadyExists(
	            PaymentAlreadyExistsException ex) {

	        ResponseStructure<String> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        response.setMessage(ex.getMessage());
	        response.setData(null);

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<ResponseStructure<String>> handleIllegalArgument(
	            IllegalArgumentException ex) {

	        ResponseStructure<String> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        response.setMessage(ex.getMessage());
	        response.setData(null);

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

}
