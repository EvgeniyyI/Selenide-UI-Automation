package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement authFormTitle = $x("//h2[text()='Вход']");

    public LoginPage shouldHaveTitle(String expectedTitle) {
        authFormTitle.shouldHave(text(expectedTitle));
        return this;
    }
}
