# Project Development Guidelines

## Build/Configuration Instructions

### Prerequisites
- Java 21 or later
- Maven 3.8.x or later

### Building the Project
1. Clone the repository
2. Build the project using Maven:
   ```bash
   ./mvnw clean package
   ```

### Running the Application
1. Start the application in development mode:
   ```bash
   ./mvnw quarkus:dev
   ```
   This enables hot deployment with background compilation.

2. The application will be available at `http://localhost:8080`

### Configuration
The application uses the following configuration properties in `src/main/resources/application.properties`:

- `api.key`: API key for standard access (377f0a14-f42c-46fd-bf50-a98999f7bc8d)
- `api.key.mobile`: API key for mobile clients (d28cf030-3c81-408f-875e-a5253e7059bf)
- `quarkus.fault-tolerance.enabled`: Enables Quarkus fault tolerance features

To add additional configuration, extend the `application.properties` file.

### Building Native Executables
To build a native executable:
```bash
./mvnw package -Pnative
```

To build a native executable for a container:
```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

## Testing Information

### Running Tests
To run all tests:
```bash
./mvnw test
```

To run a specific test class:
```bash
./mvnw test -Dtest=CardResourceTest
```

### Authentication in Tests
All API endpoints are protected by API key authentication. When writing tests, you must include the `X-API-Key` header with a valid API key:

```java
@ConfigProperty(name = "api.key")
String apiKey;

@Test
void testEndpoint() {
    given()
        .header("X-API-Key", apiKey)
        .when().get("/endpoint")
        .then()
            .statusCode(200);
}
```

### Test Example
Here's a complete example of a test class for the CardResource:

```java
package fiap.tds;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CardResourceTest {

    @ConfigProperty(name = "api.key")
    String apiKey;

    @Test
    void testGetAllCards() {
        given()
            .header("X-API-Key", apiKey)
            .when().get("/card")
            .then()
                .statusCode(200)
                .body("size()", is(5));
    }
}
```

### Creating New Tests
1. Create a new test class in the `src/test/java` directory
2. Annotate the class with `@QuarkusTest`
3. Inject the API key using `@ConfigProperty`
4. Write test methods using REST-assured and JUnit 5
5. Include the `X-API-Key` header in all requests
6. Run the tests using Maven

## Additional Development Information

### Project Structure
- `src/main/java/fiap/tds`: Main application code
  - `entities`: Domain model classes
  - `dtos`: Data Transfer Objects
  - `infrastructure`: Cross-cutting concerns like filters

### API Authentication
The application uses API key authentication for all endpoints. The API key must be provided in the `X-API-Key` header of each request. There are two valid API keys:
- Standard API key: `377f0a14-f42c-46fd-bf50-a98999f7bc8d`
- Mobile API key: `d28cf030-3c81-408f-875e-a5253e7059bf`

The authentication is implemented in `MyApiFilter.java`.

### Fault Tolerance
The application uses Quarkus SmallRye Fault Tolerance for resilience patterns:
- `@RateLimit`: Limits the rate of requests to an endpoint
- `@Timeout`: Sets a timeout for endpoint execution
- `@Fallback`: Provides a fallback method if the primary method fails

Example usage can be found in `CardResource.java`.

### Code Style
- Use 4 spaces for indentation
- Follow standard Java naming conventions
- Use Java records for DTOs when appropriate
- Use Optional for nullable parameters
- Use var for local variables when the type is obvious

### REST API Design
- Use appropriate HTTP methods (GET, POST, PUT, DELETE)
- Return appropriate HTTP status codes
- Use path parameters for resource identifiers
- Use query parameters for filtering and pagination
- Use JSON for request and response bodies