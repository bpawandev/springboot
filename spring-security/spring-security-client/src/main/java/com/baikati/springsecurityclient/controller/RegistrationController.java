package com.baikati.springsecurityclient.controller;

import com.baikati.springsecurityclient.dto.PasswordDTO;
import com.baikati.springsecurityclient.dto.UserDTO;
import com.baikati.springsecurityclient.entity.PasswordResetToken;
import com.baikati.springsecurityclient.entity.User;
import com.baikati.springsecurityclient.entity.VerificationToken;
import com.baikati.springsecurityclient.event.RegistrationCompleteEvent;
import com.baikati.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO, final HttpServletRequest request) {
        User user = userService.registerUser(userDTO);
        publisher.publishEvent(new RegistrationCompleteEvent(user, getApplicationUrl(request)));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "user verified successfully";
        }
        return "bad user";
    }

    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenEmail(user, getApplicationUrl(request), verificationToken);
        return "Verification token sent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordDTO passwordDto, HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordDto.getEmail());
        String url = "";
        if (Objects.nonNull(user)) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(user, getApplicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token, @RequestBody PasswordDTO passwordDTO) {
        String result = userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")) {
            return "invalid token";
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if (user.isPresent()) {
            userService.changePassword(user.get(), passwordDTO.getNewPassword());
            return "password reset successfully";
        } else {
            return "invalid token";
        }

    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordDTO passwordDTO) {
        User user = userService.findUserByEmail(passwordDTO.getEmail());
        if (!userService.isValidPassword(user, passwordDTO.getOldPassword())) {
            return "invalid Old Password";
        }
        userService.changePassword(user, passwordDTO.getNewPassword());
        return "Password changed successfully";
    }

    public String passwordResetTokenMail(User user, String appUrl, String token) {
        String url = appUrl + "/savePassword?token=" + token;
        log.info("click the link to reset your password {} ", url);
        return url;
    }

    private void resendVerificationTokenEmail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();
        log.info("Click the link to verify you account: {} ", url);
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort()
                + request.getServletContext().getContextPath();
    }
}
