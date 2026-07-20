package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    private final SelenideElement title = $x("//h2[text()='Регистрация']");
    private final SelenideElement subtitle = $("p.text-gray-600");

    private final SelenideElement loginField = $("input[placeholder='Логин']");
    private final SelenideElement passwordField = $("input[placeholder='Пароль']");
    private final SelenideElement surnameField = $("input[placeholder='Фамилия']");
    private final SelenideElement firstNameField = $("input[placeholder='Имя']");
    private final SelenideElement lastNameField = $("input[placeholder='Отчество']");
    private final SelenideElement emailField = $("input[placeholder='Email']");
    private final SelenideElement phoneField = $("input[placeholder='Телефон']");

    private final SelenideElement registerButton = $x("//button/span[text()='Зарегистрироваться']");
    private final SelenideElement loginButton = $x("//button/span[text()='Вход']");

    private final SelenideElement errorAlert = $(".el-alert--error");
    private final SelenideElement successAlert = $(".el-alert--success");

   public RegisterPage open() {
        Selenide.open("/register");
        return this;
    }

    public RegisterPage shouldBeOpened() {
        title.shouldHave(exactText("Регистрация"));
        subtitle.shouldBe(visible);
        registerButton.shouldBe(visible);
        return this;
    }

    public RegisterPage fillMainFields(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        return this;
    }

    public RegisterPage fillPersonData(String surname, String firstName, String lastName, String email, String phone) {
        surnameField.setValue(surname);
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        emailField.setValue(email);
        phoneField.setValue(phone);
        return this;
    }

    public RegisterPage clickRegister() {
        registerButton.click();
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();

        return new LoginPage().open();
    }

    public RegisterPage registerAs(String login, String password, String surname, String firstName,
                                   String lastName, String email, String phone) {
        fillMainFields(login, password);
        fillPersonData(surname, firstName, lastName, email, phone);
        clickRegister();
        return this;
    }

    // ==================== Проверки ====================

    public RegisterPage shouldShowSuccessMessage(String expectedText) {
        successAlert.shouldBe(visible);
        if (!expectedText.isEmpty()) {
            successAlert.shouldHave(text(expectedText));
        }
        return this;
    }

    public RegisterPage shouldShowError(String expectedText) {
        errorAlert.shouldBe(visible);
        errorAlert.shouldHave(text(expectedText));
        return this;
    }

    public RegisterPage shouldShowValidationError() {
        $("div.el-message--error").shouldHave(text("Заполните все поля"));
        return this;
    }

    public LoginPage successShouldRedirectToLogin() {
        // После успеха должна быть переадресация на /login через 1.5 секунды
        return new LoginPage().shouldBeOpened();
    }

    public RegisterPage shouldStayOnRegisterPage() {
        title.shouldHave(exactText("Регистрация"));
        return this;
    }

    public RegisterPage fillMinimalData(String login, String password) {
        fillMainFields(login, password);
        fillPersonData("Тестов", "Тест", "Тестович", "test@example.com", "+375291234455");
        return this;
    }
}
