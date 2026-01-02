Here’s a **clean, industry-standard `stock-layout.html`** commonly used in **Spring Boot + Thymeleaf** apps.
It’s modular, responsive, and easy to scale (header, sidebar, footer, content).

---

### ✅ `stock-layout.html`

```html
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title layout:title-pattern="$CONTENT_TITLE | Stock App">Stock App</title>

    <!-- Global CSS -->
    <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
    <link rel="stylesheet" th:href="@{/css/app.css}">

    <!-- Page specific CSS -->
    <th:block layout:fragment="styles"></th:block>
</head>

<body class="bg-light">

<!-- ===== Header / Navbar ===== -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" th:href="@{/}">StockApp</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/dashboard}">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/strategy}">Strategy Builder</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/logout}">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- ===== Main Layout ===== -->
<div class="container-fluid">
    <div class="row">

        <!-- Sidebar -->
        <aside class="col-md-2 d-none d-md-block bg-white border-end min-vh-100">
            <div class="list-group list-group-flush mt-3">
                <a th:href="@{/dashboard}" class="list-group-item list-group-item-action">
                    Dashboard
                </a>
                <a th:href="@{/strategy}" class="list-group-item list-group-item-action">
                    Strategy Builder
                </a>
                <a th:href="@{/backtest}" class="list-group-item list-group-item-action">
                    Backtesting
                </a>
                <a th:href="@{/reports}" class="list-group-item list-group-item-action">
                    Reports
                </a>
            </div>
        </aside>

        <!-- Content Area -->
        <main class="col-md-10 ms-sm-auto px-4 py-4">
            <!-- Page Content -->
            <div layout:fragment="content">
                <!-- Child templates inject content here -->
            </div>
        </main>

    </div>
</div>

<!-- ===== Footer ===== -->
<footer class="text-center py-3 border-top bg-white">
    <small class="text-muted">
        © 2025 Stock App. All rights reserved.
    </small>
</footer>

<!-- Global JS -->
<script th:src="@{/js/bootstrap.bundle.min.js}"></script>

<!-- Page specific JS -->
<th:block layout:fragment="scripts"></th:block>

</body>
</html>
```

---

### ✅ Why this is “industry standard”

* ✔ **Uses Thymeleaf Layout Dialect correctly**
* ✔ **Responsive (Bootstrap-based)**
* ✔ **Clear separation**: header, sidebar, content, footer
* ✔ **Supports per-page CSS & JS** via `styles` and `scripts` fragments
* ✔ **Scales well** for dashboards / trading platforms (like OPSTRA)

---

### 🔌 Your existing page will work **as-is**

Your page:

```html
<div layout:fragment="content">
    <h1>Strategy-builder page!!</h1>
</div>
```

👉 will render perfectly inside this layout.

---

If you want next:

* OPSTRA-style **strategy builder UI**
* **Dark trading dashboard theme**
* **Reusable header/sidebar fragments**
* **Role-based menu (admin/user)**

Just tell me 👍



Great question 👍
This part is **core to how professional Thymeleaf layouts work**, so let’s go **slow, clear, and practical**.

I’ll explain **each line**, then show **real examples** of how child pages use it.

---

## 1️⃣ `<title layout:title-pattern="...">`

```html
<title layout:title-pattern="$CONTENT_TITLE | Stock App">
    Stock App
</title>
```

### 🔹 What problem does this solve?

You want:

* Each page to have its **own title**
* But still keep a **common app name** (`Stock App`)

Instead of repeating:

```html
<title>Strategy Builder | Stock App</title>
<title>Dashboard | Stock App</title>
```

on every page, Thymeleaf **builds it automatically**.

---

### 🔹 How it works

`layout:title-pattern` is provided by the **Thymeleaf Layout Dialect**.

* `$CONTENT_TITLE` → title from the **child page**
* `Stock App` → common suffix

### 🧠 Logic:

```
Final title = [Child Page Title] | Stock App
```

---

### 🔹 Example: Child Page

```html
<head>
    <title>Strategy Builder</title>
</head>
```

### ✅ Final browser title becomes:

```
Strategy Builder | Stock App
```

---

### 🔹 If child page has NO title

Then fallback is:

```
Stock App
```

✔ Clean
✔ Consistent
✔ Industry standard

---

## 2️⃣ Global CSS (Loaded on EVERY page)

```html
<link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
<link rel="stylesheet" th:href="@{/css/app.css}">
```

---

### 🔹 `th:href="@{...}"` explained

This is **Thymeleaf URL expression**.

```text
@{/css/app.css}
```

Means:

> “Resolve this path relative to my application context”

So if your app runs at:

```
http://localhost:8080
```

It becomes:

```
http://localhost:8080/css/app.css
```

If deployed as:

```
http://mydomain.com/stockapp
```

It becomes:

```
http://mydomain.com/stockapp/css/app.css
```

✔ Safe for production
✔ Context-path aware
✔ No hardcoding

---

### 🔹 Why two CSS files?

