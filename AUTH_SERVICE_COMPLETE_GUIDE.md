# Guide de Test - Service d'Authentification

## Service d'Authentification - Endpoints

Le service d'authentification est maintenant opérationnel sur **`http://localhost:8090`**

### Architecture
- **Base URL:** `http://localhost:8090/api/auth`
- **Format:** JSON
- **Port externe:** 8090 (mappé vers 8080 interne)

---

## ✅ Endpoints Disponibles

### 1️⃣ SIGNUP - Inscription d'un nouvel utilisateur

**Endpoint:**
```
POST /api/auth/signup
```

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePassword123"
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "User registered successfully",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTY0OTg4NjAwMCwiZXhwIjoxNjQ5ODg5NjAwfQ.abc...",
  "username": "john_doe"
}
```

**Error Responses:**
```json
// Utilisateur déjà existant
{
  "success": false,
  "message": "Username already exists",
  "token": null,
  "username": null
}

// Email déjà utilisé
{
  "success": false,
  "message": "Email already exists",
  "token": null,
  "username": null
}
```

---

### 2️⃣ LOGIN - Se connecter

**Endpoint:**
```
POST /api/auth/login
```

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "SecurePassword123"
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTY0OTg4NjAwMCwiZXhwIjoxNjQ5ODg5NjAwfQ.abc...",
  "username": "john_doe"
}
```

**Error Responses:**
```json
// Utilisateur non trouvé
{
  "success": false,
  "message": "User not found",
  "token": null,
  "username": null
}

// Mot de passe incorrect
{
  "success": false,
  "message": "Invalid password",
  "token": null,
  "username": null
}

// Compte désactivé
{
  "success": false,
  "message": "User account is disabled",
  "token": null,
  "username": null
}
```

---

### 3️⃣ VALIDATE - Valider un token JWT

**Endpoint:**
```
GET /api/auth/validate
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTY0OTg4NjAwMCwiZXhwIjoxNjQ5ODg5NjAwfQ.abc...
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Token is valid",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTY0OTg4NjAwMCwiZXhwIjoxNjQ5ODg5NjAwfQ.abc...",
  "username": "john_doe"
}
```

**Error Response:**
```json
{
  "success": false,
  "message": "Invalid token",
  "token": null,
  "username": null
}
```

---

### 4️⃣ LOGOUT - Se déconnecter

**Endpoint:**
```
POST /api/auth/logout
```

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTY0OTg4NjAwMCwiZXhwIjoxNjQ5ODg5NjAwfQ.abc...
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Logout successful",
  "token": null,
  "username": null
}
```

**Error Response:**
```json
{
  "success": false,
  "message": "Invalid token",
  "token": null,
  "username": null
}
```

---

## 🧪 Tests avec cURL

### Étape 1: Inscription (SIGNUP)
```bash
curl -X POST http://localhost:8090/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "testuser@example.com",
    "password": "TestPass123!"
  }' | json_pp
```

Sauvegardez le `token` reçu pour les tests suivants.

### Étape 2: Connexion (LOGIN)
```bash
TOKEN="<token_from_signup>"

curl -X POST http://localhost:8090/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "TestPass123!"
  }' | json_pp
```

### Étape 3: Validation du Token
```bash
TOKEN="<token_from_login>"

curl -X GET http://localhost:8090/api/auth/validate \
  -H "Authorization: Bearer $TOKEN" | json_pp
```

### Étape 4: Déconnexion (LOGOUT)
```bash
curl -X POST http://localhost:8090/api/auth/logout \
  -H "Authorization: Bearer $TOKEN" | json_pp
```

### Étape 5: Test - Token Invalide Après Logout
```bash
curl -X GET http://localhost:8090/api/auth/validate \
  -H "Authorization: Bearer $TOKEN" | json_pp
