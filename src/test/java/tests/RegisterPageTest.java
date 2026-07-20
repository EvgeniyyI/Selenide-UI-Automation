package tests;

import config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterPageTest extends TestConfig {

    @Test
    @DisplayName("Страница регистрации открывается корректно")
    void registerPageShouldOpen() {
        new RegisterPage()
                .open()
                .shouldBeOpened();
    }

    @Test
    @DisplayName("Успешная регистрация + редирект на логин")
    void successfulRegistration() {
        new RegisterPage()
                .open()
                .fillMinimalData("newuser_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), "Password123!")
                .clickRegister()
                .shouldShowSuccessMessage("Регистрация прошла успешно!")
                .successShouldRedirectToLogin();
    }

    @Test
    @DisplayName("Ошибка валидации при пустых полях")
    void validationErrorOnEmptyFields() {
        new RegisterPage()
                .open()
                .clickRegister()
                .shouldShowValidationError()
                .shouldStayOnRegisterPage();
    }

    @Test
    @DisplayName("Ошибка при попытке зарегистрировать существующего пользователя")
    void duplicateUserError() {
        new RegisterPage()
                .open()
                .fillMinimalData("user", "user123")
                .clickRegister()
                .shouldShowError("Пользователь с таким логином уже существует");
    }

    @Test
    @DisplayName("Кнопка 'Вход' переводит обратно на страницу логина")
    void clickLoginRedirectsToLogin() {
        new RegisterPage()
                .open()
                .clickLoginButton()
                .shouldBeOpened();
    }
}
