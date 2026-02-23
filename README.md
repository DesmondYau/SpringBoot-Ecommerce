# SpringBoot-Ecommerce
<img width="749" height="491" alt="JavaEcommerce" src="https://github.com/user-attachments/assets/e7d9160c-2fa3-4a63-9876-a73ef1fcda56" />





# Description
This is an e-commerce application developed using the **Spring Boot** in a **microservices architecture**:

**👤 Customer Service**
- Manages customer creation, updates, retrieval, and deletion. Supports profile lookup and validation for downstream services.
  
**📦 Product Service**
- Handles product creation, lookup, and inventory updates. Validates stock and processes purchases with transactional integrity.

**🧾 Order Service**
- Processes order creation and history. Performs **synchronous communication** with Customer and Product services using **Spring RestClient** to validate and fulfill orders
- Acts as a **Kafka producer**, publishing order events to a Kafka topic for **asynchronous processing** by downstream consumers such as the Notification Service.

**📣 Notification Service**
- Listens for order-related events as a **Kafka consumer**, enabling asynchronous communication with the Order Service.
- Upon receiving an event, it generates and sends email notifications using JavaMailSender.
  
**🚪 Gateway Service**
- Serves as the **unified entry point** to the system. Routes incoming requests to appropriate services

**🧭 Discovery Service**
- Enables dynamic **service registration** and lookup using **Spring Cloud Netflix Eureka**. Ensures services can find and communicate with each other without hardcoded URLs.

**🗂️ Config Server**
- Centralizes configuration for all services using **Spring Cloud Config Server**.

# Technologies

🧱 Core Frameworks & Libraries
- 🚀 Spring Boot – Base framework for building microservices
- 🚪 Spring Cloud Gateway – API gateway for routing and filtering
- 🔗 Spring RestClient – HTTP client for synchronous inter-service communication
- 🧭 Spring Cloud Netflix Eureka – Service discovery and registration
- 🗂️ Spring Cloud Config Server – Centralized configuration management
- 📧 JavaMailSender – Email delivery for notifications


🔄 Messaging & Streaming
- 📡 Apache Kafka – Event streaming platform for asynchronous communication
  - Producer: Order Service
  - Consumer: Notification Service


🛢️ Databases
- 🐘 PostgreSQL – Relational database for structured data (Customer, Product, Order)


🛠️ Build & Dependency Management
- 📦 Maven – Project build and dependency management
