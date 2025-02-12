package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageObjectsTest3 {
    private WebDriver driver;

    private final By menuButton = By.id("react-burger-menu-btn");
    private final By appStateResetButton = By.id("reset_sidebar_link");

    public PageObjectsTest3(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMenuButton() {
        driver.findElement(menuButton).click();
    }

    public boolean verifyRemoveButton(String itemName) {
        String removeButton = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item_description']//button[text()='Remove']", itemName);
        return driver.findElement(By.xpath(removeButton)).isDisplayed();
    }

    public void clickAppStateResetButton() {
        driver.findElement(appStateResetButton).click();
    }
}
