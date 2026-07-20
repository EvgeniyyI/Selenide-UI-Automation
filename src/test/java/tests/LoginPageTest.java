package tests;

import config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.RegisterPage;
//import pages.ProfilePage;

import static com.codeborne.selenide.Condition.visible;

public class LoginPageTest extends TestConfig {
    @Test
    @DisplayName("Страница логина открывается корректно")
    void loginPageShouldOpen() {
        new LoginPage()
                .open()
                .shouldBeOpened();
    }

    @Test
    @DisplayName("Успешный логин с валидными данными")
    void successfulLogin() {
        new LoginPage()
                .open()
                .loginAs("user", "user123")
                .shouldShowSuccessMessage();

//        // Проверка редиректа
//        new ProfilePage().shouldBeOpened();
    }

    @Test
    @DisplayName("Ошибка при пустых полях")
    void validationErrorOnEmptyFields() {
        new LoginPage().open()
                .clickLogin()
                .shouldShowValidationError()
                .shouldStayOnLoginPage();
    }

    @Test
    @DisplayName("Ошибка при неверных учётных данных")
    void invalidCredentials() {
        new LoginPage()
                .open()
                .loginAs("wrong_user", "wrong_pass")
                .shouldShowError("Неверный логин или пароль")
                .shouldStayOnLoginPage();
    }

    @Test
    @DisplayName("Кнопка 'Регистрация' переводит на страницу регистрации")
    void clickRegisterRedirects() {
        new LoginPage()
                .open()
                .clickRegisterButton();

        new RegisterPage().shouldBeOpened();
    }
}
