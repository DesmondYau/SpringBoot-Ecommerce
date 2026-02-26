# SpringBoot-Ecommerce
<img width="749" height="491" alt="JavaEcommerceOptimize drawio" src="https://github.com/user-attachments/assets/2fd73a51-fc93-4979-b656-9da167c720f8" />


# Overview
This project is an e‑commerce application built with **Spring Boot** and designed using a **microservices architecture**. It demonstrates service decomposition, synchronous and asynchronous communication, centralized configuration, and service discovery. The system evolved from a basic design into an optimized architecture with **redis caching** and **kafka event streaming** to improve scalability and performance.

# Service Description
This is an e-commerce application developed using the **Spring Boot** in a **microservices architecture**:

**👤 Customer Service**
- Manages customer creation, updates, retrieval, and deletion. Supports profile lookup and validation for downstream services
  
**📦 Product Service**
- Manages product catalog operations, leverages **Redis caching** for fast lookups
- Ensures transactional integrity (e.g. product and stock validation) during purchases and act as **Kafka producer** to publish stock changes **asynchronously**

**📣 Stock Service**
- Listens for stock change events as a **Kafka consumer**, enabling asynchronous communication with the Order Service
- Upon receiving an event, it generates and sends email notifications using JavaMailSender

**🧾 Order Service**
- Manages order creation and retrieval, using **Spring RestClient** to validate customers and products **synchronously** before persisting transactions. 
- Calculates total amount, maintains history, and enforces transactional integrity across services
  
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


🔄 Messaging & Streaming
- 📡 Apache Kafka – Event streaming platform for asynchronous communication
  - Producer: Order Service
  - Consumer: Notification Service


🛢️ Databases
- 🐘 PostgreSQL – Relational database for structured data (Customer, Product, Order)


🛠️ Build & Dependency Management
- 📦 Maven – Project build and dependency management
