package iuh.fit.se.bai02;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp_XML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Employee emp = (Employee) context.getBean("employeeBean");
        emp.showInfo();
    }
}
