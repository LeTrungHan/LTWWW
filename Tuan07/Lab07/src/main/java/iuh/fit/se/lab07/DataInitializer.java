package iuh.fit.se.lab07;

import iuh.fit.se.lab07.entities.Employee;
import iuh.fit.se.lab07.serverces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final EmployeeService employeeService;
    
    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra nếu đã có dữ liệu thì không khởi tạo lại
        if (employeeService.getAllEmployees().isEmpty()) {
            log.info("===== BẮT ĐẦU KHỞI TẠO DỮ LIỆU MẪU =====");
            initSampleData();
            log.info("===== HOÀN THÀNH KHỞI TẠO DỮ LIỆU MẪU =====");
        } else {
            log.info("Database đã có dữ liệu, bỏ qua khởi tạo");
        }
    }
    
    private void initSampleData() {
        // Thêm 10 nhân viên mẫu
        Employee emp1 = new Employee(null,
                LocalDate.of(1990, 5, 15),
                "Nguyễn",
                "Văn An",
                Employee.Gender.M,
                LocalDate.of(2015, 3, 10));
        
        Employee emp2 = new Employee(null,
                LocalDate.of(1992, 8, 20),
                "Trần",
                "Thị Bình",
                Employee.Gender.F,
                LocalDate.of(2016, 6, 15));
        
        Employee emp3 = new Employee(null,
                LocalDate.of(1988, 12, 5),
                "Lê",
                "Văn Cường",
                Employee.Gender.M,
                LocalDate.of(2014, 1, 20));
        
        Employee emp4 = new Employee(null,
                LocalDate.of(1995, 3, 25),
                "Phạm",
                "Thị Dung",
                Employee.Gender.F,
                LocalDate.of(2018, 9, 5));
        
        Employee emp5 = new Employee(null,
                LocalDate.of(1991, 7, 10),
                "Hoàng",
                "Văn Em",
                Employee.Gender.M,
                LocalDate.of(2017, 4, 12));
        
        Employee emp6 = new Employee(null,
                LocalDate.of(1993, 11, 30),
                "Võ",
                "Thị Phượng",
                Employee.Gender.F,
                LocalDate.of(2019, 2, 28));
        
        Employee emp7 = new Employee(null,
                LocalDate.of(1989, 4, 8),
                "Đỗ",
                "Văn Giang",
                Employee.Gender.M,
                LocalDate.of(2015, 8, 15));
        
        Employee emp8 = new Employee(null,
                LocalDate.of(1994, 9, 18),
                "Bùi",
                "Thị Hà",
                Employee.Gender.F,
                LocalDate.of(2020, 1, 10));
        
        Employee emp9 = new Employee(null,
                LocalDate.of(1990, 2, 14),
                "Đinh",
                "Văn Hùng",
                Employee.Gender.M,
                LocalDate.of(2016, 11, 20));
        
        Employee emp10 = new Employee(null,
                LocalDate.of(1996, 6, 22),
                "Ngô",
                "Thị Lan",
                Employee.Gender.F,
                LocalDate.of(2021, 5, 15));
        
        // Lưu vào database
        employeeService.addEmployee(emp1);
        employeeService.addEmployee(emp2);
        employeeService.addEmployee(emp3);
        employeeService.addEmployee(emp4);
        employeeService.addEmployee(emp5);
        employeeService.addEmployee(emp6);
        employeeService.addEmployee(emp7);
        employeeService.addEmployee(emp8);
        employeeService.addEmployee(emp9);
        employeeService.addEmployee(emp10);
        
        log.info("Đã thêm 10 nhân viên mẫu vào database");
        
        // Hiển thị thống kê
        long maleCount = employeeService.countByGender(Employee.Gender.M);
        long femaleCount = employeeService.countByGender(Employee.Gender.F);
        log.info("Thống kê: Nam: {}, Nữ: {}", maleCount, femaleCount);
    }
}
