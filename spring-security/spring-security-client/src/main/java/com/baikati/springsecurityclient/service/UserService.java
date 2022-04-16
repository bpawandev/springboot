package com.baikati.springsecurityclient.service;

import com.baikati.springsecurityclient.dto.UserDTO;
import com.baikati.springsecurityclient.entity.User;
import com.baikati.springsecurityclient.entity.VerificationToken;

import java.util.Optional;

public interface UserService {
    User registerUser(UserDTO userDTO);

    void saveVerificationTokenForUser(User user, String token);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String token);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean isValidPassword(User user, String oldPassword);
}
