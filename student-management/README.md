# Student Management System

A robust Spring Boot application for advanced CRUD operations on student entities, with validation, error handling, MySQL integration, and clean architecture.

## Features

- RESTful CRUD API for students
- Input validation and global exception handling
- MySQL database integration
- Clean layered architecture (controller, service, repository, model, exception)
- Unit tests with JUnit 5 and Mockito
- Lombok for reduced boilerplate

## Requirements

- Java 17+
- Maven
- MySQL

## Setup

1. **Clone the repository**
2. **Create a MySQL database** (e.g., `studentdb`)
3. **Update `src/main/resources/application.properties`** with your DB credentials:
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
    spring.datasource.username=your_user
    spring.datasource.password=your_pass
    ```
4. **Build and run the application**
    ```
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints

| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| POST   | `/students`        | Add a new student        |
| GET    | `/students`        | Get all students         |
| GET    | `/students/{id}`   | Get student by ID        |
| PUT    | `/students/{id}`   | Update student by ID     |
| DELETE | `/students/{id}`   | Delete student by ID     |

## Example Student JSON

```json
{
  "name": "John Doe",
  "age": 20,
  "grade": "A+",
  "address": "123 Main St"
}
```

## Testing

Run unit tests with:
```
mvn test
```

---
