package iuh.fit.se.lab07.repositories;

import iuh.fit.se.lab07.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface cho Employee entity
 * Kế thừa JpaRepository để có sẵn các method CRUD cơ bản
 * Định nghĩa các method tìm kiếm custom theo nhu cầu
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * Tìm kiếm nhân viên theo tên (first name)
     * Spring Data JPA tự động generate query từ tên method
     */
    List<Employee> findByFirstName(String firstName);
    
    /**
     * Tìm kiếm nhân viên theo họ (last name)
     */
    List<Employee> findByLastName(String lastName);
    
    /**
     * Tìm kiếm nhân viên theo giới tính
     */
    List<Employee> findByGender(Employee.Gender gender);
    
    /**
     * Tìm kiếm nhân viên theo khoảng ngày sinh
     */
    List<Employee> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
    
    /**
     * Tìm kiếm nhân viên được tuyển dụng sau một ngày cụ thể
     */
    List<Employee> findByHireDateAfter(LocalDate date);
    
    /**
     * Tìm kiếm nhân viên theo tên hoặc họ (chứa chuỗi tìm kiếm)
     */
    List<Employee> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    
    /**
     * Tìm kiếm nhân viên bằng JPQL query
     * Tìm theo tên đầy đủ (kết hợp first name và last name)
     */
    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName) LIKE %:fullName%")
    List<Employee> findByFullName(@Param("fullName") String fullName);
    
    /**
     * Đếm số lượng nhân viên theo giới tính bằng JPQL
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.gender = :gender")
    long countByGender(@Param("gender") Employee.Gender gender);
    
    /**
     * Tìm kiếm nhân viên bằng Native SQL query
     * Tìm 10 nhân viên được tuyển gần đây nhất
     */
    @Query(value = "SELECT * FROM employees ORDER BY hire_date DESC LIMIT 10", nativeQuery = true)
    List<Employee> findTop10RecentHires();
}