```

---

## 📮 Tests avec Postman

### 1. Importer la collection

Créez une nouvelle collection Postman avec les variables:
```
@BASE_URL = http://localhost:8090/api/auth
@TOKEN = (sera défini après login/signup)
```

### 2. Créer les requêtes

#### SIGNUP Request
```
Method: POST
URL: {{@BASE_URL}}/signup
Headers:
  Content-Type: application/json

Body (raw):
{
  "username": "postman_user",
  "email": "postman@example.com",
  "password": "PostmanPass123!"
}

Pre-request Script:
// Aucun script

Tests Script:
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("TOKEN", jsonData.token);
    pm.test("Signup successful", function () {
        pm.expect(jsonData.success).to.be.true;
        pm.expect(jsonData.token).to.exist;
    });
}
```

#### LOGIN Request
```
Method: POST
URL: {{@BASE_URL}}/login
Headers:
  Content-Type: application/json

Body (raw):
{
  "username": "postman_user",
  "password": "PostmanPass123!"
}

Tests Script:
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("TOKEN", jsonData.token);
    pm.test("Login successful", function () {
        pm.expect(jsonData.success).to.be.true;
        pm.expect(jsonData.message).to.equal("Login successful");
    });
}
```

#### VALIDATE Request
```
Method: GET
URL: {{@BASE_URL}}/validate
Headers:
  Authorization: Bearer {{TOKEN}}

Tests Script:
pm.test("Token is valid", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.success).to.be.true;
    pm.expect(jsonData.username).to.exist;
});
```

#### LOGOUT Request
```
Method: POST
URL: {{@BASE_URL}}/logout
Headers:
  Authorization: Bearer {{TOKEN}}

Tests Script:
pm.test("Logout successful", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.success).to.be.true;
    pm.expect(jsonData.message).to.equal("Logout successful");
});
```

---

## 🔐 Détails du JWT Token

### Structure
```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTY0OTg4NjAwMCwiZXhwIjoxNjQ5ODg5NjAwfQ.signature
   ↑ Header              ↑ Payload (Claims)                              ↑ Signature
```

### Claims
- **`sub`** (subject): Nom d'utilisateur
- **`iat`** (issued at): Timestamp de création
- **`exp`** (expiration): Timestamp d'expiration

### Configuration
- **Algorithm:** HS512
- **Secret:** `MySuperSecretKeyForJWTs1234567890`
- **Expiration:** 3600000ms (1 heure)

---

## 🗄️ Base de Données

Les utilisateurs sont stockés dans MySQL:
- **Database:** `auth_service`
- **Table:** `users`
- **Champs:**
  - `id` (AUTO_INCREMENT)
  - `username` (UNIQUE, NOT NULL)
  - `email` (UNIQUE, NOT NULL)
  - `password` (NOT NULL, bcrypt-encoded)
  - `is_enabled` (BOOLEAN, default=true)
  - `created_at` (TIMESTAMP)
  - `updated_at` (TIMESTAMP)

---

## 🔄 Processus de Test Complet

```bash
#!/bin/bash

# 1. SIGNUP
echo "=== SIGNUP ==="
RESPONSE=$(curl -s -X POST http://localhost:8090/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "TestPass123!"
  }')
TOKEN=$(echo $RESPONSE | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo "Token: $TOKEN"
echo ""

# 2. LOGIN
echo "=== LOGIN ==="
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8090/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "TestPass123!"
  }')
echo $LOGIN_RESPONSE | json_pp
TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo ""

# 3. VALIDATE
echo "=== VALIDATE TOKEN ==="
curl -s -X GET http://localhost:8090/api/auth/validate \
  -H "Authorization: Bearer $TOKEN" | json_pp
echo ""

# 4. LOGOUT
echo "=== LOGOUT ==="
curl -s -X POST http://localhost:8090/api/auth/logout \
  -H "Authorization: Bearer $TOKEN" | json_pp
```

---

## ✨ Prochaines Étapes

- Intégrer les endpoints d'authentification dans le gateway Nginx
- Ajouter un filtre JWT pour les autres microservices
- Implémenter la refresh token
- Ajouter les rôles et permissions (roles-based access control)
