# 🚗 Parking Lot Management System

A modular, scalable **Spring Boot + MySQL** backend application to manage a multi-floor parking lot with support for dynamic space allocation and pluggable payment strategies.

---

## 📌 Overview

This Parking Lot Management System handles vehicle entry and exit flows, assigns parking spots using strategy patterns, calculates parking fees dynamically, and ensures accurate state transitions for each vehicle. The architecture separates domain, service, and controller layers for clean code and maintainability.

---

## ✅ Features

- **Vehicle Entry**  
  - Saves vehicle and creates a parking ticket  
  - Assigns parking space using a configurable `ParkingStrategy`  
  - Prevents overbooking or re-entry into occupied space  

- **Vehicle Exit**  
  - Calculates payment using a pluggable `PaymentStrategy` (cash, coupon, etc.)  
  - Frees up space and removes ticket  

- **Strategy Support**  
  - `ParkingStrategy`: FIFO, RANDOM  
  - `PaymentStrategy`: Cash, Coupon, Cashless  

- **Modular Architecture**  
  - `Controller` layer: REST endpoints  
  - `Service` layer: business logic  
  - `Repository` layer: JPA interfaces  
  - `Domain` layer: JPA-annotated entities  

- **Unit Tested**  
  - JUnit + Mockito unit tests for core services  
  - Mocks for repositories and strategies  

---

## 🧱 Tech Stack

| Layer        | Technology     |
| ------------ | -------------- |
| Backend      | Java 17, Spring Boot 3 |
| Database     | MySQL          |
| ORM          | Spring Data JPA |
| Testing      | JUnit 5, Mockito |
| Utilities    | Lombok, ModelMapper (optional) |

---

## 🗃️ Database Configuration

Update your `application.properties` for MySQL and table auto-generation:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parking_lot
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

