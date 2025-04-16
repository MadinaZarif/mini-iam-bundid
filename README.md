# Mini IAM System - Inspired by Bund.ID 🇩🇪✨

This is a **mini Identity and Access Management (IAM)** demo project built with **Spring Boot**, inspired by the **Bund.ID** concept (used in Germany for digital identity).

🛠️ **Why this project?**  
As a self-taught developer transitioning into the IAM field, I wanted to understand key concepts such as:
- Authentication & Authorization
- Roles and access control
- Dynamic role switching
- Admin views and user management

---

## 💡 Features

- 🔐 Custom Login page using Spring Security
- 🧑‍💼 Predefined user roles: **Admin**, **Support**, **Citizen**, and **Guest**
- 🔄 Dynamic role switching (session-based)
- 📋 Admin panel with a list of users and role-changing functionality
- 🎨 Simple front-end with **Thymeleaf** and **CSS**
- ✅ CI/CD pipeline and deployed to S3 (for other site content)

---

## 🧪 Technologies Used

- Java 21  
- Spring Boot 3  
- Spring Security 6  
- Thymeleaf  
- Git & GitHub  
- GitHub Actions (CI/CD)  
- AWS S3 (for static site hosting)  

---

## 🚀 How to Run Locally

```bash
git clone https://github.com/MadinaZarif/mini-iam-bundid.git
cd mini-iam-bundid/demo
./mvnw spring-boot:run
