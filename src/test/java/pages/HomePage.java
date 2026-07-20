package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private final SelenideElement homePageTitle = $x("//h1").should(exist);
    private final SelenideElement homePageDescription = $x("//h1/following-sibling::p");

    private final SelenideElement loginButton = $x("//button[1]");
    private final SelenideElement registerButton = $x("//button[2]");

    private final SelenideElement howItWorksSection = $x("//h2[contains(text(),'Как это работает')]");
    private final SelenideElement benefitsSection = $x("//h2[contains(text(), 'Почему выбирают нас')]");

    public HomePage open() {
        Selenide.open("");
        return this;
    }

    public HomePage shouldBeOpenedAsGuest() {
        homePageTitle.shouldHave(text("Добро пожаловать в Центр Социальной Помощи"));
        homePageDescription.shouldBe(visible);
        loginButton.shouldBe(visible);
        registerButton.shouldBe(visible);
        return this;
    }

    public LoginPage clickLogin() {
        loginButton.click();
        return new LoginPage();
    }

    public RegisterPage clickRegister() {
        registerButton.click();
        return new RegisterPage();
    }

    public HomePage shouldHaveAllMainSections() {
        howItWorksSection.shouldBe(visible);
        benefitsSection.shouldBe(visible);
        return this;
    }

}
