package iuh.fit.se.lab07.controllers;

import iuh.fit.se.lab07.entities.Employee;
import iuh.fit.se.lab07.serverces.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST Controller xử lý các HTTP requests liên quan đến Employee
 * Endpoints:
 * - GET /api/employees - Lấy tất cả nhân viên
 * - GET /api/employees/{id} - Lấy nhân viên theo ID
 * - POST /api/employees - Thêm nhân viên mới
 * - PUT /api/employees/{id} - Cập nhật nhân viên
 * - DELETE /api/employees/{id} - Xóa nhân viên
 * - GET /api/employees/search/* - Các endpoint tìm kiếm
 */
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    /**
     * GET /api/employees
     * Lấy danh sách tất cả nhân viên
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    /**
     * GET /api/employees/{id}
     * Lấy thông tin nhân viên theo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST /api/employees
     * Thêm nhân viên mới
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
    
    /**
     * PUT /api/employees/{id}
     * Cập nhật thông tin nhân viên
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {
        employee.setEmpNo(id);
        try {
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE /api/employees/{id}
     * Xóa nhân viên
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET /api/employees/search/firstname/{name}
     * Tìm kiếm theo tên
     */
    @GetMapping("/search/firstname/{name}")
    public ResponseEntity<List<Employee>> findByFirstName(@PathVariable String name) {
        List<Employee> employees = employeeService.findByFirstName(name);
        return ResponseEntity.ok(employees);
    }
    
    /**
     * GET /api/employees/search/lastname/{name}
     * Tìm kiếm theo họ
     */
    @GetMapping("/search/lastname/{name}")
    public ResponseEntity<List<Employee>> findByLastName(@PathVariable String name) {
        List<Employee> employees = employeeService.findByLastName(name);
        return ResponseEntity.ok(employees);
    }
    
    /**
     * GET /api/employees/search/gender/{gender}
     * Tìm kiếm theo giới tính (M hoặc F)
     */
    @GetMapping("/search/gender/{gender}")
    public ResponseEntity<List<Employee>> findByGender(@PathVariable Employee.Gender gender) {
        List<Employee> employees = employeeService.findByGender(gender);
        return ResponseEntity.ok(employees);
    }
    
    /**
     * GET /api/employees/search/fullname/{name}
     * Tìm kiếm theo tên đầy đủ
     */
    @GetMapping("/search/fullname/{name}")
    public ResponseEntity<List<Employee>> findByFullName(@PathVariable String name) {
        List<Employee> employees = employeeService.findByFullName(name);
        return ResponseEntity.ok(employees);
    }
    
    /**
     * GET /api/employees/search/birthdate?start=yyyy-MM-dd&end=yyyy-MM-dd
     * Tìm kiếm theo khoảng ngày sinh
     */
    @GetMapping("/search/birthdate")
    public ResponseEntity<List<Employee>> findByBirthDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<Employee> employees = employeeService.findByBirthDateRange(start, end);
        return ResponseEntity.ok(employees);
    }
    
    /**
     * GET /api/employees/stats/count-by-gender/{gender}
     * Đếm số lượng nhân viên theo giới tính
     */
    @GetMapping("/stats/count-by-gender/{gender}")
    public ResponseEntity<Long> countByGender(@PathVariable Employee.Gender gender) {
        long count = employeeService.countByGender(gender);
        return ResponseEntity.ok(count);
    }
    
    /**
     * GET /api/employees/recent-hires
     * Lấy 10 nhân viên được tuyển gần đây nhất
     */
    @GetMapping("/recent-hires")
    public ResponseEntity<List<Employee>> getRecentHires() {
        List<Employee> employees = employeeService.getRecentHires();
        return ResponseEntity.ok(employees);
    }
}
