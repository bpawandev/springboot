package com.baikati.springsecurityclient.event.listener;

import com.baikati.springsecurityclient.entity.User;
import com.baikati.springsecurityclient.event.RegistrationCompleteEvent;
import com.baikati.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the Verification token for the User with the link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);
        //send mail to user

        String url = event.getAppUrl() + "/verifyRegistration?token=" + token;
        log.info("Click the link to verify you account: {} ", url);
    }
}
