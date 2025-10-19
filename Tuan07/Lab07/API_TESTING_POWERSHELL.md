# HƯỚNG DẪN TEST API VỚI POWERSHELL

## 📋 Danh sách API Endpoints

### 1️⃣ LẤY TẤT CẢ NHÂN VIÊN
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/employees" -Method GET

# Hoặc dùng alias ngắn
irm "http://localhost:8080/api/employees"
```

### 2️⃣ LẤY NHÂN VIÊN THEO ID
```powershell
# Lấy nhân viên có ID = 1
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/1" -Method GET
```

### 3️⃣ THÊM NHÂN VIÊN MỚI
```powershell
# Tạo object JSON
$body = @{
    birthDate = "1995-08-20"
    firstName = "Nguyễn"
    lastName = "Văn Test"
    gender = "M"
    hireDate = "2024-10-19"
} | ConvertTo-Json

# Gửi POST request
Invoke-RestMethod -Uri "http://localhost:8080/api/employees" `
    -Method POST `
    -Body $body `
    -ContentType "application/json"
```

**Hoặc viết gọn trên 1 dòng:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/employees" -Method POST -Body '{"birthDate":"1995-08-20","firstName":"Nguyễn","lastName":"Văn Test","gender":"M","hireDate":"2024-10-19"}' -ContentType "application/json"
```

### 4️⃣ CẬP NHẬT THÔNG TIN NHÂN VIÊN
```powershell
# Cập nhật nhân viên có ID = 1
$body = @{
    birthDate = "1990-05-15"
    firstName = "Nguyễn"
    lastName = "Văn An Updated"
    gender = "M"
    hireDate = "2015-03-10"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/employees/1" `
    -Method PUT `
    -Body $body `
    -ContentType "application/json"
```

### 5️⃣ XÓA NHÂN VIÊN
```powershell
# Xóa nhân viên có ID = 1
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/1" -Method DELETE
```

---

## 🔍 CÁC API TÌM KIẾM

### 6️⃣ TÌM THEO TÊN (FIRST NAME)
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/firstname/Nguyễn" -Method GET
```

### 7️⃣ TÌM THEO HỌ (LAST NAME)
```powershell
# Lưu ý: Nếu có khoảng trắng, cần encode URL hoặc dùng dấu ngoặc kép
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/lastname/Văn An" -Method GET

# Hoặc encode URL
$lastName = [System.Web.HttpUtility]::UrlEncode("Văn An")
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/lastname/$lastName" -Method GET
```

### 8️⃣ TÌM THEO GIỚI TÍNH
```powershell
# Tìm nhân viên nam (M)
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/gender/M" -Method GET

# Tìm nhân viên nữ (F)
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/gender/F" -Method GET
```

### 9️⃣ TÌM THEO TÊN ĐẦY ĐỦ
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/fullname/Nguyễn Văn" -Method GET
```

### 🔟 TÌM THEO KHOẢNG NGÀY SINH
```powershell
# Tìm nhân viên sinh từ 1990-01-01 đến 1995-12-31
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/birthdate?start=1990-01-01&end=1995-12-31" -Method GET
```

---

## 📊 CÁC API THỐNG KÊ

### 1️⃣1️⃣ ĐẾM SỐ NHÂN VIÊN THEO GIỚI TÍNH
```powershell
# Đếm số nhân viên nam
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/stats/count-by-gender/M" -Method GET

# Đếm số nhân viên nữ
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/stats/count-by-gender/F" -Method GET
```

### 1️⃣2️⃣ LẤY 10 NHÂN VIÊN TUYỂN GẦN ĐÂY NHẤT
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/recent-hires" -Method GET
```

---

## 🎯 VÍ DỤ SỬ DỤNG NÂNG CAO

### Lưu kết quả vào biến và xử lý
```powershell
# Lấy tất cả nhân viên và lưu vào biến
$allEmployees = Invoke-RestMethod -Uri "http://localhost:8080/api/employees" -Method GET

# Hiển thị số lượng
$allEmployees.Count

# Hiển thị dạng bảng
$allEmployees | Format-Table -Property empNo, firstName, lastName, gender, hireDate

# Lọc nhân viên nam
$allEmployees | Where-Object { $_.gender -eq "M" }

# Sắp xếp theo ngày tuyển dụng
$allEmployees | Sort-Object -Property hireDate -Descending
```

### Lưu kết quả vào file JSON
```powershell
$employees = Invoke-RestMethod -Uri "http://localhost:8080/api/employees" -Method GET
$employees | ConvertTo-Json | Out-File -FilePath "employees.json"
```

### Hiển thị kết quả đẹp hơn với ConvertTo-Json
```powershell
$employee = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/1" -Method GET
$employee | ConvertTo-Json -Depth 10
```

---

## 🧪 TEST CASE ĐẦY ĐỦ

### Test Case 1: CRUD Operations
```powershell
# 1. Thêm nhân viên mới
$newEmployee = Invoke-RestMethod -Uri "http://localhost:8080/api/employees" `
    -Method POST `
    -Body '{"birthDate":"1997-06-15","firstName":"Test","lastName":"User","gender":"M","hireDate":"2024-10-19"}' `
    -ContentType "application/json"

Write-Host "Đã tạo nhân viên ID: $($newEmployee.empNo)"

# 2. Lấy nhân viên vừa tạo
$employee = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/$($newEmployee.empNo)" -Method GET
$employee | ConvertTo-Json

# 3. Cập nhật nhân viên
$updateBody = @{
    birthDate = $employee.birthDate
    firstName = $employee.firstName
    lastName = "Updated User"
    gender = $employee.gender
    hireDate = $employee.hireDate
} | ConvertTo-Json

$updated = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/$($newEmployee.empNo)" `
    -Method PUT `
    -Body $updateBody `
    -ContentType "application/json"

Write-Host "Đã cập nhật: $($updated.lastName)"

# 4. Xóa nhân viên
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/$($newEmployee.empNo)" -Method DELETE
Write-Host "Đã xóa nhân viên ID: $($newEmployee.empNo)"
```

### Test Case 2: Tìm kiếm và thống kê
```powershell
# Tìm tất cả nhân viên nam
$males = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/gender/M" -Method GET
Write-Host "Số nhân viên nam: $($males.Count)"

# Tìm tất cả nhân viên nữ
$females = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/gender/F" -Method GET
Write-Host "Số nhân viên nữ: $($females.Count)"

# Lấy nhân viên tuyển gần đây
$recent = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/recent-hires" -Method GET
$recent | Format-Table -Property empNo, firstName, lastName, hireDate

# Thống kê
$maleCount = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/stats/count-by-gender/M" -Method GET
$femaleCount = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/stats/count-by-gender/F" -Method GET
Write-Host "Thống kê: Nam: $maleCount, Nữ: $femaleCount"
```

---

## 🔍 DEBUGGING

### Xem chi tiết request/response
```powershell
# Sử dụng Invoke-WebRequest để xem headers, status code
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/employees" -Method GET

# Xem status code
$response.StatusCode

# Xem headers
$response.Headers

# Xem content (JSON string)
$response.Content

# Parse JSON
$data = $response.Content | ConvertFrom-Json
$data
```

### Bắt lỗi với try-catch
```powershell
try {
    $employee = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/999" -Method GET
    Write-Host "Tìm thấy nhân viên: $($employee.firstName)"
}
catch {
    Write-Host "Lỗi: $_"
    Write-Host "Status Code: $($_.Exception.Response.StatusCode.value__)"
}
```

---

## 💡 TIPS & TRICKS

### 1. Tạo function helper
```powershell
function Get-Employee {
    param([int]$id)
    Invoke-RestMethod -Uri "http://localhost:8080/api/employees/$id" -Method GET
}

function Get-AllEmployees {
    Invoke-RestMethod -Uri "http://localhost:8080/api/employees" -Method GET
}

function Add-Employee {
    param(
        [string]$firstName,
        [string]$lastName,
        [string]$gender,
        [string]$birthDate,
        [string]$hireDate
    )
    
    $body = @{
        firstName = $firstName
        lastName = $lastName
        gender = $gender
        birthDate = $birthDate
        hireDate = $hireDate
    } | ConvertTo-Json
    
    Invoke-RestMethod -Uri "http://localhost:8080/api/employees" `
        -Method POST `
        -Body $body `
        -ContentType "application/json"
}

# Sử dụng
$emp = Get-Employee -id 1
$all = Get-AllEmployees
Add-Employee -firstName "Test" -lastName "User" -gender "M" -birthDate "1995-01-01" -hireDate "2024-10-19"
```

### 2. Alias ngắn gọn
```powershell
# Tạo alias
Set-Alias -Name emp -Value Get-AllEmployees

# Sử dụng
emp
```

### 3. Format output đẹp
```powershell
$employees = Invoke-RestMethod -Uri "http://localhost:8080/api/employees" -Method GET

# Dạng bảng
$employees | Format-Table

# Dạng list
$employees | Format-List

# Dạng grid (interactive)
$employees | Out-GridView
```

---

## 🚀 SCRIPT TỰ ĐỘNG TEST TẤT CẢ ENDPOINTS

```powershell
# test-all-endpoints.ps1

Write-Host "=== BẮT ĐẦU TEST API ===" -ForegroundColor Green

# Test 1: Lấy tất cả
Write-Host "`n1. GET all employees" -ForegroundColor Yellow
$all = Invoke-RestMethod -Uri "http://localhost:8080/api/employees" -Method GET
Write-Host "✓ Số lượng: $($all.Count)" -ForegroundColor Green

# Test 2: Thêm mới
Write-Host "`n2. POST new employee" -ForegroundColor Yellow
$new = Invoke-RestMethod -Uri "http://localhost:8080/api/employees" `
    -Method POST `
    -Body '{"birthDate":"1997-06-15","firstName":"Test","lastName":"PowerShell","gender":"M","hireDate":"2024-10-19"}' `
    -ContentType "application/json"
Write-Host "✓ Đã tạo ID: $($new.empNo)" -ForegroundColor Green

# Test 3: Lấy theo ID
Write-Host "`n3. GET by ID" -ForegroundColor Yellow
$emp = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/$($new.empNo)" -Method GET
Write-Host "✓ Tìm thấy: $($emp.firstName) $($emp.lastName)" -ForegroundColor Green

# Test 4: Cập nhật
Write-Host "`n4. PUT update" -ForegroundColor Yellow
$updateBody = @{
    birthDate = $emp.birthDate
    firstName = $emp.firstName
    lastName = "Updated"
    gender = $emp.gender
    hireDate = $emp.hireDate
} | ConvertTo-Json
$updated = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/$($new.empNo)" `
    -Method PUT -Body $updateBody -ContentType "application/json"
Write-Host "✓ Đã cập nhật: $($updated.lastName)" -ForegroundColor Green

# Test 5: Tìm kiếm
Write-Host "`n5. Search by gender" -ForegroundColor Yellow
$males = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/search/gender/M" -Method GET
Write-Host "✓ Số nhân viên nam: $($males.Count)" -ForegroundColor Green

# Test 6: Thống kê
Write-Host "`n6. Statistics" -ForegroundColor Yellow
$count = Invoke-RestMethod -Uri "http://localhost:8080/api/employees/stats/count-by-gender/M" -Method GET
Write-Host "✓ Tổng nhân viên nam: $count" -ForegroundColor Green

# Test 7: Xóa
Write-Host "`n7. DELETE" -ForegroundColor Yellow
Invoke-RestMethod -Uri "http://localhost:8080/api/employees/$($new.empNo)" -Method DELETE
Write-Host "✓ Đã xóa ID: $($new.empNo)" -ForegroundColor Green

Write-Host "`n=== HOÀN THÀNH TEST ===" -ForegroundColor Green
```

**Chạy script:**
```powershell
.\test-all-endpoints.ps1
```

---

## ⚠️ LƯU Ý

1. **Invoke-RestMethod** tự động parse JSON, tiện lợi hơn
2. **Invoke-WebRequest** trả về object đầy đủ hơn (headers, status code, etc.)
3. Với **string có khoảng trắng** trong URL, nhớ dùng dấu ngoặc kép
4. **Tiếng Việt** có thể cần encode URL
5. PowerShell **không phân biệt** chữ hoa/thường cho parameters

---

## 🎯 KHUYẾN NGHỊ

**Cho việc test nhanh:** Dùng `Invoke-RestMethod` (alias: `irm`)  
**Cho việc debugging:** Dùng `Invoke-WebRequest` (alias: `iwr`)  
**Cho việc tự động hóa:** Viết script PowerShell như ví dụ trên
