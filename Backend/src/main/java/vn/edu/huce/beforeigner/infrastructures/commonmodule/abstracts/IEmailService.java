package vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts;

import jakarta.mail.MessagingException;

public interface IEmailService {
    
    void send(String to, String from, String subject, String text) throws MessagingException ;
}
