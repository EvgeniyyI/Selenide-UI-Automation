package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ProfilePage {

    public ProfilePage open() {
        Selenide.open("/profile");
        return this;
    }

    public ProfilePage shouldBeOpened() {

        return this;
    }
}
