# SpringBoot-Ecommerce
<img width="749" height="491" alt="JavaEcommerce" src="https://github.com/user-attachments/assets/b7a9cb27-c862-4122-8c36-f47842727984" />




# Description
This is an e-commerce application developed using Spring Boot and Java in microservices architecture:

**👤 Customer Service**

**📦 Product Service**

**🧾 Order Service**

**📣 Notification Service**
- Acts as a Kafka producer, publishing order events to a Kafka topic for asynchronous processing by downstream consumers such as the Notification Service

**🚪 Gateway Service**
- Serves as the unified entry point to the system. Routes incoming requests to appropriate services

**🧭 Discovery Service**
- Enables dynamic service registration and lookup using Spring Cloud Netflix Eureka. Ensures services can find and communicate with each other without hardcoded URLs.

**🗂️ Config Server**


# Technologies

**Spring Boot**
- Spring Cloud Gateway
- Spring RestClient

**Kafka**

**PostgreSQL**

**MongoDB**
