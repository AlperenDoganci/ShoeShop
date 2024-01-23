# Programming 3 JAVA-SPRING PROJECT
## ShoeShop
## Author: [Alperen Doganci]

### Project Description:
This project shows the shoes and customers in a shoe shop.
It is a web application that allows to add shoes and customers to the database and to the webpage.
It shows the existing shoes and customers from the database. 
You can see the details of each shoe and customer with the purchases they are associated. You can delete certain shoes and customers by clicking on the corresponding delete button. 

### Table of Contents
- [Project Description](#project-description)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Technologies Used](#technologies-used)
  - [Database](#database)
- [Profiles](#profiles)
- [Domain Model](#domain-model)
  - [Domain Classes](#domain-classes)
  - [Domain Relationships](#domain-relationships)

### Getting Started
You need to have gradle and java installed on your computer together with spring.
You can run the application by the preffered IDE you are using.
You should have JDK 17 installed.
You should set up the database connection for postgresql and h2 database. You can do this in the configuration folder and in the application.properties file. Set up your own credentials for the database connection.
After you have done the steps above you can run the application by running the main method in the main class.
You should open the webpage by typing the url localhost:8080 in your browser.
### Installation
git clone https://gitlab.com/alperendoganci/java-spring-project-alperen-doganci.git

### Technologies Used
- Java
- Spring
- Gradle
- Thymeleaf
- HTML
- CSS
- Bootstrap
### Database
- Postgresql
- H2 Database

### Profiles
You can run the applications with different profiles. Each profile use a different approach. You can set the profile in the application.properties file to the following profiles:
- jdbc_template: This profile uses the jdbc template approach to connect to the h2 memory database.
- jpa: This profile uses the jpa approach to connect to the postgresql database.
- spring_data: This profile uses the spring data approach to connect to the postgresql database.
- list_template: This profile uses the list approach that uses java collections.

## Domain Model
The Shoe Shop is designed to manage the inventory of shoes, customer information, and purchase transactions. 
In this project, you can add new shoes to the system, register customers, and track shoe purchases. The system's domain includes two main classes: Shoe and Customer. Purchase is a side domain class.

### Domain Classes
#### Shoe
The Shoe class represents a shoe entity in the inventory of the store. 
It has the following attributes:

Properties:
- **Brand:** The brand of the shoe.
- **Size:** The size of the shoe.
- **Color:** The color of the shoe.
- **Price:** The price of the shoe.
- **Purchases:** A collection of purchases that includes the shoe.
- **Customers:** A collection of customers who have purchased the shoe.

#### Customer
The Customer class represents an individual who interacts with the shoe store by making purchases.
It has the following attributes:

Properties:
- **FirstName:** The first name of the customer.
- **LastName:** The last name of the customer.
- **Email:** The email address of the customer.
- **PhoneNumber:** The phone number of the customer.
- **DateOfBirth:** The date of birth of the customer.
- **Gender:** (Enum) The gender of the customer
- **Purchases:** A collection of purchases made by the customer.
- **Shoes:** A collection of shoes purchased by the customer.

#### Purchase
The Purchase class represents a transaction where a customer buys a shoe.

Properties:
- **purchaseDate:** The date of the purchase. 
- **totalPrice:** The total price of the purchase.
- **paymentMethod:** (Enum) The payment method of the purchase.
- **shoes:** The shoes that are purchased.
- **customer:** The customer who made the purchase.

## Domain Relationships
The domain model has the following relationships:
### Customer - Purchase (One-to-Many)
- Each customer can make multiple purchases.
- However, each purchase is associated with only one customer.
- This creates a "One-to-Many" relationship between customers and purchases.### Shoe - Purchase (Many-to-Many)
- customers table (customer_id) -> purchases table (customer_id)
### Shoe - Purchase (Many-to-Many)
- A purchase can include multiple shoes.
- A shoe can be part of multiple purchases.
- This forms a "Many-to-Many" relationship between purchases and shoes.
- purchases table (purchase_id) -> purchase_shoe table (purchase_id)
- shoes table (shoe_id) -> purchase_shoe table (shoe_id)
### Shoe - Customer (Many-to-Many)
- A customer can purchase multiple shoes.
- A shoe can be purchased by multiple customers.
- shoes table (shoe_id) -> customer_shoe table (shoe_id)
-  customers table (customer_id) -> customer_shoe table (customer_id)