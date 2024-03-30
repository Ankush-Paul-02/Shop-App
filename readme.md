# Shop Application

Welcome to the Shop Application! This web-based platform is designed to manage orders, coupons, inventory, and user information for e-commerce activities. Below, you'll find detailed information about the project structure, database schema, and endpoints.

### Shop Application Installation Guide

This guide provides step-by-step instructions on how to install the Shop Application project.

#### Prerequisites

- Java Development Kit (JDK) 21
- IntelliJ IDEA
- MySQL Database Server installed
- Git installed (optional)

#### Installation Steps

1. **Clone the Repository (optional)**:
   If you have Git installed, you can clone the repository using the following command:

   ```bash
   git clone https://github.com/Ankush-Paul-02/Shop-App.git
   ```

   Alternatively, you can download the project as a ZIP archive from the repository's GitHub page.

2. **Database Setup**:

   - Create a new MySQL database named `shop-app`.
   - Execute the SQL scripts provided in the project's `db-scripts` directory to create the necessary tables and seed initial data.

3. **Configuration**:

   - Navigate to the project's `src/main/resources` directory.
   - Open the `application.properties` file.
   - Update the database connection properties (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`) according to your MySQL configuration.

4. **Build the Project**:
   Open a terminal or command prompt, navigate to the root directory of the project, and run the following command to build the project:

   ```bash
   mvn clean install
   ```

5. **Run the Application**:
   After a successful build, run the following command to start the application:

   ```bash
   java -jar target/shop-app.jar
   ```

   Alternatively, you can run the application using Maven:

   ```bash
   mvn spring-boot:run
   ```

6. **Access the Application**:
   Once the application is running, you can access it using the following URL:
   ```
   http://localhost:8081
   ```

## Project Structure

The project follows the MVC (Model-View-Controller) architecture and is structured as follows:

- **controllers**: Contains classes responsible for handling HTTP requests and generating responses.
- **models**: Includes entity classes representing the data structure of the application.
- **repositories**: Contains interfaces for performing CRUD operations on database entities.
- **services**: Includes service classes that implement business logic and interact with repositories.

## Database Information

### Database Name: shop-app

### Tables:

1. **orders**: Stores information about orders placed by users.
2. **coupons**: Stores details of available coupons and their discount percentages.
3. **inventory**: Contains information about available items in the inventory.
4. **order_payments**: Records payment details for orders placed by users.
5. **users**: Stores user information.

## Endpoints

### UserController

- **POST /api/v1/shop/user**: Create a new user.
- **GET /api/v1/shop/user/{id}**: Retrieve user information by ID.
- **GET /api/v1/shop/user/{id}/{coupon}**: Check if a user has used a specific coupon.

### CouponController

- **POST /api/v1/shop/coupon/{couponDiscount}**: Create a new coupon with a specified discount percentage.
- **GET /api/v1/shop/fetchCoupons**: Fetch all available coupons.

### InventoryController

- **POST /api/v1/shop/inventory**: Create a new inventory item.
- **GET /api/v1/shop/inventory**: Retrieve information about the inventory.

### OrderController

- **POST /api/v1/shop/{userId}/order?qty={quantity}&coupon={coupon}**: Place a new order for a user with the specified quantity and coupon.
- **GET /api/v1/shop/{userId}/orders**: Retrieve all orders placed by a user.

### PaymentController

- **POST /api/v1/shop/{userId}/{orderId}/pay?amount={amount}**: Process payment for a specific order.
- **GET /api/v1/shop/{userId}/orders/{orderId}**: Retrieve payment details for a specific order.

## Getting Started

To get started with the Shop Application, follow these steps:

1. Set up the database using the provided schema.
2. Configure the application properties with your database connection details.
3. Run the application.
4. Test the endpoints using an API testing tool like Postman.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL

## Contributors

This project was developed by Ankush Paul.
