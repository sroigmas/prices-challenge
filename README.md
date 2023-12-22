# Prices challenge

This microservice exposes a REST API to get the price for a given product in a specific date.

### Notes

- Field 'price_list' has been renamed to 'rate_id'
- Field 'price' has been renamed to 'final_price'
- Field 'curr' has been renamed to 'ccy'
- According to the problem proposed, 'rate_id' and 'product_id' could be FK to another tables
- Hexagonal architecture is being used. Spring has been considered part of the infrastructure so
  there are no Spring annotations inside the 'application' folder.
  Use cases are being initialized inside 'infrastructure/configuration' folder

## How to run

````bash
./mvnw clean install
./mvnw spring-boot:run
````
You can check the OpenAPI documentation here: http://localhost:8080/swagger-ui/index.html

You can test the endpoint like this:
http://localhost:8080/api/v1/prices?brand_id=1&product_id=35455&date=2020-06-14T16:00:00Z

## How to run tests

````bash
./mvnw clean install
./mvnw test
````
