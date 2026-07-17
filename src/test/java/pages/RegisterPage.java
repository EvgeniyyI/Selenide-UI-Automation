package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    private final SelenideElement registerFromTitle = $x("//h2[text()='Регистрация']");

    public RegisterPage shouldHaveTitle() {
        registerFromTitle.shouldBe(visible);
        return this;
    }
}
