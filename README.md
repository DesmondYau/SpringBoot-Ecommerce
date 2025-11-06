# SpringBoot-Ecommerce
<img width="749" height="491" alt="JavaEcommerce" src="https://github.com/user-attachments/assets/b7a9cb27-c862-4122-8c36-f47842727984" />




# Description
This is an e-commerce application developed using the **Spring Boot Framework** in a **microservices architecture**:

**👤 Customer Service**
- Manages customer creation, updates, retrieval, and deletion. Supports profile lookup and validation for downstream services.
  
**📦 Product Service**
- Handles product creation, lookup, and inventory updates. Validates stock and processes purchases with transactional integrity.

**🧾 Order Service**
- Processes order creation and order history. Perform **synchronous communication** with Customer and Product services using Spring RestClient to validate and fulfill orders.
- Acts as a **Kafka producer**, publishing order events to a Kafka topic for asynchronous processing by downstream Notification Service

**📣 Notification Service**
- Listens for order-related events as a **Kafka consumer**, enabling asynchronous communication with the Order Service
- Upon receiving an event, it generates and sends email notifications using JavaMailSender

**🚪 Gateway Service**
- Serves as the **unified entry point** to the system. Routes incoming requests to appropriate services

**🧭 Discovery Service**
- Enables dynamic **service registration** and lookup using **Spring Cloud Netflix Eureka**. Ensures services can find and communicate with each other without hardcoded URLs.

**🗂️ Config Server**
- Centralizes configuration for all services using **Spring Cloud Config Server**.

# Technologies

**Spring Boot**
- Spring Cloud Gateway
- Spring RestClient

**Kafka**

**PostgreSQL**

**MongoDB**
