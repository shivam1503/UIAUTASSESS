# Test Automation Framework

This is a test automation framework for testing a web application using Java, Maven, Selenium, and Cucumber.

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome browser

## Project Structure

- `src/test/java`: Contains the Java test code.
  - `base`: Base classes for the tests.
  - `stepDefinitions`: Step definitions for Cucumber.
  - `features`: Cucumber feature files.
- `src/test/resources`: Contains the configuration files.
  - `config.properties`: Configuration properties for the tests.

## Configuration

Update the `config.properties` file with the appropriate values:

```ini
# Browser settings
browser=chrome

# Application URL
app.url=https://www.saucedemo.com

# Headless mode (true/false)
headless=false

# Login Credentials (for test users)
username=standard_user
password=secret_sauce
