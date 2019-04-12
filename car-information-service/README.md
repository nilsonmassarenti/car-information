# Car Information

This is a REST example project to check the car information.

# Tests

This code has a 90% coverage.

```mvn test ```

# Run

```mvn spring-boot:run ```
If the application is up, check ```http://localhost:8080```

# API endpoint

The endpoints are: 

- Create single car information - POST - `v1/informations/`
- Create car information  by bulk- POST - `v1/informations/`
- List the car informations accounts - GET - `v1/informations/{vin}`
