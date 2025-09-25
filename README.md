# Cafe-94 Restaurant API

## About this Repository 

This repository contains an API, created with Spring, for an all-in-one management system for a local restaurant.

The system is comprised of multiple features that are common to retail and food outlets.

It will provide login functionality for both customers and staff. Customers can create profile that includes their name, address, 
order history and any events they are attending. Staff profiles include their name, shifts and total hours worked

Customers can check availability and book a table for a given date. They can also view information about the menu, create orders (in-house, takeaway and delivery),
as well as book and register for catered events.

Staff will be divided into waiters, managers, chefs and delivery drivers. The system will allow staff members to carry
out common tasks associated with their respective roles. This includes handling customers' booking, event and order requests. 

Managers can also view weekly reports that include the busiest restaurant periods, most popular menu items, the staff member who worked
the most hours, as well as the most active customer

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/ifanmo/spring-boot-restaurant
cd restaurant-api
```

### 2. Configure Environment Variables
- Rename the ``.env.example`` file to ``.env``.
- Update the following environment variables inside .env:

#### JWT_SECRET

Generate a secure random key using:

```bash
openssl rand -base64 32
```
## ▶️ Running the Project

This is a Maven project. To start the application, run:

```bash
./mvnw spring-boot:run
```

If you're on Windows:

```bash
mvnw.cmd spring-boot:run
```

Once running, the application will be available at:

```arduino
http://localhost:8080
```

---

## 📚 API Documentation

Swagger UI is available at:

```bash
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Example API Flow

Here's a sample flow to help you understand how to interact with the API after starting the application.

### 1. Get All Menu Items

```bash
GET /menu
```

The database is automatically populated with 10 sample menu items using a Flyway migration script.

### 2. Create a Cart

```bash
POST /carts
```

This will return the cart ID. You don't need to be logged in to create a cart.

### 3. Add Menu Items to Cart

Once you have a cart ID, you can add products to it by sending:

```bash
POST /carts/{cartId}/items
```

**Request body example**:
```json
{
  "productId": 1
}
```

### 4. Register a New Customer
To check out, you have to register as a customer and login:

```bash
POST /users
```

**Request body**:

```json
{
  "email": "user1@example.com",
  "password": "password",
  "role": "CUSTOMER"
}
```

### 5. Login to Get an Access Token

```bash
POST /auth/login 
```

**Request body**:
```json
{
  "email": "user1@example.com",
  "password": "password"
}
```

**Response body**:
```json
{
  "token": "generated-json-web-token"
}
```

### 6. Checkout
You can checkout either a restaurant, delivery or takeout order by changing the request body

```bash
POST /checkout 
```

**Headers**
```bash
Authorization: Bearer generated-json-web-token
```

**Request body**
```json
{
  "cartId": "your-cart-id"
}
```
