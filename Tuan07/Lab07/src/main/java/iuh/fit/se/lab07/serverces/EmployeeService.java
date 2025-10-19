package iuh.fit.se.lab07.serverces;

import iuh.fit.se.lab07.entities.Employee;
import iuh.fit.se.lab07.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service layer xử lý business logic cho Employee
 * Sử dụng @Transactional để quản lý transaction
 * @Slf4j từ Lombok để tự động tạo logger
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    /**
     * Lấy tất cả nhân viên
     */
    public List<Employee> getAllEmployees() {
        log.info("Lấy danh sách tất cả nhân viên");
        return employeeRepository.findAll();
    }
    
    /**
     * Lấy nhân viên theo ID
     */
    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Tìm nhân viên với ID: {}", id);
        return employeeRepository.findById(id);
    }
    
    /**
     * Thêm nhân viên mới
     */
    public Employee addEmployee(Employee employee) {
        log.info("Thêm nhân viên mới: {}", employee.getFullName());
        return employeeRepository.save(employee);
    }
    
    /**
     * Cập nhật thông tin nhân viên
     */
    public Employee updateEmployee(Employee employee) {
        log.info("Cập nhật nhân viên ID: {}", employee.getEmpNo());
        if (!employeeRepository.existsById(employee.getEmpNo())) {
            throw new RuntimeException("Không tìm thấy nhân viên với ID: " + employee.getEmpNo());
        }
        return employeeRepository.save(employee);
    }
    
    /**
     * Xóa nhân viên theo ID
     */
    public void deleteEmployee(Long id) {
        log.info("Xóa nhân viên ID: {}", id);
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy nhân viên với ID: " + id);
        }
        employeeRepository.deleteById(id);
    }
    
    /**
     * Tìm kiếm nhân viên theo tên
     */
    public List<Employee> findByFirstName(String firstName) {
        log.info("Tìm nhân viên theo tên: {}", firstName);
        return employeeRepository.findByFirstName(firstName);
    }
    
    /**
     * Tìm kiếm nhân viên theo họ
     */
    public List<Employee> findByLastName(String lastName) {
        log.info("Tìm nhân viên theo họ: {}", lastName);
        return employeeRepository.findByLastName(lastName);
    }
    
    /**
     * Tìm kiếm nhân viên theo giới tính
     */
    public List<Employee> findByGender(Employee.Gender gender) {
        log.info("Tìm nhân viên theo giới tính: {}", gender);
        return employeeRepository.findByGender(gender);
    }
    
    /**
     * Tìm kiếm nhân viên theo khoảng ngày sinh
     */
    public List<Employee> findByBirthDateRange(LocalDate startDate, LocalDate endDate) {
        log.info("Tìm nhân viên sinh từ {} đến {}", startDate, endDate);
        return employeeRepository.findByBirthDateBetween(startDate, endDate);
    }
    
    /**
     * Tìm kiếm nhân viên theo tên đầy đủ
     */
    public List<Employee> findByFullName(String fullName) {
        log.info("Tìm nhân viên theo tên đầy đủ: {}", fullName);
        return employeeRepository.findByFullName(fullName);
    }
    
    /**
     * Đếm số lượng nhân viên theo giới tính
     */
    public long countByGender(Employee.Gender gender) {
        log.info("Đếm số nhân viên giới tính: {}", gender);
        return employeeRepository.countByGender(gender);
    }
    
    /**
     * Lấy 10 nhân viên được tuyển gần đây nhất
     */
    public List<Employee> getRecentHires() {
        log.info("Lấy 10 nhân viên được tuyển gần đây");
        return employeeRepository.findTop10RecentHires();
    }
}
