package com.example.deliverable_1_user_interface.data;

import com.example.deliverable_1_user_interface.data.model.LoggedInUser;

import java.io.IOException;
import java.util.Objects;

import javax.security.auth.login.LoginException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) throws LoginException {

        try {
            String testUsername = "Joseph";
            String testPassword = "1234567890";




            if (Objects.equals(username, testUsername) && Objects.equals(password, testPassword)) {
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                "Joseph");
                return new Result.Success<>(fakeUser);
            }



            else {
                throw new LoginException("Incorrect Username or Password!!");
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public static void logout() {
        LoginRepository.logout();
        // TODO: revoke authentication
    }
}