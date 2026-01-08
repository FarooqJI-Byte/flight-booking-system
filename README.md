📌 Project Overview

The Flight Booking System is a backend RESTful application developed using Spring Boot that manages flight schedules, bookings, passengers, and payments.
It follows a layered architecture and demonstrates real-world backend development practices including entity relationships, exception handling, and API integration.
This project is designed to simulate a real airline booking workflow where a user can search flights, create bookings, add passengers, and process payments.

🛠️ Tech Stack :

Java
Spring Boot
Spring Data JPA
Hibernate
PostgreSQL
Maven
REST APIs
Git & GitHub

Application Architecture :

Controller Layer – Handles HTTP requests and responses
Service Layer – Business logic and validations
DAO Layer – Database interaction using JPA
Repository Layer – Spring Data JPA repositories
Global Exception Handling – Centralized error management

Key Features :

Flight management (add, update, fetch, delete)
Booking creation with passengers and payment details
One-to-many and one-to-one entity relationships
Enum-based status management for bookings and payments
Prevention of JSON infinite recursion
Global exception handling with meaningful responses
API testing using REST clients

🔗 Major APIs :

✈️ Flight APIs :

Add Flight
Get All Flights
Get Flight by ID
Get Flight by Airline
Get Flight by Source and Destination
Update Flight
Delete Flight

📖 Booking APIs : 

Create Booking (with passengers & payment)
Get All Booking
Get Booking by ID
Get Bookings by Flight
Get Bookings by Status
Update Booking Status
Delete Booking

🗄️ Database :
PostgreSQL

Entity relationships :

One Flight → Many Bookings
One Booking → Many Passengers
One Booking → One Payment

▶️ How to Run the Project
1.Clone the repository
2.Import the project into Eclipse or IntelliJ
3.Configure PostgreSQL database in application.properties
4.Run the application as a Spring Boot App
5.Access APIs at:
http://localhost:8080

📌 Future Enhancements : 
Frontend integration (HTML/CSS/JavaScript)
Role-based authentication and authorization
Seat availability management

👨‍💻 Author
Farooq Irakall
Backend Developer | Java | Spring Boot

🏁 Notes: 
This project focuses on backend development and API design. Frontend implementation is optional and can be integrated in future phases.












