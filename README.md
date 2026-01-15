
# Flash Sale Backend System

A backend system that simulates a high-traffic flash sale scenario (e.g., Big Billion Days),
focusing on correctness under concurrency rather than raw scale.

## 🚀 Problem Statement

During flash sales, thousands of users attempt to purchase a limited-stock product at the same time.
Naive implementations often oversell inventory due to race conditions.

This project demonstrates how to:
- Prevent overselling under concurrent requests
- Handle failures gracefully
- Validate correctness using load testing

---

## 🏗 Architecture Overview

Client  
→ Spring Boot REST API  
→ Service Layer (@Transactional)  
→ MySQL (Atomic Update)  

Each request is handled by a separate thread.
Concurrency control is delegated to the database.

---

## 🔑 Key Features

- Atomic inventory decrement using MySQL
- Transactional order creation
- Graceful out-of-stock handling
- Global exception handling
- Load tested using Apache JMeter

---

## 🧠 Core Concurrency Logic

Instead of:
```java
read stock → update stock
