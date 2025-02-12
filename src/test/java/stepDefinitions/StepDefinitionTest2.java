package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static stepDefinitions.Hooks.pgObj2;

public class StepDefinitionTest2 extends BaseTest {

    @When("I click on {string} dropdown option")
    public void clickSortDropdownBtn(String dropdownOption) {
        pgObj2.clickSortDropdownBtn(dropdownOption);
    }

    @Then("I see the products are sorted in {string} order of name")
    public void verifySortedOrder(String sortOrder) {
        List<WebElement> productNameElements = pgObj2.getProductNames();
        List<String> productNames = new ArrayList<>();
        for (WebElement product : productNameElements) {
            productNames.add(product.getText());
        }
        List<String> sortedProductNames = new ArrayList<>(productNames);
        if(Objects.equals(sortOrder, "ascending")) {
            Collections.sort(sortedProductNames);
        }
        else if(Objects.equals(sortOrder, "descending")) {
            Collections.sort(sortedProductNames, Collections.reverseOrder());
        }
        Assert.assertEquals(productNames, sortedProductNames, "The product list is not sorted!");
    }
}
