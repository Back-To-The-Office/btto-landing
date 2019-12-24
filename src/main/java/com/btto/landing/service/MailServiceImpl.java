package com.btto.landing.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final String[] recipients;

    public MailServiceImpl(@Autowired JavaMailSender javaMailSender, @Value("${mail.service.recipients}") String recipientsStrings) {
        this.javaMailSender = javaMailSender;
        this.recipients = Arrays.stream(recipientsStrings.split(","))
                .map(StringUtils::trimToNull)
                .filter(Objects::nonNull)
                .toArray(String[]::new);
    }

    @Override
    public void sendEmail(final String subject, final String message) {
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipients);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
    }

}
