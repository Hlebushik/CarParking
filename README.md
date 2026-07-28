# Parking Lot Management API

A basic REST API for managing a parking lot. The project demonstrates a classic backend architecture (Controller-Service-Repository) using Spring Boot.

## Tech Stack
- Java 21
- Spring Boot (Web, Data JPA)
- PostgreSQL
- Lombok
- Maven

## Local Setup
1. Create a PostgreSQL database named `parking`.
2. Verify the database credentials in `src/main/resources/application.properties` (default: `postgres` / `1234`).
3. Run the following command to start the application:
   ```bash
   mvn spring-boot:run

## API Endpoints
- GET /cars — Get a list of all parked cars.
- GET /cars/{carNumber} — Find a car by its license plate number.
- POST /cars — Register a new car (Body: {"carNumber": "AX1234AA", "paid": false}).
- PUT /cars/{carNumber} — Update the payment status (Body: {"paid": true}).
- DELETE /cars/{carNumber} — Remove a car from the parking spot (only allowed if paid).