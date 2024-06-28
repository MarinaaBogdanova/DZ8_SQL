package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static final SelenideElement header = $("[data-test-id='dashboard']");

    public DashboardPage() {
        header.shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
    }
}