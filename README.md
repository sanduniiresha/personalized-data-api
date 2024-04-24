# Personalized Data API

This API provides personalized information for shoppers and product metadata, serving both internal data team operations and external eCommerce integration.

## Technologies

- Java 21
- Spring Boot 3.1.10
- Spring Data JPA
- Spring Web
- H2 Database (runtime)
- Jakarta EE API
- Flyway Core
- Spring Boot Validation
- Lombok (provided)

## Endpoints

### 1. Internal Data Team Operations

#### Save Shopper Product List
- **Endpoint:** `POST /api/internal/shoppers`
- **Description:** Saves personalized product lists for shoppers.
- **Request Body:** Shopper's personalized product list data.
- **Response:** Success message indicating successful data storage.

#### Save Product Metadata
- **Endpoint:** `POST /api/internal/products`
- **Description:** Saves metadata for products.
- **Request Body:** Product metadata.
- **Response:** Success message indicating successful data storage.

### 2. External eCommerce Integration

#### Retrieve Shopper Products with Pagination
- **Endpoint:** `GET /api/external/shoppers/{shopperId}/products`
- **Description:** Retrieves personalized product information by shopper with optional filtering by category, brand, and pagination support.
- **Parameters:**
    - `{shopperId}`: The unique identifier of the shopper.
    - `category` (optional): Filter products by category.
    - `brand` (optional): Filter products by brand.
    - `page` (optional, default = 1): Page number for pagination.
    - `size` (optional, default = 10, max = 100): Number of items per page.
- **Response:** Returns a paginated list of personalized product information for the specified shopper, filtered by category and brand.

## Accessing H2 Console

To access the H2 database console, navigate to [http://localhost:8080/h2-console](http://localhost:8080/h2-console) in your web browser.
Enter the JDBC URL `jdbc:h2:mem:testdb`, username `sa`, 
check application.properties file for password. Then click "Connect".

## Installation

1. Clone the repository:
   -git clone https://github.com/sanduniiresha/personalized-data-api

