Below is **clean, structured, easy-to-maintain learning notes** for your **Pagination + Sorting implementation**.
You can copy this into **your notes folder**, e.g.:
`docs/pagination-sorting.md` or `notes/pagination-sorting.md`.

---

# 📘 Pagination & Sorting — Learning Notes (Spring Boot + JPA)

---

## ✅ 1. What is Pagination?

Pagination means splitting large data into smaller pages.

Example:
Page 0 → first 10 records
Page 1 → next 10 records
Page 2 → next 10 records

Spring uses:

```java
Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
```

---

## ✅ 2. Why Pagination Is Needed?

✔ Avoid returning thousands of records
✔ Improve performance & database load
✔ Better UI/UX for tables
✔ Saves network bandwidth

---

## ✅ 3. What is Sorting?

Sorting allows arranging results in ascending or descending order:

```java
Sort.by("firstName").ascending();
Sort.by("salary").descending();
```

---

## 💡 4. Key Spring Boot Classes

| Class         | Purpose                              |
| ------------- | ------------------------------------ |
| `Pageable`    | Holds page, size, sort info          |
| `PageRequest` | Implementation of Pageable           |
| `Page<T>`     | Contains paginated data and metadata |
| `Sort`        | Sorting conditions                   |

---

## 🧩 5. Service Layer Logic (Core Logic)

```java
Pageable pageable = PageRequest.of(
        page,
        size,
        sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()
);

Page<Employee> employeePage = employeeRepository.findAll(pageable);
```

---

## 🔄 6. Converting `Page<Employee>` → `PageableResponse<EmployeeDto>`

Your structure:

```
content → list<EmployeeDto>
pageNumber
pageSize
totalElements
totalPages
lastPage
```

---

## 📌 7. Controller Method (Pagination + Sorting)

### Request Example:

```
GET /api/v1/employees?page=0&size=5&sortBy=firstName&sortDir=desc
```

### Controller Code:

```java
@GetMapping
public ResponseEntity<ApiResponse<PageableResponse<EmployeeDto>>> getAllEmployees(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir) {

    log.info("Pagination Request => page={}, size={}, sortBy={}, sortDir={}", 
             page, size, sortBy, sortDir);

    PageableResponse<EmployeeDto> response =
            employeeService.getAllEmployees(page, size, sortBy, sortDir);

    return ResponseEntity.ok(ApiResponse.<PageableResponse<EmployeeDto>>builder()
            .success(true)
            .message("Employees fetched successfully")
            .data(response)
            .build());
}
```

---

## 🎯 8. How Pagination Works Internally?

1. Controller receives page & size
2. Build a `PageRequest`
3. Repository runs `SELECT * FROM employees LIMIT size OFFSET page*size`
4. Result wrapped into `Page<T>`
5. Convert to custom DTO response
6. Return final result
7. Logs help track request

---

## 🧪 9. Sample API Response (Final Output)

```json
{
  "success": true,
  "message": "Employees fetched successfully",
  "data": {
    "content": [...],
    "pageNumber": 0,
    "pageSize": 10,
    "totalElements": 43,
    "totalPages": 5,
    "lastPage": false
  }
}
```

---

## 🏷 10. Important Best Practices

### ✔ Always use Pageable in DB-heavy endpoints

### ✔ Default page=0, size=10

### ✔ Validate `size` (avoid 0 or >100)

### ✔ Sorting only on indexed columns (performance)

### ✔ Add logs for analysis

---

## 🛠 11. Logging Guidelines

```java
log.info("API Request: page={}, size={}, sortBy={}, sortDir={}", page, size, sortBy, sortDir);
log.info("Fetched {} employees", response.getContent().size());
```

---
