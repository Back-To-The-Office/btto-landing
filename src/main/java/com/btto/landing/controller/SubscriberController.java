package com.btto.landing.controller;

import com.btto.landing.model.SubscriberRequest;
import com.btto.landing.service.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import javax.validation.Valid;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/landing")
public class SubscriberController {

    private final MailService mailService;
    private ExecutorService mailSenderExecutorService;

    @Autowired
    public SubscriberController(MailService mailService) {
        this.mailService = mailService;
        this.mailSenderExecutorService = Executors.newSingleThreadExecutor();
    }

    @PreDestroy
    private void shutdown() throws InterruptedException {
        if (mailSenderExecutorService != null) {
            mailSenderExecutorService.shutdownNow();
            mailSenderExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        }
        mailSenderExecutorService = null;
    }

    @PostMapping("send")
    public String sendSubscriber(@Valid @RequestBody final SubscriberRequest subscriberRequest) {
        final StringBuilder mailBody = new StringBuilder("name: " + subscriberRequest.getName() + "\nemail: " + subscriberRequest.getEmail());
        if (StringUtils.isNotBlank(subscriberRequest.getMessage())) {
            mailBody.append("\nmessage: ").append(subscriberRequest.getMessage());
        }
        mailSenderExecutorService.execute(() -> mailService.sendEmail("New subscriber", mailBody.toString()));
        return "";
    }
}
