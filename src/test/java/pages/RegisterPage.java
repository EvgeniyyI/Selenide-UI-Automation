package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    private final SelenideElement registerFromTitle = $x("//h2[text()='Регистрация']");

   public RegisterPage open() {
        Selenide.open("/register");
        return this;
    }

    public RegisterPage shouldBeOpened() {
        registerFromTitle.shouldBe(visible);
        return this;
    }
}
