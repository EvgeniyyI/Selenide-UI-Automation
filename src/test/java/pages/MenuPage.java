package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MenuPage {

    private final SelenideElement menuButton = $(".menu-button-badge .el-button");

    private final SelenideElement menuDrawer = $(".el-drawer");

    private final ElementsCollection menuItems = $$(".el-menu-item span:not(.el-badge__content)");

    private final SelenideElement analyticsSubMenu = $(".el-sub-menu .el-sub-menu__title");
    private final ElementsCollection analyticsSubItems = $$(".el-sub-menu .el-menu-item span");

    private final SelenideElement logoutButton = $(".el-menu-item:last-child");

    public MenuPage openMenu() {
        menuButton.shouldBe(visible).click();
        menuDrawer.shouldBe(visible);
        return this;
    }

    public MenuPage closeMenu() {
        $(".el-button .el-icon .close").click();
        menuDrawer.shouldBe(hidden);
        return this;
    }

    public MenuPage shouldBeOpened() {
        menuDrawer.shouldBe(visible);
        return this;
    }

    public MenuPage shouldHaveMenuItemText(int index, String expectedText) {
        menuItems.get(index).shouldHave(text(expectedText));
        return this;
    }

    public ElementsCollection getMenuItems() {
        return menuItems;
    }

    public MenuPage shouldHaveMenuItem(String expectedText) {
        menuItems.findBy(text(expectedText)).shouldBe(visible);
        return this;
    }

    public MenuPage shouldNotHaveMenuItem(String expectedText) {
        menuItems.filterBy(text(expectedText)).shouldHave(size(0));
        return this;
    }

    public MenuPage shouldHaveAnalyticsSubMenu() {
        analyticsSubMenu.shouldBe(visible);
        return this;
    }

    public MenuPage shouldHaveAnalyticsSubItems(String... expectedTexts) {
        for (String text : expectedTexts) {
            analyticsSubItems.findBy(text(text)).shouldBe(visible);
        }
        return this;
    }
}