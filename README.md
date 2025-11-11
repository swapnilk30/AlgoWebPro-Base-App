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
