package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id='login'] input");
    private final SelenideElement passwordField = $("[data-test-id='password'] input");
    private final SelenideElement loginButton = $("[data-test-id='action-login']");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void verificationErrorMassage(String expectedText) {
        errorNotification.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }

    public void invalidVerification3times() {
        loginField.setValue(DataHelper.getAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getWrongPasswordAuthInfo().getPassword());
        loginButton.click();
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
        loginField.setValue(DataHelper.getAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getWrongPasswordAuthInfo().getPassword());
        loginButton.click();
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
        loginField.setValue(DataHelper.getAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getWrongPasswordAuthInfo().getPassword());
        loginButton.click();
    }

}