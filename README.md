# EcommerceAPI

# Project Overview

This project is a Spring Boot REST API for managing products in an e-commerce system. It supports full CRUD operations, along with input validation, filtering, and proper error handling.

The API follows RESTful principles and uses a simple in-memory data structure for storage, making it ideal for learning and testing. It also demonstrates best practices such as layered architecture, dependency injection, and the use of HTTP status codes for clear client-server communication.

# Key Features

* RESTful API design
* Create, Read, Update, Delete (CRUD)
* Partial updates using PATCH
* Product filtering (by category and price range)
* Input validation using Jakarta Validation
* Exception handling
* In-memory data storage (no database)

# Setup Instructions

## Requirements
* Java 17
* IntelliJ IDEA
* Maven
* Postman (for testing)

## Steps to Run

1. Clone the repository:

* git clone https://github.com/your-repo/product-api.git

2. Open in IntelliJ

3. Run the main class:

ECOMMERCEAPIAPPLICATION.JAVA

* Server will start at: http://localhost:8080


---

# API Endpoint Reference

## 1. Get All Products
- Method: GET
- Path: `/api/v1/products`
- Response: 200 OK

## 2. Get Product by ID
- Method: GET
- Path: `/api/v1/products/{id}`
- Responses: 200 OK, 404 Not Found

## 3. Create Product
- Method: POST
- Path: `/api/v1/products`
- Response: 201 Created

## 4. Update Product (PUT)
- Method: PUT
- Path: `/api/v1/products/{id}`
- Responses: 200 OK, 400 Bad Request, 404 Not Found

## 5. Partial Update (PATCH)
- Method: PATCH
- Path: `/api/v1/products/{id}`
- Responses: 200 OK, 404 Not Found

## 6. Delete Product
- Method: DELETE
- Path: `/api/v1/products/{id}`
- Responses: 204 No Content, 404 Not Found

## 7. Filter by Category/Name/Price
 
* Method: GET
* Path: http://localhost:8080/api/v1/products
* Response: 200 OK

# Testing Results (Proof)
* GET All Products 
![GETALLPRODUCTS1.png](LAB7-SS/GETALLPRODUCTS1.png)
![GETALLPRODUCTS2.png](LAB7-SS/GETALLPRODUCTS2.png)
![GETALLPRODUCTS3.png](LAB7-SS/GETALLPRODUCTS3.png)
![GETALLPRODUCTS4.png](LAB7-SS/GETALLPRODUCTS4.png)

  
* GET by ID

![GET200OK.png](LAB7-SS/GET200OK.png)

![GET404NOTFOUND.png](LAB7-SS/GET404NOTFOUND.png)

* POST (Create) 
![POST201CREATED.png](LAB7-SS/POST201CREATED.png)
![POST201CREATED1.png](LAB7-SS/POST201CREATED1.png)

* PUT (Update) 
![PUT200OK1.png](LAB7-SS/PUT200OK1.png)
![PUT400BADREQUEST.png](LAB7-SS/PUT400BADREQUEST.png)
![PUT404NOTFOUND.png](LAB7-SS/PUT404NOTFOUND.png)

* PATCH (Partial Update) 
![PATCH200OK.png](LAB7-SS/PATCH200OK.png)
![PATCH404NOTFOUND.png](LAB7-SS/PATCH404NOTFOUND.png)

* DELETE 
![DELETE204NOCONTENT.png](LAB7-SS/DELETE204NOCONTENT.png)
![DELETE404NOTFOUND.png](LAB7-SS/DELETE404NOTFOUND.png)



# Known Limitations
* Uses in-memory storage (data resets on restart)
* No database integration
* Filtering currently supports category only        
