Feature: Test Feature

  Background:
    Given I log in to the application

  @test1
  Scenario Outline: checkout end to end
    And I am on products page
    When I add to cart '<itemName>' item
    And I go to cart page
    Then I should see '<itemName>' item in the cart
    And I go to checkout page
    And I enter personal information
    And I click on continue button
    And I am on overview page
    And I click on finish button
    And I see success message 'Thank you for your order!'

    Examples:
    | itemName |
    | Sauce Labs Bike Light |

    @test2
    Scenario: Products sorting
      And I am on products page
      When I click on 'Name (A to Z)' dropdown option
      Then I see the products are sorted in 'ascending' order of name
      When I click on 'Name (Z to A)' dropdown option
      Then I see the products are sorted in 'descending' order of name

      # assuming clicking on the app state reste button will change the button state of 'remove' to 'add to cart'
    # seems like a bug so the test case will fail
    @test3
    Scenario: Verify Reset App State
      And I am on products page
      When I add to cart 'Sauce Labs Bike Light' item
      And I should see remove button for 'Sauce Labs Bike Light' item
      And I click on menu button
      And I click on reset app state button
      Then I should not see remove button for 'Sauce Labs Bike Light' item
      