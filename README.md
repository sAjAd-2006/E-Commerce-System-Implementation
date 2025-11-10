# Vendilo — Online Store

Vendilo is a command-line online store project written in Java.  
It focuses on clean object-oriented design, robust business logic, and role-based features.

## Key Features
- User registration & login (Customer, Seller, Support, Admin)
- Product search, filtering, and sorting
- Shopping cart, orders, and wallet system  
  - Customers can top up wallet  
  - Sellers receive 90% of each sale and can withdraw
- Address management and shipping cost rules
- Support requests & seller verification workflow
- Promo codes and Vendilo+ subscription (5% discount, shipping perks)
- Notifications system

## Business Rules
- Strong password policy (≥8 chars, upper/lowercase, digit, special char)
- Unique and valid email/phone
- Sellers must be verified before accessing store features
- Each user can rate a product only once

## Checklist
- ✅ Registration/login  
- ✅ Product search & cart  
- ✅ Orders & wallet logic  
- ✅ Error handling & validations  
- ✅ Unit tests (JUnit 5)

## Extensions
- Password hashing  
- Reports & charts  
- Fuzzy search  
- Auto-replies for support
