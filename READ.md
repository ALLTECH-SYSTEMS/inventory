# Online Bookstore Application

## Description
This project is an online bookstore application that allows users to manage books, shopping carts, and purchase history. It supports various features such as book browsing, adding books to carts, and checking out.

## Getting Started

### Prerequisites
Before you begin, ensure you have met the following requirements:
- Java 17 or higher
- Maven 3.9.6 or higher (for building the project)
- PostgreSQL 16 or higher (for the database)

### Dependencies
This project uses the following major dependencies:
- Spring Boot 3.2.5
  - Spring Web
  - Spring Data JPA
- PostgreSQL Driver
- Lombok (for reducing boilerplate code)

### Configuration
1. **Database Configuration**:
   - Create a PostgreSQL database named `book`.
   - Update the `src/main/resources/application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/book
     spring.datasource.username=yourUsername
     spring.datasource.password=yourPassword
     ```

2. **Application Properties**:
   - Configure application-specific properties in `application.properties`, such as:
     ```properties
     server.port=8080
     ```

### Building the Project
To build the project, run the following command from the root of the project:
```bash
mvn clean install
