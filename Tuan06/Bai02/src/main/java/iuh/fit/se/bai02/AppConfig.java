package iuh.fit.se.bai02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Address address() {
        return new Address("Da Nang", "Hai Chau", "Vietnam");
    }

    @Bean
    public Employee employee() {
        Employee emp = new Employee();
        emp.setId(3001);
        emp.setName("Tran Binh");
        emp.setAddress(address());
        return emp;
    }
}

