#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Base URL
BASE_URL="http://localhost:8090/api/auth"

echo -e "${BLUE}=== AUTH SERVICE TEST SCRIPT ===${NC}\n"

# Test 1: SIGNUP
echo -e "${BLUE}1. Testing SIGNUP${NC}"
SIGNUP_RESPONSE=$(curl -s -X POST "$BASE_URL/signup" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "testuser@example.com",
    "password": "password123"
  }')

echo "Response: $SIGNUP_RESPONSE"
TOKEN=$(echo $SIGNUP_RESPONSE | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo -e "${GREEN}Token obtained: $TOKEN${NC}\n"

# Test 2: LOGIN
echo -e "${BLUE}2. Testing LOGIN${NC}"
LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }')

echo "Response: $LOGIN_RESPONSE"
TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo -e "${GREEN}Token obtained: $TOKEN${NC}\n"

# Test 3: VALIDATE TOKEN
echo -e "${BLUE}3. Testing TOKEN VALIDATION${NC}"
VALIDATE_RESPONSE=$(curl -s -X GET "$BASE_URL/validate" \
  -H "Authorization: Bearer $TOKEN")

echo "Response: $VALIDATE_RESPONSE\n"

# Test 4: LOGOUT
echo -e "${BLUE}4. Testing LOGOUT${NC}"
LOGOUT_RESPONSE=$(curl -s -X POST "$BASE_URL/logout" \
  -H "Authorization: Bearer $TOKEN")

echo "Response: $LOGOUT_RESPONSE\n"

# Test 5: FAILED LOGIN (wrong password)
echo -e "${BLUE}5. Testing FAILED LOGIN (wrong password)${NC}"
FAILED_LOGIN=$(curl -s -X POST "$BASE_URL/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "wrongpassword"
  }')

echo "Response: $FAILED_LOGIN\n"

# Test 6: SIGNUP DUPLICATE USER
echo -e "${BLUE}6. Testing SIGNUP with duplicate username${NC}"
DUPLICATE_SIGNUP=$(curl -s -X POST "$BASE_URL/signup" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "anotheruser@example.com",
    "password": "password123"
  }')

echo "Response: $DUPLICATE_SIGNUP\n"

echo -e "${GREEN}=== ALL TESTS COMPLETED ===${NC}"
