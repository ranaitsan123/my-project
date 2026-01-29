# Testing Auth Service

## Quick Start

Le service d'authentification est disponible sur `http://localhost:8090`

## Endpoints

### 1. SIGNUP (Register)
```bash
curl -X POST http://localhost:8090/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "SecurePassword123"
  }'
```

**Response:**
```json
{
  "success": true,
  "message": "User registered successfully",
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "john_doe"
}
```

---

### 2. LOGIN
```bash
curl -X POST http://localhost:8090/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "SecurePassword123"
  }'
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "john_doe"
}
```

---

### 3. VALIDATE TOKEN
```bash
curl -X GET http://localhost:8090/api/auth/validate \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."
```

**Response:**
```json
{
  "success": true,
  "message": "Token is valid",
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "john_doe"
}
```

---

### 4. LOGOUT
```bash
curl -X POST http://localhost:8090/api/auth/logout \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."
```

**Response:**
```json
{
  "success": true,
  "message": "Logout successful",
  "token": null,
  "username": null
}
```

---

## Testing with Postman

### Collection Setup

1. **Base URL:** `http://localhost:8090/api/auth`

2. **SIGNUP Request:**
   - Method: `POST`
   - URL: `{{BASE_URL}}/signup`
   - Body (JSON):
   ```json
   {
     "username": "testuser",
     "email": "testuser@example.com",
     "password": "password123"
   }
   ```
   - Save the `token` from response

3. **LOGIN Request:**
   - Method: `POST`
   - URL: `{{BASE_URL}}/login`
   - Body (JSON):
   ```json
   {
     "username": "testuser",
     "password": "password123"
   }
   ```
   - Save the `token` from response

4. **VALIDATE TOKEN Request:**
   - Method: `GET`
   - URL: `{{BASE_URL}}/validate`
   - Header: `Authorization: Bearer {{token}}`

5. **LOGOUT Request:**
   - Method: `POST`
   - URL: `{{BASE_URL}}/logout`
   - Header: `Authorization: Bearer {{token}}`

---

## Error Scenarios

### Duplicate Username
```bash
curl -X POST http://localhost:8090/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"username": "john_doe", "email": "another@example.com", "password": "pass123"}'
```

Response:
```json
{
  "success": false,
  "message": "Username already exists",
  "token": null,
  "username": null
}
```

### Invalid Credentials
```bash
curl -X POST http://localhost:8090/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "john_doe", "password": "wrongpassword"}'
```

Response:
```json
{
  "success": false,
  "message": "Invalid password",
  "token": null,
  "username": null
}
```

### Invalid Token
```bash
curl -X GET http://localhost:8090/api/auth/validate \
  -H "Authorization: Bearer invalid_token"
```

Response:
```json
{
  "success": false,
  "message": "Invalid token",
  "token": null,
  "username": null
}
```

---

## Testing with cURL Script

Run the provided test script:
```bash
./test-auth.sh
```

This script will:
1. Create a new user (signup)
2. Log in with those credentials
3. Validate the token
4. Test logout
5. Test error scenarios
