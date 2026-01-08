package booking_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import booking_System.dao.PaymentDao;
import booking_System.dto.ResponseStructure;
import booking_System.entity.Payment;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public ResponseEntity<ResponseStructure<Payment>> addPayment(
            int bookingId, Payment payment) {

        Payment saved = paymentDao.recordPayment(bookingId, payment);

        ResponseStructure<Payment> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Payment Added Successfully");
        response.setData(saved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayments() {
        ResponseStructure<List<Payment>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payments Retrieved Successfully");
        response.setData(paymentDao.getAllPayments());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Payment>> getPaymentById(int id) {
        ResponseStructure<Payment> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payment Retrieved Successfully");
        response.setData(paymentDao.getPaymentById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(String status) {
        ResponseStructure<List<Payment>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payments Retrieved By Status");
        response.setData(paymentDao.getPaymentByStatus(status));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(String mode) {
        ResponseStructure<List<Payment>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payments Retrieved By Mode");
        response.setData(paymentDao.getPaymentByMode(mode));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Payment>> getPaymentByBooking(int bookingId) {
        ResponseStructure<Payment> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payment Retrieved By Booking");
        response.setData(paymentDao.getPaymentByBooking(bookingId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(int id, String status) {
        ResponseStructure<Payment> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payment Status Updated Successfully");
        response.setData(paymentDao.updatePaymentStatus(id, status));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentAmountGreaterThan(double amount) {
        ResponseStructure<List<Payment>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payments Retrieved By Amount");
        response.setData(paymentDao.getPaymentAmountGreaterThan(amount));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentsWithPagination(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        Page<Payment> payments = paymentDao.getAllPayments(pageable);

        ResponseStructure<Page<Payment>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Payments Retrieved Successfully");
        response.setData(payments);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
