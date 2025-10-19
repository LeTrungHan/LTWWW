package iuh.fit.se.bai01;

// Interface cho cac loai dich vu gui tin nhan
public interface MessageService {
    
    boolean sendMessage(String to, String message);
    String getServiceName();
}