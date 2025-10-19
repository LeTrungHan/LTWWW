# HƯỚNG DẪN TEST API VỚI CURL

## 📋 Danh sách API Endpoints

### 1️⃣ LẤY TẤT CẢ NHÂN VIÊN
```bash
curl -X GET http://localhost:8080/api/employees
```

### 2️⃣ LẤY NHÂN VIÊN THEO ID
```bash
# Lấy nhân viên có ID = 1
curl -X GET http://localhost:8080/api/employees/1
```

### 3️⃣ THÊM NHÂN VIÊN MỚI
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "1995-08-20",
    "firstName": "Nguyễn",
    "lastName": "Văn Test",
    "gender": "M",
    "hireDate": "2024-10-19"
  }'
```

### 4️⃣ CẬP NHẬT THÔNG TIN NHÂN VIÊN
```bash
# Cập nhật nhân viên có ID = 1
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "1990-05-15",
    "firstName": "Nguyễn",
    "lastName": "Văn An Updated",
    "gender": "M",
    "hireDate": "2015-03-10"
  }'
```

### 5️⃣ XÓA NHÂN VIÊN
```bash
# Xóa nhân viên có ID = 1
curl -X DELETE http://localhost:8080/api/employees/1
```

---

## 🔍 CÁC API TÌM KIẾM

### 6️⃣ TÌM THEO TÊN (FIRST NAME)
```bash
curl -X GET "http://localhost:8080/api/employees/search/firstname/Nguyễn"
```

### 7️⃣ TÌM THEO HỌ (LAST NAME)
```bash
curl -X GET "http://localhost:8080/api/employees/search/lastname/Văn An"
```

### 8️⃣ TÌM THEO GIỚI TÍNH
```bash
# Tìm nhân viên nam (M)
curl -X GET http://localhost:8080/api/employees/search/gender/M

# Tìm nhân viên nữ (F)
curl -X GET http://localhost:8080/api/employees/search/gender/F
```

### 9️⃣ TÌM THEO TÊN ĐẦY ĐỦ
```bash
curl -X GET "http://localhost:8080/api/employees/search/fullname/Nguyễn Văn"
```

### 🔟 TÌM THEO KHOẢNG NGÀY SINH
```bash
# Tìm nhân viên sinh từ 1990-01-01 đến 1995-12-31
curl -X GET "http://localhost:8080/api/employees/search/birthdate?start=1990-01-01&end=1995-12-31"
```

---

## 📊 CÁC API THỐNG KÊ

### 1️⃣1️⃣ ĐẾM SỐ NHÂN VIÊN THEO GIỚI TÍNH
```bash
# Đếm số nhân viên nam
curl -X GET http://localhost:8080/api/employees/stats/count-by-gender/M

# Đếm số nhân viên nữ
curl -X GET http://localhost:8080/api/employees/stats/count-by-gender/F
```

### 1️⃣2️⃣ LẤY 10 NHÂN VIÊN TUYỂN GẦN ĐÂY NHẤT
```bash
curl -X GET http://localhost:8080/api/employees/recent-hires
```

---

## 🧪 TEST CASE ĐẦY ĐỦ

### Test Case 1: Thêm và kiểm tra nhân viên mới
```bash
# 1. Thêm nhân viên mới
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "1997-06-15",
    "firstName": "Test",
    "lastName": "User",
    "gender": "M",
    "hireDate": "2024-10-19"
  }'

# 2. Lấy tất cả nhân viên để xem nhân viên vừa thêm
curl -X GET http://localhost:8080/api/employees

# 3. Tìm nhân viên theo tên
curl -X GET http://localhost:8080/api/employees/search/firstname/Test
```

### Test Case 2: Cập nhật và xóa
```bash
# 1. Cập nhật nhân viên ID 11
curl -X PUT http://localhost:8080/api/employees/11 \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "1997-06-15",
    "firstName": "Test",
    "lastName": "Updated User",
    "gender": "M",
    "hireDate": "2024-10-19"
  }'

# 2. Kiểm tra cập nhật
curl -X GET http://localhost:8080/api/employees/11

# 3. Xóa nhân viên
curl -X DELETE http://localhost:8080/api/employees/11

# 4. Kiểm tra đã xóa chưa (sẽ trả về 404)
curl -X GET http://localhost:8080/api/employees/11
```

### Test Case 3: Thống kê
```bash
# 1. Đếm số nhân viên nam
curl -X GET http://localhost:8080/api/employees/stats/count-by-gender/M

# 2. Đếm số nhân viên nữ
curl -X GET http://localhost:8080/api/employees/stats/count-by-gender/F

# 3. Lấy nhân viên tuyển gần đây
curl -X GET http://localhost:8080/api/employees/recent-hires
```

---

## 🎯 VALIDATION TEST

### Test thêm nhân viên với dữ liệu không hợp lệ

#### 1. Thiếu first name (sẽ bị lỗi validation)
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "1995-08-20",
    "lastName": "Văn Test",
    "gender": "M",
    "hireDate": "2024-10-19"
  }'
```

#### 2. Ngày sinh trong tương lai (sẽ bị lỗi validation)
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "2030-08-20",
    "firstName": "Nguyễn",
    "lastName": "Văn Test",
    "gender": "M",
    "hireDate": "2024-10-19"
  }'
```

#### 3. Tên quá dài (sẽ bị lỗi validation - max 14 ký tự)
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "birthDate": "1995-08-20",
    "firstName": "Nguyễn Văn Tên Rất Dài",
    "lastName": "Test",
    "gender": "M",
    "hireDate": "2024-10-19"
  }'
```

---

## 💡 LƯU Ý

1. **Đảm bảo application đang chạy** trên `http://localhost:8080`
2. **MariaDB phải đang chạy** với database `employees` đã được tạo
3. **Dữ liệu mẫu** sẽ tự động được thêm vào khi application khởi động lần đầu
4. **Format ngày**: Sử dụng định dạng `yyyy-MM-dd` (VD: 2024-10-19)
5. **Gender**: Chỉ chấp nhận `M` (Male) hoặc `F` (Female)

---

## 🐛 TROUBLESHOOTING

### Lỗi: Connection refused
```
Nguyên nhân: Application chưa khởi động
Giải pháp: Chạy lệnh: mvn spring-boot:run hoặc mvnw spring-boot:run
```

### Lỗi: Cannot connect to database
```
Nguyên nhân: MariaDB chưa khởi động hoặc cấu hình sai
Giải pháp: 
1. Kiểm tra MariaDB đang chạy
2. Kiểm tra username/password trong application.properties
3. Tạo database: CREATE DATABASE employees;
```

### Lỗi: 404 Not Found
```
Nguyên nhân: Endpoint URL sai hoặc ID không tồn tại
Giải pháp: Kiểm tra lại URL và ID
```

### Lỗi: 400 Bad Request
```
Nguyên nhân: Dữ liệu gửi lên không hợp lệ (validation error)
Giải pháp: Kiểm tra lại JSON data và các ràng buộc validation
```
