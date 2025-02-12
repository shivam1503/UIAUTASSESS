package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.ConfigReader;

import static stepDefinitions.Hooks.pgObj;

public class StepDefinition extends BaseTest{
    String userName = ConfigReader.getProperty("username");
    String password = ConfigReader.getProperty("password");


    @And("I log in to the application")
    public void login() {
        pgObj.enterUsername(userName);
        pgObj.enterPassword(password);
        pgObj.clickLogin();
    }

    @And("I am on products page")
    public void verifyProductsPage() {
        pgObj.appLogoVisible();
        pgObj.productPageTitleVisible();
    }

    @When("I add to cart {string} item")
    public void addToCartItem(String itemName) {
        pgObj.clickAddToCart(itemName);
    }

    @And("I go to cart page")
    public void goToCart() {
        pgObj.clickCartButton();
    }

    @Then("I should see {string} item in the cart")
    public void verifyItemInCart(String itemName) {
        Assert.assertTrue(pgObj.verifyItemInCart(itemName));
    }

    @And("I go to checkout page")
    public void goToCheckoutPage() {
        pgObj.clickCheckoutBtn();
    }

    @And("I enter personal information")
    public void enterPersonalInfo() {
        pgObj.enterpersonalInfo("shivam", "rai", "400001");
    }

    @And("I click on continue button")
    public void clickContinueBtn() {
        pgObj.clickContinueBtn();
    }

    @And("I am on overview page")
    public void verifyOverviewPage() {
        pgObj.overViewPage();
    }

    @And("I click on finish button")
    public void clickFinishButton(){
        pgObj.clickFinishBtn();
    }

    @And("I see success message {string}")
    public void verifySuccessMessage(String message) {
        Assert.assertEquals(pgObj.getSuccessMessage(), message);
    }
}
