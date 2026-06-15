# BookMyShow clone

A movie ticket booking application that allows users to search for movies, select theatres and shows, reserve seats, make payments, and book tickets efficiently.

# Features #

1. User Management
2. Movie Management
3. Theatre Management
4. Review Management
5. Seat Management
6. Show Management
7. ShowSeat Management
8. Booking Management
9. Ticket Management
10. Payment Management
11. Booking Cancellation
12. Refund Management
13. Notification Management
14. Search Management
15. Analytics Management
16. Exception Handler

 # Tech Stack
1. Java 17
2. Spring Boot
3. Spring Data JPA
4. Hibernate
5. PostgreSQL
6. Gradle
7. Lombok
8. JWT Authentication
9. OAuth2 (Google & GitHub Login)

# Project Structure

src -
    | 
   main -
         |
        controller
        service
        repository
        dto
        mapper
        schema
        exception
        security
        config
        
# Booking Flow  
1. User logs in.
2. User searches a theatre and show.
3. User selects a theatre and show.
4. user chooses seats.
5. User makes payment.
6. Booking is confirmed.
7. Ticket is generated.
8. Notification is sent to the user.
9. User can Cancel the booking and receive a refund.

# Installations and SetUp
-- Clone the Repository --
```
[git clone http](https://github.com/shivanisoni12284/Book-My-Show.git)
```

### Configure Database

update --> application.yaml

server:
  port: 3000
spring:
  application:
    name: bookmyshow

  datasource:
    url: jdbc:postgresql://localhost:5432/bookmyshowapp
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            scope:
              - email
              - profile
              - openid
          github:
            client-id: ${GITHUB_CLIENT_ID}
            client-secret: ${GITHUB_CLIENT_SECRET}

### Build and Run
```bash
gradlew build
gradlew bootRun
```

The application will start on:
```
http://localhost:3000
```

### Enviroment Variable
```
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret
```
            
# Future Enhancement
- Email Notifications
- SMS Notifications
- Redis Caching
- Docker Containerization
- Microservices Architecture
- Recommendation System
- Admin Dashboard
- Booking History
- Like Dislike module
- Seat Lock Expiration Scheduler



        



