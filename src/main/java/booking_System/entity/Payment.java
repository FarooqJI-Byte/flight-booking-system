package booking_System.entity;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
		
		@CreationTimestamp
	    private LocalDateTime paymentDate;

	    private Double amount;

	    private String modeOfTransaction;

	    @Enumerated(EnumType.STRING)
	    private PaymentStatus status;

	    @JsonIgnore
	    @OneToOne
	    @JoinColumn(name = "booking_id")
	    private Booking booking;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public LocalDateTime getPaymentDate() {
	        return paymentDate;
	    }

	    public void setPaymentDate(LocalDateTime paymentDate) {
	        this.paymentDate = paymentDate;
	    }

	    public Double getAmount() {
	        return amount;
	    }

	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    public String getModeOfTransaction() {
	        return modeOfTransaction;
	    }

	    public void setModeOfTransaction(String modeOfTransaction) {
	        this.modeOfTransaction = modeOfTransaction;
	    }

	    public PaymentStatus getStatus() {
	        return status;
	    }

	    public void setStatus(PaymentStatus status) {
	        this.status = status;
	    }

	    public Booking getBooking() {
	        return booking;
	    }

	    public void setBooking(Booking booking) {
	        this.booking = booking;
	    }

	    @Override
	    public String toString() {
	        return "Payment [id=" + id + ", paymentDate=" + paymentDate + ", amount=" + amount + ", modeOfTransaction="
	                + modeOfTransaction + ", status=" + status + "]";
	    }
	}

