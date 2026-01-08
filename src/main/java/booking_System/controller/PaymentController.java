package booking_System.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import booking_System.dto.ResponseStructure;
import booking_System.entity.Payment;
import booking_System.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/add/{bookingId}")
    public ResponseEntity<ResponseStructure<Payment>> addPayment(
            @PathVariable int bookingId,
            @RequestBody Payment payment) {

        return paymentService.addPayment(bookingId, payment);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable int id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(@PathVariable String status) {
        return paymentService.getPaymentByStatus(status);
    }

    @GetMapping("/mode/{mode}")
    public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(@PathVariable String mode) {
        return paymentService.getPaymentByMode(mode);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<ResponseStructure<Payment>> getPaymentByBooking(@PathVariable int bookingId) {
        return paymentService.getPaymentByBooking(bookingId);
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable int id, @PathVariable String status) {
        return paymentService.updatePaymentStatus(id, status);
    }

    @GetMapping("/greater/{amount}")
    public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentAmountGreaterThan(@PathVariable double amount) {
        return paymentService.getPaymentAmountGreaterThan(amount);
    }
    @GetMapping("/page")
    public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentsWithPagination(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy
    ) {
        return paymentService.getPaymentsWithPagination(page, size, sortBy);
    }

}
