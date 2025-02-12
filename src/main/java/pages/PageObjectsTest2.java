package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PageObjectsTest2 {
    private WebDriver driver;

    private final By sortDropdownBtn = By.xpath("//select[@class='product_sort_container']");
    private final By productNames = By.xpath("//div[@class='inventory_item_label']/a/div");
    public PageObjectsTest2(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSortDropdownBtn(String visibleText) {
        WebElement dropdownBtn = driver.findElement(sortDropdownBtn);
        Select dropdown = new Select(dropdownBtn);
        dropdown.selectByVisibleText(visibleText);
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(productNames);
    }
}
