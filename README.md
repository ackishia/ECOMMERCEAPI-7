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


# Laboratory 8 – E-commerce Full Stack Application

# Overview

This project is a full-stack e-commerce application that integrates a **Spring Boot backend**, **MySQL database**, and a **dynamic frontend** using the Fetch API.



# Technologies Used

* **Backend:** Spring Boot, Spring Data JPA, Hibernate
* **Database:** MySQL (XAMPP)
* **Frontend:** HTML, CSS, JavaScript (Fetch API)


# Database Schema

# Product Table

![DATABASESETUP-8.png](LAB8-SS/DATABASESETUP-8.png)

| Column         | Type    | Description            |
| -------------- | ------- | ---------------------- |
| id             | bigint  | Primary Key            |
| name           | varchar | Product name           |
| description    | varchar | Product description    |
| price          | double  | Product price          |
| image_url      | varchar | Image filename/path    |
| stock_quantity | int     | Available stock        |
| category_id    | bigint  | Foreign key (Category) |



# Category Table
![CATEGORY.png](LAB8-SS/CATEGORY.png)

| Column | Type    | Description   |
| ------ | ------- | ------------- |
| id     | bigint  | Primary Key   |
| name   | varchar | Category name |


# Relationship

* **One Category → Many Products** (One-to-Many)



# API Endpoints

| Method | Endpoint                | Description       |
| ------ | ----------------------- | ----------------- |
| GET    | `/api/v1/products`      | Get all products  |
| GET    | `/api/v1/products/{id}` | Get product by ID |
| POST   | `/api/v1/products`      | Create product    |
| PUT    | `/api/v1/products/{id}` | Update product    |
| PATCH  | `/api/v1/products/{id}` | Partial update    |
| DELETE | `/api/v1/products/{id}` | Delete product    |



# Frontend Integration

The frontend uses **Fetch API** to retrieve and display products dynamically.

```javascript
async function fetchProducts() {
    try {
        const response = await fetch("http://localhost:8080/api/v1/products");

        // Handle HTTP errors
        if (!response.ok) {
            if (response.status === 404) {
                throw new Error("Products not found");
            }
            if (response.status === 500) {
                throw new Error("Internal server error");
            }
            throw new Error("Failed to fetch products");
        }

        const products = await response.json();
        renderProducts(products);

    } catch (error) {
        console.error("Fetch error:", error.message);

        const productContainer = document.querySelector(".products-grid");

        if (productContainer) {
            productContainer.innerHTML = `<h2>${error.message}</h2>`;
        }
    }
}
```



# Testing

#  Flow Test

* Products are fetched from backend
* Displayed dynamically on the webpage
* Layout is responsive (mobile & desktop)

![PRODUCT-8.png](LAB8-SS/PRODUCT-8.png)

# Persistence Test

* Data remains saved even after server restart



# Console Check

* No CORS errors
* No fetch errors

![PRODUCT-8(2).png](LAB8-SS/PRODUCT-8%282%29.png)


#  Database Testing

# Updated Product Table After Testing

![updatedtestinginpostman.png](LAB8-SS/updatedtestinginpostman.png)


# Postman Testing

# Task 3

* Product Table
  ![CATEGORY.png](LAB8-SS/CATEGORY.png)

* POST Categories

  ![POSTCREATE201-LAB8.png](LAB8-SS/POSTCREATE201-LAB8.png)

* POST method for getting Products
  ![POST.png](LAB8-SS/POST.png)



# Task 4

* POST
  ![POST201CREATED-8.png](LAB8-SS/POST201CREATED-8.png)

* GET Products
 ![GET200OK-8.png](LAB8-SS/GET200OK-8.png)
![GETALLPRODUCTS200OK-8.png](LAB8-SS/GETALLPRODUCTS200OK-8.png)

* PATCH
![PATCH200OK-8.png](LAB8-SS/PATCH200OK-8.png)

* PUT
  ![PUT200OK-8.png](LAB8-SS/PUT200OK-8.png)

* Database Success
  ![SUCCESFULDATABASE.png](LAB8-SS/SUCCESFULDATABASE.png)

* Filtering
  ![GET200OK-BYPRICERANGE-8.png](LAB8-SS/GET200OK-BYPRICERANGE-8.png)
  ![GET200OK-FILTERBYCATEGORY-8.png](LAB8-SS/GET200OK-FILTERBYCATEGORY-8.png)
  ![GET200OK-FILTERBYNAME-8.png](LAB8-SS/GET200OK-FILTERBYNAME-8.png)
  ![GET200OK-FILTERBYPRICE.png](LAB8-SS/GET200OK-FILTERBYPRICE.png)
* Delete
  ![DELETE204NOCONTENT-8.png](LAB8-SS/DELETE204NOCONTENT-8.png)



# Authors

* Jackielyn C. Calambas
* Chris F. Galupo





