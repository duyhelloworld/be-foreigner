package vn.edu.huce.beforeigner.infrastructures.commonmodule.impls;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IEmailService;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    
    private final JavaMailSender mailSender;
    
    @Override
    public void send(String to, String from, String subject, String text) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");
        helper.setText(text, true);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        mailSender.send(mailMessage);
    }
    
}
