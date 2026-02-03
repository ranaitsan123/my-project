# My Project

A small microservice example for university management (students, formations, inscriptions) with an authentication service and a simple static frontend — built with Spring Boot, MySQL and Docker.

## Table of contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Quick start (Docker)](#quick-start-docker)
- [Local development](#local-development)
- [Running tests](#running-tests)
- [Useful docs](#useful-docs)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

---

## Overview
This repository demonstrates a compact microservices architecture (auth, student, formation, inscription) with a static frontend and a MySQL backend initialized from SQL scripts in `mysql-init/`.

## Architecture

## 🧱 Architecture Overview

The system is composed of multiple independent services, each with its own database schema, all orchestrated using **Docker Compose**.

```

Client (Frontend)
|
v
Nginx API Gateway
|
-

|        |            |            |            |
Auth   Student     Formation   Inscription   MySQL
Service Service     Service     Service

```

---

## 📦 Services

### 🔐 Auth Service
- Handles authentication and authorization
- Uses **JWT** for stateless security
- Exposes login/register endpoints
- Port: `8090 → 8080 (container)`

### 👨‍🎓 Student Service
- Manages student data
- Connected to its own MySQL database

### 📚 Formation Service
- Manages formation/course data
- Connected to its own MySQL database

### 📝 Inscription Service
- Manages student inscriptions
- Connected to its own MySQL database

### 🌐 API Gateway (Nginx)
- Acts as a reverse proxy
- Routes requests to backend services
- Serves the frontend
- Exposed on port `8080`

### 🛢 MySQL
- Single MySQL container
- Multiple databases initialized at startup
- Persistent volume for data storage

---

## 📁 Project Structure

```

.
├── auth-service
├── student-service
├── formation_service
├── inscription_service
├── frontend
├── mysql-init
├── nginx.conf
├── docker-compose.yml
├── AUTH_SERVICE_COMPLETE_GUIDE.md
├── AUTH_TEST_GUIDE.md
├── test-auth.sh
└── project_dump_backend.txt

````

---

## ⚙️ Prerequisites

Make sure you have the following installed:

- Docker
- Docker Compose
- Git
- Java 17+ (for local development)

---

## 🚀 Getting Started

### 1️⃣ Clone the repository
```bash
git clone <your-repo-url>
cd my-project
````

### 2️⃣ Start all services

```bash
docker-compose up --build
```

This will:

* Start MySQL
* Build and run all Spring Boot services
* Start Nginx as the API Gateway
* Serve the frontend

---

## 🔑 Environment Configuration

Key environment variables are defined in `docker-compose.yml`:

```env
MYSQL_ROOT_PASSWORD=root
JWT_SECRET=your_secret_key
JWT_EXPIRATION_MS=3600000
```

⚠️ **Do not use these secrets in production.**

---

## 🌍 Ports

| Service      | Host Port | Container Port |
| ------------ | --------- | -------------- |
| API Gateway  | 8080      | 80             |
| Auth Service | 8090      | 8080           |
| MySQL        | internal  | 3306           |

---

## 🧪 Testing

### Auth Service Tests

You can test authentication endpoints using:

```bash
./test-auth.sh
```

Additional documentation:

* `AUTH_SERVICE_COMPLETE_GUIDE.md`
* `AUTH_TEST_GUIDE.md`

---

## 🐳 Docker Notes

* MySQL health checks ensure services only start when DB is ready
* Databases are initialized from `mysql-init/`
* Data is persisted using Docker volumes

---

## 📌 Future Improvements

* Add service discovery (Eureka / Consul)
* Centralized configuration
* API documentation with Swagger
* Monitoring & logging (Prometheus, Grafana)

---

## 👨‍💻 Author

Built as a learning and practice project for **microservices architecture**, **Spring Boot**, and **Docker**.

---

## 📜 License

This project is for educational purposes.

