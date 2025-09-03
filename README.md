# Java Spring Boot API

This project is a RESTful API built with Java and Spring Boot. It provides endpoints for user authentication, registration, and management of user hobbies.

## Features

- User registration and login
- JWT-based authentication
- CRUD operations for users and hobbies
- Exception handling and API responses

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- H2/Other Database (configurable)
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/vickymoditech/JavaSpringBootApi.git
   ```
2. Navigate to the project directory:
   ```bash
   cd JavaSpringBootApi
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Configuration

Edit `src/main/resources/application.properties` to configure database and JWT settings.

## API Endpoints

- `/api/auth/register` - Register a new user
- `/api/auth/login` - Login and receive JWT token
- `/api/users` - Manage users
- `/api/hobbies` - Manage hobbies

## Project Structure

- `controller/` - REST controllers
- `service/` - Business logic
- `repository/` - Data access
- `entity/` - JPA entities
- `dto/` - Data Transfer Objects
- `config/` - Configuration classes
- `interceptor/` - JWT Interceptor
- `shared/` - Common classes (responses, exceptions)

## License

This project is licensed under the MIT License.

## Author

- [vickymoditech](https://github.com/vickymoditech)
