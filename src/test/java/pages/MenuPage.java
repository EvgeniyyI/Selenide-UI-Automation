package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MenuPage {
    
    // Кнопка открытия меню (бургер)
    private final SelenideElement menuButton = $(".menu-button-badge .el-button");
    
    // Само меню (drawer)
    private final SelenideElement menuDrawer = $(".el-drawer");
    
    // Все пункты меню (элементы с текстом)
    private final ElementsCollection menuItems = $$(".el-menu-item span:not(.el-badge__content)");
    
    // Подменю (Analytics)
    private final SelenideElement analyticsSubMenu = $(".el-sub-menu .el-sub-menu__title");
    private final ElementsCollection analyticsSubItems = $$(".el-sub-menu .el-menu-item span");
    
    // Кнопка "Выход"
    private final SelenideElement logoutButton = $(".el-menu-item:last-child");
    
    // Открыть меню
    public MenuPage openMenu() {
        menuButton.shouldBe(visible).click();
        menuDrawer.shouldBe(visible);
        return this;
    }
    
    // Закрыть меню (опционально)
    public MenuPage closeMenu() {
        $(".el-button .el-icon .close").click();
        menuDrawer.shouldBe(hidden);
        return this;
    }
    
    // Проверка, что меню открыто
    public MenuPage shouldBeOpened() {
        menuDrawer.shouldBe(visible);
        return this;
    }
    
    // Проверка текста пункта меню по индексу
    public MenuPage shouldHaveMenuItemText(int index, String expectedText) {
        menuItems.get(index).shouldHave(text(expectedText));
        return this;
    }
    
    // Геттер для всех пунктов меню
    public ElementsCollection getMenuItems() {
        return menuItems;
    }
    
    // Проверка, что пункт меню существует и содержит текст
    public MenuPage shouldHaveMenuItem(String expectedText) {
        menuItems.findBy(text(expectedText)).shouldBe(visible);
        return this;
    }
    
    // Проверка, что пункт меню НЕ существует
    public MenuPage shouldNotHaveMenuItem(String expectedText) {
        // Проверяем, что элемент с таким текстом отсутствует в меню
        menuItems.filterBy(text(expectedText)).shouldHave(size(0));
        return this;
    }
    
    // Проверка подменю Analytics
    public MenuPage shouldHaveAnalyticsSubMenu() {
        analyticsSubMenu.shouldBe(visible);
        return this;
    }
    
    // Проверка пунктов в подменю Analytics
    public MenuPage shouldHaveAnalyticsSubItems(String... expectedTexts) {
        for (String text : expectedTexts) {
            analyticsSubItems.findBy(text(text)).shouldBe(visible);
        }
        return this;
    }
}