Here are clear, practical **highlight notes about Lombok `@Builder`**, specifically for your Spring Boot project. You can copy-paste this into your documentation / README.

---

# 📝 **Lombok @Builder – Important Notes (Must Read)**

### ✅ **1. @Builder ignores field initialization**

If you write:

```java
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
}
```

This **will NOT work**.
When using `@Builder`, Lombok **does not apply default field values**, so `timestamp` becomes **null**.

💡 **Fix:** use `@Builder.Default`

```java
@Builder
public class ErrorResponse {
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
```

---

### ✅ **2. @Builder should NOT be used on Exception classes**

This is wrong:

```java
@Builder
public class ResourceNotFoundException extends RuntimeException { }
```

✔ Exceptions should be constructed with constructors
✔ They are not meant for step-by-step building
✔ Builder adds unnecessary code and confusion
✔ Logging + stack traces become harder to track

---

### ✅ **3. @Builder works best for response objects, DTOs & immutable objects**

Good usage examples:

* API response classes (`ApiResponse`)
* Custom response bodies
* Request DTOs
* Complex object creation
* Immutable classes (`@Value + @Builder`)

Example:

```java
ApiResponse.<EmployeeDto>builder()
        .success(true)
        .message("Fetched successfully")
        .data(employeeDto)
        .build();
```

---

### ❌ **4. DO NOT use @Builder on JPA entities**

Reasons:

* Causes unexpected field initialization issues
* Conflicts with JPA proxies
* Breaks lazy loading
* Can skip required fields silently
* Can accidentally bypass validation

Better: use constructors + setters.

---

### ⚠️ **5. @Builder overwrites constructors**

If your class has:

```java
@AllArgsConstructor
@NoArgsConstructor
@Builder
```

Then the builder will always call the generated **all-args constructor**, not your field defaults.

---

### ⛔ **6. Builder creates a separate builder class (increases bytecode size)**

Not a big problem, but important to remember in large apps.

---

### 💡 Best Practices Summary

✔ Use builder only for:

* API response objects
* DTOs
* Immutable classes

✔ Use `@Builder.Default` for default values
✔ **Do NOT use builder for:**

* Exceptions
* Entities
* Simple POJOs with few fields
* Objects created frequently inside loops (builder is slower)

---

### ⭐ Final Golden Rule

> “Use @Builder where it improves readability.
> Avoid it where it hides logic or causes unexpected behaviour.”

---

If you want, I can also generate:

✅ A full documentation section for your project
or
✅ Notes specifically for your **common.response** classes

Just tell me **“generate documentation”**.
