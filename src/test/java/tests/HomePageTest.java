package tests;

import config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends TestConfig {
    @Test
    @DisplayName("Главная страница открывается для неавторизованного пользователя")
    void homePageForGuest() {
        new HomePage().open()
                .shouldBeOpenedAsGuest()
                .shouldHaveAllMainSections();
    }

    @Test
    @DisplayName("Клик по кнопке 'Войти' переводит на страницу логина")
    void clickLoginRedirectsToLogin() {
        new HomePage().open()
                .clickLogin()
                .shouldHaveTitle("Вход");
    }

    @Test
    @DisplayName("Клик по кнопке 'Почему выбирают нас' переводит на страницу регистрации")
    void clickRegisterRedirectsToRegister() {
        new HomePage().open()
                .clickRegister()
                .shouldHaveTitle();
    }
}
