package iuh.fit.se.lab07.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class đại diện cho bảng Employee trong database
 * Sử dụng JPA annotations để map với bảng trong database
 * Sử dụng Lombok để tự động generate getters, setters, constructors
 */
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    @EqualsAndHashCode.Include
    private Long empNo;
    
    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate birthDate;
    
    @Column(name = "first_name", nullable = false, length = 14)
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 14, message = "Tên không được vượt quá 14 ký tự")
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 16)
    @NotBlank(message = "Họ không được để trống")
    @Size(max = 16, message = "Họ không được vượt quá 16 ký tự")
    private String lastName;
    
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Giới tính không được để trống")
    private Gender gender;
    
    @Column(name = "hire_date", nullable = false)
    @NotNull(message = "Ngày tuyển dụng không được để trống")
    private LocalDate hireDate;
    
    /**
     * Enum định nghĩa giới tính
     */
    public enum Gender {
        M, // Male - Nam
        F  // Female - Nữ
    }
    
    /**
     * Method helper để lấy tên đầy đủ
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
