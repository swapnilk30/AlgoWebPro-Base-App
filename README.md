
# ✅ **Project Structure**

```
src/main/java/com/algowebpro/
│
├── config/
│			   ModelMapperConfig.java
│			   SwaggerConfig.java
│
├── common/
│   ├── constants/
│	  │	     AppConstants.java
│	  ├── utils/
│	  │	     MappingUtil.java
│   ├── response/
│   │      ErrorResponse.java
│   │      ApiResponse.java
│   │      SuccessResponse.java
|   |      PageableResponse.java
│	  ├── exception/
│   │      GlobalExceptionHandler.java
│   │      ResourceNotFoundException.java
│
├── security/
│    ├── config/
│    │      SecurityConfig.java
│    │      AuthenticationProviderConfig.java
│    │
│    ├── filter/
│    │      JwtAuthenticationFilter.java
│    │
│    ├── service/
│    │      JwtService.java
│    │      CustomUserDetailsService.java
│    │
│    ├── model/
│    │      AuthRequest.java
│    │      AuthResponse.java
│    │
│    └── util/
│           JwtUtils.java  (optional)
│
├── ums/
│
└── ems/
│
│
├── docs/
│   ├── notes/
│   │    ├── spring-boot-basics.md
│   │    ├── jwt-authentication.md
│   │    ├── exception-handling.md
│   │    ├── lombok-builder.md
│   │    └── security-config.md
│   │
│   └── architecture/
│        ├── project-structure.md
│        ├── sequence-diagrams.md
│        └── api-flow.md
```


---

### 🧩 Branch Merge Workflow (bootstrap-ui → master)

1. **Bootstrap Integration**

   * Added **Bootstrap v5.3.8** manually under
     `src/main/resources/static/css` and `src/main/resources/static/js`
   * Linked Bootstrap files in Thymeleaf templates for responsive UI.

2. **Check status and commit all changes**

   ```bash
   git status
   git add .
   git commit -m "Integrated Bootstrap v5.3.8 and finalized UI updates"
   ```

3. **Switch to master branch**

   ```bash
   git checkout master
   git pull origin master
   ```

4. **Merge feature branch into master**

   ```bash
   git merge bootstrap-ui
   ```

5. **Push merged changes to remote**

   ```bash
   git push origin master
   ```

6. **Delete merged branch (cleanup)**

   ```bash
   git branch -d bootstrap-ui
   git push origin --delete bootstrap-ui
   ```

---

✅ *Bootstrap v5.3.8 successfully integrated and merged into the master branch for production-ready UI.*

---

# 🚀 Features Implemented

## ✅ **1. Global Exception Handling**

A complete centralized exception-handling mechanism using `@RestControllerAdvice`.

**Includes:**

* Custom exceptions:

  * `ResourceNotFoundException`
  * `ApiException`
* Standardized API error structure (`ErrorResponse`)
* Validation, runtime, and business rule handling
* Detailed logging for debugging
* Clean JSON error output for frontend consumption

---

## ✅ **2. Pagination & Sorting**

Implemented efficient backend-driven pagination using Spring Data JPA.

**Features:**

* Query params:
  `pageNumber`, `pageSize`, `sortBy`, `sortDir`
* Custom `PageableResponse<T>` wrapper
* Optimized sorting + pagination at DB level
* Logging for request tracing

**Example API:**

```
GET /api/v1/employees?pageNumber=0&pageSize=10&sortBy=name&sortDir=asc
```

---

## ✅ **3. Bootstrap UI Integration**

Fully integrated **Bootstrap 5.3.3** for responsive UI development.

**Done in the project:**

* Added Bootstrap CDN in Thymeleaf template
* Created reusable layout fragments (`header`, `footer`, `navbar`)
* Responsive pages for:

  * Employee list
  * Employee registration form
  * Dashboard template
* Integrated with Thymeleaf views
* Included mobile-friendly layout using Bootstrap grid system

**Benefits:**

* Faster UI development
* Consistent styling
* Zero CSS maintenance
* Works smoothly with Spring MVC + Thymeleaf

---

## 🗂️ Documentation Added

Notes maintained separately for developer reference:

```
/docs/notes/
    global-exception-handling.md
    pagination-sorting.md
    bootstrap-ui-integration.md
```

---

## ✔️ Next Features (Optional to Add)

* JWT Authentication (planned)
* Search + Filters for Employee
* Docker Configuration
* Database Migrations with Flyway

---

If you want, I can generate a **fully structured README.md** including:

* Project Overview
* Features
* Tech Stack
* Folder Structure
* Setup Guide
* API Documentation
* Branch Naming Strategy
* Screenshots (Bootstrap UI)
* Contribution Guide

Just say **“Generate full README”**.

