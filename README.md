# 🚀 SecureAuth - Full Stack Authentication System

SecureAuth is a robust and secure user authentication system built using **Spring Boot**, **Spring Security**, **ReactJS**, and **MySQL**. It provides a modern solution for user login, registration, email verification, JWT-based authentication, and password recovery — all while following industry best practices in security and architecture.

---

## 🛠️ Tech Stack

**Backend:**
- Spring Boot
- Spring Security
- JavaMailSender
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate ORM
- MySQL

**Frontend:**
- ReactJS
- Axios
- React Router DOM
- Bootstrap

---
## 🧪 API Endpoints
Base Url :

```bash
http://localhost:8080
```

| Method | Endpoint                                  | Description                                 |
|--------|-------------------------------------------|---------------------------------------------|
| POST   | `/api/v1.0/register`                      | Register a new user                         |
| POST   | `/api/v1.0/login`                         | Authenticate user and return JWT            |
| POST   | `/api/v1.0/send-reset-otp?email=...`      | Send OTP to email for password reset        |
| POST   | `/api/v1.0/reset-password`                | Reset password using OTP                    |
| POST   | `/api/v1.0/send-otp`                      | Send OTP to user for verification           |
| POST   | `/api/v1.0/verify-otp`                    | Verify the OTP sent to user email           |
| GET    | `/api/v1.0/profile`                       | Get user profile (secured route)            |
| GET    | `/api/v1.0/is-authenticated`              | Check if JWT token is valid and authenticated|

---

## 🔐 Key Features

- ✅ **User Registration with Email Verification**
- 🔐 **JWT-Based Authentication**
- 🔁 **Login and Token Refresh**
- 🧠 **Role-Based Access Control (Admin/User)**
- 🔃 **Forgot Password and Reset Flow**
- 📧 **Secure Email Notifications via JavaMail**
- 📜 **Token Expiration Handling**
- 🧪 **Secure API Access using Spring Security Filter Chain**
- 📈 **Scalable Codebase for Future Enhancements**

---

## 📷 Screenshots

> _Add UI screenshots here if available (login, register, dashboard, etc.)_

---

## 🧰 Getting Started

### Prerequisites

- Java 17+
- Node.js and npm
- MySQL
- Maven

### Backend Setup

1. Clone the repo
   ```bash
   git clone https://github.com/your-username/SecurePath.git
   cd backend

2. Configure application.properties
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/securepath_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password

   spring.mail.username=your_email@gmail.com
   spring.mail.password=your_password
   jwt.secret=your_jwt_secret_key

3. Build and run the backend
   ```bash
   mvn clean install
   mvn spring-boot:run