| File                | Purpose                                        |
| ------------------- | ---------------------------------------------- |
| `bootstrap.min.css` | Layout, grid, buttons, responsiveness          |
| `app.css`           | Your custom styles (colors, spacing, branding) |

📌 **Industry rule**:
Never modify Bootstrap directly → override in `app.css`

---

## 3️⃣ Page-Specific CSS (VERY important)

```html
<th:block layout:fragment="styles"></th:block>
```

This line is **powerful**.

---

### 🔹 What is `<th:block>`?

* It’s a **Thymeleaf-only container**
* It **does NOT render** any HTML tag
* Used for grouping logic or fragments

So:

```html
<th:block>...</th:block>
```

Produces **no extra HTML**

---

### 🔹 What is `layout:fragment="styles"`?

This defines a **placeholder** in the layout.

Think of it as:

> “If a child page wants to add extra CSS, put it HERE.”

---

### 🔹 Example: Child page adds its own CSS

```html
<!DOCTYPE html>
<html layout:decorate="~{stock-app/layouts/stock-layout}">
<head>
    <title>Strategy Builder</title>

    <th:block layout:fragment="styles">
        <link rel="stylesheet" th:href="@{/css/strategy.css}">
    </th:block>
</head>
<body>
<div layout:fragment="content">
    ...
</div>
</body>
</html>
```

---

### ✅ Final HTML in browser

```html
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/app.css">
<link rel="stylesheet" href="/css/strategy.css">
```

✔ Only loaded when needed
✔ Faster pages
✔ Clean separation

---

## 🧠 Why professionals do it this way

| Problem                      | Solution           |
| ---------------------------- | ------------------ |
| Global styles repeated       | Layout handles it  |
| Page-specific CSS everywhere | `styles` fragment  |
| Messy `<head>`               | Centralized layout |
| Hardcoded titles             | `title-pattern`    |

---

## 🔑 Mental Model (Very Important)

Think of layout as:

```text
Base Layout = skeleton
Child page = fills holes
```

| Hole         | Fragment  |
| ------------ | --------- |
| Title        | `<title>` |
| Extra CSS    | `styles`  |
| Main content | `content` |
| Extra JS     | `scripts` |

---

## 🚀 Next things you should learn (recommended order)

1. `layout:decorate` vs `layout:replace`
2. `layout:fragment` vs `th:fragment`
3. Page-specific JS (`scripts`)
4. Conditional loading (based on role)
5. Fragment reuse (header/sidebar)

If you want, I can **draw a flow diagram** or explain with **request → response lifecycle** next.


On a **Thymeleaf page**, you write JavaScript functions almost the same way as in normal HTML — the key difference is **how you inject server-side values** using Thymeleaf.

Below are the **common and correct patterns**, step by step 👇

---

## 1. Basic JavaScript function in a Thymeleaf HTML page

You can write a normal JS function inside `<script>` tags:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Example</title>
</head>
<body>

<button onclick="sayHello()">Click me</button>

<script>
    function sayHello() {
        alert("Hello from JavaScript!");
    }
</script>

</body>
</html>
```

✅ This works exactly like standard HTML.

---

## 2. Using Thymeleaf values **inside JavaScript**

To safely use Thymeleaf variables in JavaScript, **enable JavaScript inline mode**:

```html
<script th:inline="javascript">
    function showName() {
        let name = [[${username}]];
        alert("Hello " + name);
    }
</script>
```

### Controller example (Spring Boot)

```java
model.addAttribute("username", "Alex");
```

🟢 `[[...]]` is used for **JavaScript expressions**
🟢 Thymeleaf automatically adds quotes if needed

---

## 3. Passing Thymeleaf values as function parameters

### HTML

```html
<button th:onclick="showAge([[${age}]])">Show Age</button>
```

### JavaScript

```html
<script>
    function showAge(age) {
        alert("Age is " + age);
    }
</script>
```

---

## 4. Using Thymeleaf objects or lists in JavaScript

```html
<script th:inline="javascript">
    let user = [[${user}]];
    console.log(user.name);
    console.log(user.email);
</script>
```

Thymeleaf automatically converts objects to **JSON**.

---

## 5. External JavaScript file with Thymeleaf data (best practice)

### HTML

```html
<script th:inline="javascript">
    const userId = [[${userId}]];
</script>

<script src="/js/app.js"></script>
```

### app.js

```javascript
function loadUser() {
    console.log(userId);
}
```

🟡 Thymeleaf **cannot run inside external JS files**, so pass data first.

---

## Common mistakes ❌

* ❌ Using `${}` directly in JS without `th:inline="javascript"`
* ❌ Trying to use Thymeleaf inside `.js` files
* ❌ Forgetting `[[ ]]` for JS expressions

---

## When to use which syntax

| Use case       | Syntax               |
| -------------- | -------------------- |
| JS value       | `[[${value}]]`       |
| HTML text      | `th:text="${value}"` |
| HTML attribute | `th:onclick="..."`   |

---

If you want, tell me:

* Are you using **Spring Boot**?
* Do you want to pass **arrays, objects, or forms**?
* Inline JS or external JS?

I can tailor the example exactly to your case 🙂

