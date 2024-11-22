# 🎟️ TicketBooking Backend (Java)

Welcome to the TicketBooking Backend project! This repository contains the backend code for a ticket booking system, built using Java.

## 🚀 Features

- User authentication and authorization
- Ticket booking and cancellation
- Event management
- Admin dashboard

## 📖 Usage

### 🔐 User Authentication

- **Register**: Users can register by sending a POST request to `/api/register` with their details.
- **Login**: Users can log in by sending a POST request to `/api/login` with their credentials.

### 🎫 Ticket Booking

- **Book Ticket**: Users can book tickets by sending a POST request to `/api/book` with event and ticket details.
- **Cancel Ticket**: Users can cancel tickets by sending a DELETE request to `/api/cancel/{ticket_id}`.

### 📅 Event Management

- **Create Event**: Admins can create events by sending a POST request to `/api/events` with event details.
- **Update Event**: Admins can update events by sending a PUT request to `/api/events/{event_id}`.
- **Delete Event**: Admins can delete events by sending a DELETE request to `/api/events/{event_id}`.

## 🛠️ Tech Stack

- **Java**: The main programming language used for the backend.
- **Spring Boot**: Framework for building the API.
- **JPA**: Java Persistence API for ORM.
- **Spring Security**: Framework for securing the application.
- **PostgreSQL**: Database for storing data.

## 📦 Installation

1. **Clone the repository**
    ```sh
    git clone https://github.com/PranayKumar9902/Eventure-API-Java.git
    cd TicketBooking/backend-java
    ```

2. **Set up environment variables**
    Create a `application.properties` file in the `src/main/resources` directory and add the following:
    ```properties
      server.port=YOUR_PORT

      spring.datasource.url=jdbc:postgresql://YOUR_HOST:YOUR_PORT/YOUR_DATABASE
      spring.datasource.username=YOUR_USERNAME
      spring.datasource.password=YOUR_PASSWORD
      spring.datasource.driver-class-name=org.postgresql.Driver
      spring.sql.init.platform=postgresql
     
      spring.jpa.hibernate.ddl-auto=update  
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.format_sql=true
     
      server.servlet.context-path=/api
      jwt.secret=YOUR_SECRET_KEY

    ```

3. **Install dependencies**
    ```sh
    mvn clean install
    ```

4. **Run the application**
    ```sh
    mvn spring-boot:run
    ```

## ⚙️ Configuration

- **Database Configuration**: Ensure MySQL is installed and running. Update the `application.properties` file with your database credentials.
- **JWT Configuration**: Set the `jwt.secret` in the `application.properties` file for token generation and validation.
