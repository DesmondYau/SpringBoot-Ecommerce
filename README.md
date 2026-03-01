# SpringBoot-Ecommerce
<img width="749" height="491" alt="JavaEcommerceOptimize drawio" src="https://github.com/user-attachments/assets/665df29c-8f7b-4075-80d3-9e8a0c7c473b" />

<br>
<br>

<img width="789" height="481" alt="JavaEcommerceBaseline drawio" src="https://github.com/user-attachments/assets/1230baf8-e81d-49a2-9e28-b5d034924679" />


# Overview
This project is an e‑commerce application built with **Spring Boot** and a **microservices architecture**. It evolved from a baseline design into a resilient **event‑driven** system, enhanced with **Redis caching** for high‑performance lookups and **Kafka streaming** for scalable, asynchronous workflows. <br>
The design aims to handle **high‑concurrency scenarios such as flash sales**, supporting accurate inventory management, responsive performance, and dependable order processing under real‑world conditions.

<br>
<br>

# Optimization

🔄 **Message Streaming: Apache Kafka** <br>
- **Product Service publishes** stock decrement events, and **Stock Service consumes** events to update the database, keeping Redis and the database aligned.
- Supports **asynchronous event processing**, product purchase flows do not wait for database updates; stock changes are published to Kafka and processed later, reducing latency for customers

🚀 **Caching: Redis** <br>
- Act as an in-memory data structure store for caching
- **Caching product details and stock quantities**, reducing the number of database queries, lowering latency and offloading the database during high traffic
- Supports **atomic increments and decrements of product stock**, ensuring concurrency safety when multiple users buy the same product at once

<br>
<br>

# Performance Test in Jmeter
We conducted load testing with **10,000 requests** (1000 threads with 10 loops) using Apache JMeter to evaluate system performance before and after optimization with **Redis caching** and **Kafka event streaming**.

### Product Read Request
| Metric                    | Baseline (Before Optimization)| Optimized (Redis + Kafka) |
|---------------------------|-------------------------------|---------------------------|
| **Average Response Time** | 272 ms                        | 32 ms                     |
| **Median Response Time**  | 199 ms                        | 29 ms                     |
| **90th Percentile**       | 652 ms                        | 54 ms                     |
| **Throughput**            | 789 requests/sec              | 991 requests/sec          |
| **Error Rate**            | 0%                            | 0%                        |

**Improvement:** Response time reduced from **272 ms → 32 ms** (≈8.5x faster) and throughput increased from **789/sec → 991/sec** (≈25% higher)


### Order Request
| Metric                    | Baseline (Before Optimization) | Optimized (Redis + Kafka)|
|---------------------------|--------------------------------|--------------------------|
| **Average Response Time** | 8269 ms                        | 5533 ms                  |
| **Median Response Time**  | 8704 ms                        | 5766 ms                  |
| **90th Percentile**       | 10498 ms                       | 7475 ms                  |
| **Throughput**            | 104 requests/sec               | 146 requests/sec         |
| **Error Rate**            | 0%                             | 0%                       |

**Improvement:** Response time reduced from **8269 ms → 5533 ms** (≈33% faster) and throughput increased from **104/sec → 146/sec** (≈40% higher)

### 🚀 Summary
By integrating **Redis** for in‑memory caching, and **Kafka** for asynchronous event streaming, the system demonstrated measurable performance gains under load testing:

- **Product reads** improved from hundreds of milliseconds to tens of milliseconds, with throughput rising from ~789/sec to ~991/sec.  
- **Order requests** improved from several seconds to around 5 seconds, with throughput rising from ~104/sec to ~146/sec.  

These results highlight how caching and event streaming can reduce latency and increase throughput in an e‑commerce application. The improvements illustrate the impact of architectural choices on scalability and responsiveness.

<br>
<br>

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

