# SpringBoot-Ecommerce
<img width="749" height="491" alt="JavaEcommerceOptimize drawio" src="https://github.com/user-attachments/assets/665df29c-8f7b-4075-80d3-9e8a0c7c473b" />

<br>
<br>

<img width="789" height="481" alt="JavaEcommerceBaseline drawio" src="https://github.com/user-attachments/assets/1230baf8-e81d-49a2-9e28-b5d034924679" />


# Overview
This project is an e‑commerce application built with **Spring Boot** and a **microservices architecture**. It evolved from a baseline design into a resilient **event‑driven** system, enhanced with **Redis caching** for high‑performance lookups and **Kafka streaming** for scalable, asynchronous workflows. <br>
The design aims to handle **high‑concurrency scenarios such as flash sales**, supporting accurate inventory management, responsive performance, and dependable order processing under real‑world conditions.


# Optimization

🔄 **Event Streaming: Apache Kafka** <br>

🚀 **Caching: Redis** <br>
- Act as an in-memory key-value store for caching
- Supports atomic increments and decrements of product stock, ensuring concurrency safety when multiple users buy the same product at once
- Caching product details and stock quantities in Redis to reduce the number of database queries, lowering latency and offloading the database during high traffic


# Service Description

**👤 Customer Service**
- Manages customer creation, updates, retrieval, and deletion. Supports customer lookup and validation during purchase
  
**📦 Product Service**
- Manages product catalog operations, leverages **Redis caching** for fast product lookups. Ensures transactional integrity (e.g. product and stock validation) during purchases and act as **Kafka producer** to publish stock changes **asynchronously**

**📣 Stock Service**
- Manages inventory data, **publishing stock levels to Redis** for fast access and retrieval. Acts as a **Kafka consumer**, applying **event‑driven** stock synchronisation to update database quantities and enforce consistency across the system.
  
**🧾 Order Service**
- Manages order creation and retrieval, using **Spring RestClient** to perform **synchronous communication** with Customer Service and Product Service for validation. Calculates total amount, maintains order history, and enforces transactional integrity
  
**🚪 Gateway Service**
- Serves as the **unified entry point** to the system. Routes incoming requests to appropriate services

**🧭 Discovery Service**
- Enables dynamic **service registration** and lookup using **Spring Cloud Netflix Eureka**. Ensures services can find and communicate with each other without hardcoded URLs.

**🗂️ Config Server**
- Centralizes configuration for all services using **Spring Cloud Config Server**.


# Technologies
🧱 **Core Frameworks & Libraries:** Spring Boot, Spring Cloud Gateway, Spring RestClient,  Spring Cloud Netflix Eureka,  Spring Cloud Config Server <br>
🔄 **Message Streaming:** Apache Kafka <br>
🚀 **Caching:** Redis <br>
🛢️ **Databases:** PostgreSQL <br>

