package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement title = $("h2");
    private final SelenideElement subtitle = $("p.text-gray-600");

    private final SelenideElement loginField = $("input[placeholder='Логин']");
    private final SelenideElement passwordField = $("input[type='password']");
    private final SelenideElement submitButton = $("button[type='submit']");
    private final SelenideElement registerButton = $(".el-button--default");

    private final SelenideElement errorAlert = $(".el-alert--error");
    private final SelenideElement errorMessage = $(".el-alert--error .el-alert__title");

    public LoginPage open() {
        Selenide.open("/login");
        return this;
    }

    public LoginPage shouldBeOpened() {
        title.shouldHave(exactText("Вход"));
        subtitle.shouldBe(visible);
        loginField.shouldBe(visible);
        passwordField.shouldBe(visible);
        submitButton.shouldBe(visible);
        return this;
    }

    public LoginPage enterCredentials(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        return this;
    }

    public LoginPage clickLogin() {
        submitButton.click();
        return this;
    }

    public LoginPage loginAs(String login, String password) {
        enterCredentials(login, password);
        clickLogin();
        return this;
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();

        return new RegisterPage().open();
    }


    // ==================== Проверки ====================

    public LoginPage shouldShowSuccessMessage() {
        $("div.el-message--success").shouldBe(visible);
        return this;
    }

    public LoginPage shouldShowError(String expectedText) {
        errorAlert.shouldBe(visible);
        errorMessage.shouldHave(text(expectedText));
        return this;
    }

    public LoginPage shouldShowValidationError() {
        $("div.el-message--error").shouldHave(text("Заполните все поля"));
        return this;
    }

//    public ProfilePage successLoginShouldRedirectToProfile() {
//        return new ProfilePage().shouldBeOpened();
//    }

    public LoginPage shouldStayOnLoginPage() {
        title.shouldHave(exactText("Вход"));
        return this;
    }
}
