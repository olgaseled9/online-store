package by.itacademy.javaenterprise.seledtsova.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from:no-reply@online-store.local}")
    private String from;

    @Override
    @Transactional(readOnly = true)
    public void sendOrderConfirmation(String to, Long orderId) {
        String subject = "Подтверждение заказа #" + orderId;
        String body = String.format("Спасибо за заказ #%s. Заказ находится в статусе",orderId);
        sendPlainText(to, subject, body);
    }

    @Override
    public void sendPlainText(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        send(msg);
    }

    private void send(SimpleMailMessage msg) {
        try {
            mailSender.send(msg);
        } catch (Exception e) {
            log.error("Email send failed: {}", e.getMessage(), e);
        }
    }
}

