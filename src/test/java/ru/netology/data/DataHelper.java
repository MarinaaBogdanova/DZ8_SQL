package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    public static final Faker faker = new Faker(new Locale("en"));
    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }
    public static AuthInfo getWrongPasswordAuthInfo() {
        return new AuthInfo("vasya", "qwertyasd");
    }
    public static String getRandomPassword() {
        return faker.internet().password();
    }
    public static String getRandomLogin() {
        return faker.name().username();
    }
    public static AuthInfo getRandomUser() {
        return new AuthInfo(getRandomLogin(), getRandomPassword());
    }
    public static VerificationCode getRandomVerificationCode() {
        return new VerificationCode(faker.numerify("#####"));
    }
    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
    }
}