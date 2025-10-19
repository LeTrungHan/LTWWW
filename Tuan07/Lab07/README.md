# LAB 07 - SPRING DATA JPA

## 📋 Mô tả
Bài tập thực hành Spring Data JPA với MariaDB Database, quản lý thông tin nhân viên (Employee).

## 🛠️ Công nghệ sử dụng
- **Framework**: Spring Boot 3.5.6
- **Java**: 17
- **Database**: MariaDB
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Libraries**: Lombok, Validation

## 📁 Cấu trúc Project

```
src/main/java/iuh/fit/se/lab07/
├── entities/           # JPA Entity classes
│   └── Employee.java
├── repositories/       # Spring Data JPA Repositories
│   └── EmployeeRepository.java
├── serverces/          # Service layer (Business logic)
│   └── EmployeeService.java
├── controllers/        # REST Controllers
│   └── EmployeeController.java
├── DataInitializer.java    # Khởi tạo dữ liệu mẫu
└── Lab07Application.java   # Main application
```

## 🗄️ Cấu hình Database

### 1. Tạo Database
```sql
CREATE DATABASE employees CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Cấu hình trong application.properties
```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/employees
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
```

## 🚀 Cách chạy ứng dụng

### 1. Khởi động MariaDB
Đảm bảo MariaDB đang chạy trên port 3306

### 2. Chạy ứng dụng
```bash
# Windows
mvnw.cmd spring-boot:run

# Unix/Linux/Mac
./mvnw spring-boot:run
```

### 3. Truy cập ứng dụng
- Application sẽ chạy tại: `http://localhost:8080`
- API endpoints: `http://localhost:8080/api/employees`

## 📡 REST API Endpoints

### Quản lý nhân viên cơ bản

| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/api/employees` | Lấy danh sách tất cả nhân viên |
| GET | `/api/employees/{id}` | Lấy thông tin nhân viên theo ID |
| POST | `/api/employees` | Thêm nhân viên mới |
| PUT | `/api/employees/{id}` | Cập nhật thông tin nhân viên |
| DELETE | `/api/employees/{id}` | Xóa nhân viên |

### Tìm kiếm

| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/api/employees/search/firstname/{name}` | Tìm theo tên |
| GET | `/api/employees/search/lastname/{name}` | Tìm theo họ |
| GET | `/api/employees/search/gender/{gender}` | Tìm theo giới tính (M/F) |
| GET | `/api/employees/search/fullname/{name}` | Tìm theo tên đầy đủ |
| GET | `/api/employees/search/birthdate?start={date}&end={date}` | Tìm theo khoảng ngày sinh |

### Thống kê

| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/api/employees/stats/count-by-gender/{gender}` | Đếm số nhân viên theo giới tính |
| GET | `/api/employees/recent-hires` | Lấy 10 nhân viên tuyển gần đây |

## 📝 Ví dụ sử dụng API

### 1. Lấy tất cả nhân viên
```bash
curl http://localhost:8080/api/employees
```

### 2. Thêm nhân viên mới
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "1990-05-15",
    "firstName": "Nguyễn",
    "lastName": "Văn An",
    "gender": "M",
    "hireDate": "2024-01-15"
  }'
```

### 3. Tìm nhân viên theo tên
```bash
curl http://localhost:8080/api/employees/search/firstname/Nguyễn
```

### 4. Đếm số nhân viên nam
```bash
curl http://localhost:8080/api/employees/stats/count-by-gender/M
```

## 🎯 Các tính năng đã implement

### 1. Entity Layer
- ✅ Sử dụng JPA annotations (@Entity, @Table, @Column, @Id)
- ✅ Sử dụng Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor)
- ✅ Validation annotations (@NotNull, @NotBlank, @Size, @Past)
- ✅ Enum cho Gender
- ✅ Helper methods (getFullName())

### 2. Repository Layer
- ✅ Kế thừa JpaRepository
- ✅ Query methods theo naming convention
- ✅ Custom JPQL queries với @Query
- ✅ Native SQL queries
- ✅ Sử dụng @Param cho named parameters

### 3. Service Layer
- ✅ CRUD operations
- ✅ Business logic
- ✅ Transaction management với @Transactional
- ✅ Logging với @Slf4j
- ✅ Exception handling

### 4. Controller Layer
- ✅ RESTful API design
- ✅ Request mapping
- ✅ Request/Response handling
- ✅ Validation với @Valid
- ✅ Proper HTTP status codes
- ✅ CORS configuration

### 5. Data Initialization
- ✅ CommandLineRunner để khởi tạo dữ liệu mẫu
- ✅ Check database trước khi insert

## 🔍 Spring Data JPA Features được sử dụng

1. **Query Method Naming Convention**
   - `findByFirstName()`
   - `findByGender()`
   - `findByBirthDateBetween()`

2. **JPQL Queries**
   - `@Query` với JPQL
   - Named parameters với `@Param`
   - Aggregate functions (COUNT)

3. **Native SQL Queries**
   - `@Query` với `nativeQuery = true`
   - LIMIT clause

4. **Transaction Management**
   - `@Transactional` annotation
   - Automatic transaction handling

## 📚 Kiến thức áp dụng

- ✅ Spring Data JPA Repository pattern
- ✅ JPA Entity mapping
- ✅ Hibernate ORM
- ✅ JPQL và Native SQL
- ✅ Bean Validation (JSR-380)
- ✅ RESTful API design
- ✅ Dependency Injection
- ✅ Logging
- ✅ Transaction management

## 👨‍💻 Tác giả
- **Sinh viên**: [Tên sinh viên]
- **MSSV**: [Mã số sinh viên]
- **Lớp**: [Lớp]
- **Môn học**: Lập Trình Web Nâng Cao

## 📅 Thời gian thực hiện
Tuần 7 - Lab 07
