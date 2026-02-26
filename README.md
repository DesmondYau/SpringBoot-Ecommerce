# SpringBoot-Ecommerce
<img width="749" height="491" alt="JavaEcommerceOptimize drawio" src="https://github.com/user-attachments/assets/2fd73a51-fc93-4979-b656-9da167c720f8" />


# Overview
This project is an e‑commerce application built with **Spring Boot** and a **microservices architecture**. It evolved from a baseline design into a resilient **event‑driven** system, enhanced with **Redis caching** for high‑performance lookups and **Kafka streaming** for scalable, asynchronous workflows. The design aims to handle **high‑concurrency scenarios such as flash sales**, supporting accurate inventory management, responsive performance, and dependable order processing under real‑world conditions.


# Optimization

🔄 **Event Streaming: Apache Kafka** <br>

🚀 **Caching: Redis** <br>


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

