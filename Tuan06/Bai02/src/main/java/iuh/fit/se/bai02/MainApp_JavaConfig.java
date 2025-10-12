package iuh.fit.se.bai02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp_JavaConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee emp = context.getBean(Employee.class);
        emp.showInfo();
    }
}

