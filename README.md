# E-Commerce API with Spring Boot WebFlux

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [API Endpoints](#api-endpoints)

## Introduction
This project provides a robust e-commerce API built with Java Spring Boot WebFlux. It offers asynchronous and non-blocking operations, ensuring scalable and efficient performance for e-commerce platforms.

## Features

[//]: # (- User Registration & Authentication)
- Product Listing & Management
- Cart Operations
- Order Processing

[//]: # (- Payment Integration)

[//]: # (- Reviews & Ratings)

[//]: # (- Admin Operations)

## Prerequisites
- Java JDK (JDK 17 or higher)
- Maven
- Database (PostgreSQL)

## API Endpoints

[//]: # (### Users & Authentication)

[//]: # (- POST `/api/users/register`: Register a new user)

[//]: # (- POST `/api/users/login`: Authenticate a user)

[//]: # (- GET `/api/users/profile`: Fetch the authenticated user's profile)

[//]: # (- PUT `/api/users/profile`: Update the authenticated user's profile)

[//]: # (- DELETE `/api/users/profile`: Delete user account)

### Products
- GET `/api/products/getProducts`: Fetch all products
- GET `/api/products/getProductById/{productId}`: Fetch a specific product by ID
- POST `/api/products/createProduct`: Add a new product
- PUT `/api/products/updateProduct/{productId}`: Update a specific product by ID
- DELETE `/api/products/deleteProductById/{productId}`: Delete a specific product by ID

### Cart
- GET `/api/cart`: Fetch the authenticated user's cart
- POST `/api/cart`: Add a product to the cart
- PUT `/api/cart/{productId}`: Update quantity of a product in the cart
- DELETE `/api/cart/{productId}`: Remove a product from the cart

### Orders
- GET `/api/orders/getOrders`: Fetch all orders for authenticated user
- GET `/api/orders/getOrderById/{orderId}`: Fetch a specific order by ID
- POST `/api/orders/createOrder`: Place a new order
- PUT `/api/orders/updateOrder/{orderId}`: Update a specific order by ID
- DELETE `/api/orders/deleteOrder/{orderId}`: Cancel an order

[//]: # (### Payments)

[//]: # (- POST `/api/payments/checkout`: Process a payment and checkout)

[//]: # (- GET `/api/payments/history`: Fetch payment history for a user)

[//]: # (### Reviews & Ratings)

[//]: # (- GET `/api/products/{productId}/reviews`: Fetch all reviews for a product)

[//]: # (- POST `/api/products/{productId}/reviews`: Submit a review for a product)

[//]: # (- PUT `/api/reviews/{reviewId}`: Update a review)

[//]: # (- DELETE `/api/reviews/{reviewId}`: Delete a review)

[//]: # (### Admin Operations)

[//]: # (- GET `/api/admin/users`: Fetch all users &#40;admin only&#41;)

[//]: # (- DELETE `/api/admin/users/{userId}`: Delete a user &#40;admin only&#41;)

[//]: # (- GET `/api/admin/orders`: Fetch all orders &#40;admin only&#41;)

[//]: # (- PUT `/api/admin/orders/{orderId}`: Update order status &#40;admin only&#41;)
