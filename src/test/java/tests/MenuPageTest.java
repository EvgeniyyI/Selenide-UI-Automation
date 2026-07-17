package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.MenuPage;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.CollectionCondition.texts;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuPageTest extends TestConfig {

    private MenuPage menuPage;
    private HomePage homePage;
    private final String BASE_URL = "/";
    
    @BeforeEach
    void setUpMenu() {
        homePage = new HomePage(BASE_URL);
        menuPage = new MenuPage();
    }
    
    // ========================================
    // ТЕСТ 1: Проверка пунктов меню роли USER
    // ========================================
    @Test
    @DisplayName("Левое меню содержит все обязательные пункты для пользователя")
    void menuShouldContainAllRequiredItems() {
        menuPage.openMenu();
        
        List<String> actualMenuTexts = menuPage.getMenuItems().texts();
        
        // Ожидаемые пункты меню (доступны всем)
        List<String> expectedMenuItems = Arrays.asList(
            "Главная",
            "Профиль",
            "Сообщения",
            "Выход"
        );
        
        assertThat(actualMenuTexts)
            .containsAll(expectedMenuItems)
            .hasSize(expectedMenuItems.size());

        menuPage.closeMenu();
    }
    
    // ========================================
    // ТЕСТ 2: Детальная проверка каждого пункта (роль ADMIN или SOCIAL_WORKER)
    // ========================================
    @Test
    @DisplayName("Каждый пункт меню имеет правильный текст и видим")
    void eachMenuItemShouldHaveCorrectText() {
        menuPage.openMenu();

        menuPage.shouldHaveMenuItemText(0, "Главная")
                .shouldHaveMenuItemText(1, "Профиль")
                .shouldHaveMenuItemText(2, "График")
                .shouldHaveMenuItemText(3, "Аналитика")
                .shouldHaveMenuItemText(4, "Классификация клиентов")
                .shouldHaveMenuItemText(5, "Заявления")
                .shouldHaveMenuItemText(6, "Сообщения")
                .shouldHaveMenuItemText(7, "Выход");
    }
    
    // ========================================
    // ТЕСТ 3: Проверка сценария для роли ADMIN
    // ========================================
    @Test
    @DisplayName("Администратор видит все пункты меню, включая Админ-панель")
    void adminShouldSeeAllMenuItems() {
        menuPage.openMenu();
        
        // Все пункты для админа
        List<String> adminItems = Arrays.asList(
            "Главная",
            "Профиль",
            "График",
            "Аналитика",
            "Админ-панель",
            "Классификация клиентов",
            "Заявления",
            "Сообщения",
            "Выход"
        );
        
        menuPage.getMenuItems()
            .shouldHave(texts(adminItems));
        
        menuPage.shouldHaveMenuItem("Админ-панель");
    }

    @Test
    @DisplayName("Обычный пользователь не видит админские пункты")
    void userShouldNotSeeAdminItems() {
        menuPage.openMenu();

        menuPage.shouldNotHaveMenuItem("Админ-панель");
    }
    
    // ========================================
    // ТЕСТ 4: Проверка подменю "Аналитика"
    // ========================================
    @Test
    @DisplayName("Подменю 'Аналитика' содержит все нужные пункты")
    void analyticsSubMenuShouldHaveAllItems() {
        menuPage.openMenu();

        menuPage.shouldHaveAnalyticsSubMenu()
                .shouldHaveAnalyticsSubItems(
                        "Демография АТЕ",
                    "Аналитика заявлений",
                    "Аналитика клиентов",
                    "Аналитика опросов");
    }
}