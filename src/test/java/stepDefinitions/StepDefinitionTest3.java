package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import org.junit.Assert;

import static stepDefinitions.Hooks.pgObj3;

public class StepDefinitionTest3 extends BaseTest {

    @And("I click on menu button")
    public void clickMenuButton() {
        pgObj3.clickMenuButton();
    }

    @And("I should see remove button for {string} item")
    public void verifyRemoveButton(String itemName) {
        Assert.assertTrue(pgObj3.verifyRemoveButton(itemName));
    }

    @And("I click on reset app state button")
    public void clickAppStateResetButton() {
        pgObj3.clickAppStateResetButton();
    }

    @And("I should not see remove button for {string} item")
    public void verifyRemoveButtonNotVisible(String itemName) {
        Assert.assertFalse(pgObj3.verifyRemoveButton(itemName));
    }
}
