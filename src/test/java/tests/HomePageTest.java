package tests;

import config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends TestConfig {
    private final static String BASE_URL = "";

    @Test
    @DisplayName("Главная страница открывается для неавторизованного пользователя")
    void homePageForGuest() {
        new HomePage(BASE_URL)
                .shouldBeOpenedAsGuest()
                .shouldHaveAllMainSections();
    }

    @Test
    @DisplayName("Клик по кнопке 'Войти' переводит на страницу логина")
    void clickLoginRedirectsToLogin() {
        new HomePage(BASE_URL)
                .clickLogin()
                .shouldHaveTitle("Вход");
    }

    @Test
    @DisplayName("Клик по кнопке 'Почему выбирают нас' переводит на страницу регистрации")
    void clickRegisterRedirectsToRegister() {
        new HomePage(BASE_URL)
                .clickRegister()
                .shouldHaveTitle();
    }
}
