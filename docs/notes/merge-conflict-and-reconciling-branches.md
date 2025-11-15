
---

# 📘 **Git Notes — Divergent Branches, Pull Errors & Merge Editor Issue**

## 🔹 1. **What was the issue?**

When running:

```bash
git pull
```

Git showed:

```
fatal: Need to specify how to reconcile divergent branches.
```

This happens when:

* Your **local branch** and **remote branch** have **different commit histories** (diverged).
* Git does not know whether you want to **merge**, **rebase**, or **fast-forward**.

Git now requires an explicit instruction.

---

## 🔹 2. **How to fix the error**

### ✔️ Option 1 — Merge (most common)

```
git pull --no-rebase
```

This creates a merge commit.

### ✔️ Option 2 — Rebase (cleaner history)

```
git pull --rebase
```

### ✔️ Option 3 — Fast-forward only (if no diverged commits)

```
git pull --ff-only
```

---

## 🔹 3. **The Editor Opened: Why?**

After running:

```
git pull --no-rebase
```

A merge commit was required.
Git opened the default editor (**Vim**) asking for a commit message:

```
Merge branch 'master' of github.com:swapnilk30/AlgoWebPro-Base-App
```

Your screen showed:

```
hint: Waiting for your editor to close the file...
```

This happens automatically whenever Git needs a commit message.

---

## 🔹 4. **How to exit Vim properly**

### 👉 Save and Exit (complete merge)

```
ESC
:wq
```

### ❌ Exit without saving (abort)

```
ESC
:q!
```

---

## 🔹 5. **Recommended Global Config (Set Default Behavior)**

### ✔️ If you prefer MERGE (default, safe)

```
git config --global pull.rebase false
```

### ✔️ If you prefer REBASE (linear history)

```
git config --global pull.rebase true
```

### ✔️ Fast-forward only (strict)

```
git config --global pull.ff only
```

---

## 🔹 6. **Set a better default editor (avoid Vim)**

### VS Code:

```
git config --global core.editor "code --wait"
```

### Nano (simple):

```
git config --global core.editor "nano"
```

---

## 🔹 7. **Summary**

* The error occurred because **local and remote branches diverged**.
* Git requires pulling strategy defined (merge, rebase, or ff-only).
* Your merge triggered **Vim editor**, and you needed to exit using `:wq`.
* You can set global Git settings to avoid this next time.

---