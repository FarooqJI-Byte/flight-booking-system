package booking_System.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import booking_System.entity.Booking;
import booking_System.entity.Payment;
import booking_System.entity.PaymentStatus;
import booking_System.exception.BookingNotFoundException;
import booking_System.exception.PaymentAlreadyExistsException;
import booking_System.exception.PaymentNotFoundException;
import booking_System.repository.BookingRepository;
import booking_System.repository.PaymentRepository;

@Repository
public class PaymentDao {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    public Payment recordPayment(int bookingId, Payment payment) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // 🔴 IMPORTANT CHECK
        if (booking.getPayment() != null) {
            throw new PaymentAlreadyExistsException(
                    "Payment already exists for booking ID: " + bookingId);
        }

        payment.setBooking(booking);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException("No payments found in the system");
        }
        return payments;
    }

    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for ID: " + id));
    }

    public List<Payment> getPaymentByStatus(String status) {
        List<Payment> payments = paymentRepository.findByStatus(status);
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException("No payments found with status: " + status);
        }
        return payments;
    }

    public List<Payment> getPaymentByMode(String mode) {
        List<Payment> payments = paymentRepository.findByModeOfTransaction(mode);
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException("No payments found with mode: " + mode);
        }
        return payments;
    }

    public Payment getPaymentByBooking(int bookingId) {
        Payment payment = paymentRepository.findByBookingId(bookingId);
        if (payment == null) {
            throw new PaymentNotFoundException("No payment found for booking ID: " + bookingId);
        }
        return payment;
    }

    public Payment updatePaymentStatus(int id, String status) {

        Payment payment = getPaymentById(id);

        try {
            PaymentStatus newStatus = PaymentStatus.valueOf(status.toUpperCase());
            payment.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new PaymentNotFoundException("Invalid payment status: " + status);
        }

        return paymentRepository.save(payment);
    }
    public List<Payment> getPaymentAmountGreaterThan(double amount) {
        List<Payment> payments = paymentRepository.findByAmountGreaterThan(amount);
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException("No payments found with amount greater than: " + amount);
        }
        return payments;
    }
    public Page<Payment> getAllPayments(Pageable pageable) {

        Page<Payment> page = paymentRepository.findAll(pageable);

        if (page.isEmpty()) {
            throw new PaymentNotFoundException("No payments available");
        }

        return page;
    }

}
