package iuh.fit.se.bai02;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("iuh.fit.se.bai02")
public class MainApp_Annotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApp_Annotation.class);
        Employee emp = context.getBean(Employee.class);
        emp.showInfo();
    }
}

