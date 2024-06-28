package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.getUserStatus;


public class BankLoginTests {
    LoginPage loginPage;

    @AfterEach
    void tearDown() {
        SQLHelper.cleanAuthCodes();
    }
    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDB();
    }
    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999/", LoginPage.class);
    }

    @Test
    void shouldSuccessLoginWithLoginAndPassword() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());

    }
    @Test
    void shouldNotLoginWithUserDoesNotExist() {
        loginPage.validLogin(DataHelper.getRandomUser());
        loginPage.verificationErrorMassage("Ошибка! Неверно указан логин или пароль");
    }
    @Test
    void shouldNotLoginWithRandomVerificationCode() {
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        verificationPage.verificationPageVisible();
        verificationPage.verify(DataHelper.getRandomVerificationCode().getCode());
        verificationPage.verifyErrorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");

    }
    @Test
    void shouldNotLoginWithWrongPassword() {
        loginPage.validLogin(DataHelper.getWrongPasswordAuthInfo());
        loginPage.verificationErrorMassage("Ошибка! Неверно указан логин или пароль");
    }
    @Test
    void shouldBlockUserIfThreeOrMoreLoginWithWrongPassword() {
        loginPage.invalidVerification3times();
        Assertions.assertEquals("blocked", getUserStatus());
    }

}