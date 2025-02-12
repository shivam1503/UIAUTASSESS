package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageObjects {
    private WebDriver driver;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");


    private final By productPageLogo = By.xpath("//div[contains(@class, 'app_logo') and text()='Swag Labs']");
    private final By productPageTitle = By.xpath("//span[contains(@class, 'title') and text()='Products']");
    private final By cartButton = By.xpath("//a[@class='shopping_cart_link']");

    private final By checkoutBtn = By.id("checkout");


    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    private final By overviewTitle = By.xpath("//span[contains(text(), 'Overview')]");

    private final By finishBtn = By.id("finish");

    private final By successMessage = By.xpath("//div[@id='checkout_complete_container']/h2");

    public PageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void appLogoVisible() {
        driver.findElement(productPageLogo).isDisplayed();
    }

    public void productPageTitleVisible() {
        driver.findElement(productPageTitle).isDisplayed();
    }

    public void clickAddToCart(String productName) {
        String itemAddToCartBtn = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item_description']//button[text()='Add to cart']", productName);
        driver.findElement(By.xpath(itemAddToCartBtn)).click();
    }
    // ^ this was challenging and this is not how I wanted to implement it, but it seems the most feasible way

    public void clickCartButton() {
        driver.findElement(cartButton).click();
    }

    public boolean verifyItemInCart(String itemName) {
        String cartItem = String.format("//div[text()='%s']", itemName);
        return driver.findElement(By.xpath(cartItem)).isDisplayed();
    }

    public void clickCheckoutBtn() {
        driver.findElement(checkoutBtn).click();
    }

    public void enterpersonalInfo(String fstName, String lstname, String postCode){
        driver.findElement(firstNameInput).sendKeys(fstName);
        driver.findElement(lastNameInput).sendKeys(lstname);
        driver.findElement(postalCode).sendKeys(postCode);
    }

    public void clickContinueBtn() {
        driver.findElement(continueBtn).click();
    }

    public void overViewPage(){
        driver.findElement(overviewTitle).isDisplayed();
    }

    public void clickFinishBtn(){
        driver.findElement(finishBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
