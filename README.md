# Flight Booking System

This is a Spring Boot application that provides a REST API for searching and booking flights with Bateekh Airways (BA).

### Requirements

- Java 17
- Maven
- H2 In-Memory Database (default configuration)
- Postman or curl for testing

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone git@github.com:ahmadabuwardeh/flight-booking-system.git
   cd flight-booking-system

2. **Build the project:**
   ```bash
   mvn clean install

3. **Run the application:**
   ```bash
   mvn spring-boot:run

### Testing the API

#### Search API

1. **One-Way Trip**

To search for available one-way flights from AMM (Amman) to DXB (Dubai) on 2024-09-22 for 1 passenger:

```bash
curl -X GET "http://localhost:8080/api/flights/search?origin=AMM&destination=DXB&departureDate=2024-09-22&passengers=1"
```

2. **Round-Trip**

To search for available round-trip flights from AMM (Amman) to DXB (Dubai) with departure on 2024-09-22 and return on 2024-09-26 for 1 passenger:

```bash
curl -X GET "http://localhost:8080/api/flights/search?origin=AMM&destination=DXB&departureDate=2024-09-22&returnDate=2024-09-29&passengers=1"
```


#### Book API

1. **Booking a One-Way Flight**

To book a flight with flight code BA-101 for 3 passengers under the name Ahmad:

```bash
curl -X POST "http://localhost:8080/api/bookings?flightCode=BA-101&passengerName=Ahmad&passengers=3"
```

### Database

This application uses an H2 in-memory database, so no external database setup is required. The database will reset on every application restart.


## Running Tests

To ensure that all functionalities are working correctly, you can run the unit tests provided in the project:

   ```bash
   mvn clean test
