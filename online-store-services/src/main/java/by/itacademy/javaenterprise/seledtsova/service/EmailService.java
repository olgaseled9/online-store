package by.itacademy.javaenterprise.seledtsova.service;

public interface EmailService {

    void sendPlainText(String to, String subject, String text);
    void sendOrderConfirmation(String to, Long orderId);
}
