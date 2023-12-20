
# Prices challenge
This microservice exposes a REST API to get the price for a given product in a specific date.

### Notes
- Field 'price_list' has been renamed to 'rate_id'
- Field 'price' has been renamed to 'final_price'
- Field 'curr' has been renamed to 'ccy'
- According to the problem proposed, 'rate_id' and 'product_id' could be FK to another tables

## How to run
````bash
./mvnw clean install
./mvnw spring-boot:run
````

## How to run tests
````bash
./mvnw clean install
./mvnw test
````
