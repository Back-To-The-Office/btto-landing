package com.btto.landing.controller;

import com.btto.landing.model.Subscriber;
import com.btto.landing.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import javax.validation.Valid;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Controller
public class LandingController {

    private final MailService mailService;
    private ExecutorService mailSenderExecutorService;

    @Autowired
    public LandingController(MailService mailService) {
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

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("subscriber", new Subscriber());
        return "index";
    }

    @PostMapping("/subscriber")
    public String sendSubscriber(@Valid @ModelAttribute("subscriber") final Subscriber subscriber, final Model model) {
        model.addAttribute("subscriber", subscriber);
        mailSenderExecutorService.execute(() -> mailService.sendEmail(
                "New subscriber",
                "name: " + subscriber.getName() + "\nemail: " + subscriber.getEmail())
        );
        return "thanks";
    }
}
