# Spring Boot + Amazon Cognito 

This project demonstrates how to integrate a Spring Boot application with:

- **Amazon Cognito Hosted UI** for authentication (OAuth2)
- **Custom Logout** that signs out of both the app and Cognito Hosted UI session

---

## 🚀 Features

- Secure login using Cognito Hosted UI
- Fully OAuth2-compliant Spring Security configuration
- Clean custom logout handler (no Spring logout screen)
- Access to AWS services using credentials from Cognito tokens
- Example of public `logged-out` page
- No login loop after logout

---

## 🛠️ Technologies Used

- Spring Boot 3.x
- Spring Security (OAuth2 Client)
- AWS Cognito (Hosted UI)
- AWS SDK for Java v2

---

## 📦 Prerequisites

- Java 17+
- AWS account with:
  - Cognito User Pool + App Client (Hosted UI)

---

## ⚙️ Configuration

### 1. **Cognito Setup**

In your AWS Cognito User Pool:

- **App Client** (with Hosted UI enabled)
- **Callback URL**:  
  `http://localhost:8080/login/oauth2/code/cognito`
- **Sign-out URL**:  
  `http://localhost:8080/logged-out`
- Enable `Authorization code grant` flow and `openid` scope

---

### 2. **application.yml**

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          cognito:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
            provider: cognito
            redirect-uri: "{baseUrl}/login/oauth2/code/cognito"
            scope: openid
        provider:
          cognito:
            issuer-uri: https://YOUR_DOMAIN.auth.YOUR_REGION.amazoncognito.com
---

🔐 Custom Logout
Instead of Spring’s default /logout, the app uses /custom-logout, which:

Invalidates Spring session

Redirects to Cognito logout endpoint

Cognito signs out Hosted UI session

User is redirected to /logged-out

---
🧪 Running the App
bash
Copy
Edit
./mvnw spring-boot:run
