

[![codecov](https://codecov.io/gh/guilhermeayusso/beauty-manager-api/branch/main/graph/badge.svg)](https://codecov.io/gh/guilhermeayusso/beauty-manager-api)


# Beauty Manager API

A **Beauty Manager API** is a RESTful application developed to manage beauty services, including establishments and bookings. This API allows establishments to register their services and opening hours, as well as enabling customers to create and view bookings.

## API Documentation

You can access the full API documentation via Swagger at the following link:

[Swagger UI - Beauty Manager API](https://beauty-manager-api-b5eb0176cad9.herokuapp.com/swagger-ui/index.html)

## Features

The API provides the following main features:

- **Establishment Registration**:
    - Register new establishments with information such as name, type, opening and closing hours, address, and photos.
    - Example Endpoint:
      ```
      POST /api/v1/estabelecimentos
      ```

- **List Establishments**:
    - Retrieve a list of registered establishments, with the option to filter by criteria such as establishment type.
    - Example Endpoint:
      ```
      GET /api/v1/estabelecimentos
      ```

- **Create Booking**:
    - Create bookings for services offered by establishments.
    - Example Endpoint:
      ```
      POST /api/v1/agendamentos
      ```

- **List Bookings**:
    - Retrieve the list of bookings made by customers.
    - Example Endpoint:
      ```
      GET /api/v1/agendamentos
      ```

- **Update Booking**:
    - Update details of an existing booking.
    - Example Endpoint:
      ```
      PUT /api/v1/agendamentos/{id}
      ```

## Running Locally

To run this application locally, follow these steps:

### Prerequisites

- Java 17 or higher
- Maven 3.6+ or higher

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/guilhermeayusso/beauty-manager-api.git
   cd beauty-manager-api
   ```

2. Build and run the application using Maven:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Access the application running at `http://localhost:8080`.

## Deployment on Heroku

The API is currently hosted on Heroku. You can access the production version at the following link:

[https://beauty-manager-api-b5eb0176cad9.herokuapp.com](https://beauty-manager-api-b5eb0176cad9.herokuapp.com)

### Environment Variables on Heroku

If the application uses environment variables, you can configure them on Heroku with the following command:

```bash
heroku config:set VARIABLE_NAME=value
```

### Logs on Heroku

To monitor the application logs on Heroku:

```bash
heroku logs --tail
```

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Maven**
- **Heroku** (for deployment)
- **Swagger** (for API documentation)

## Contact

For more information, contact [guilhermeayusso](https://github.com/guilhermeayusso).
