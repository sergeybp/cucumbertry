package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by sergeybp on 05.06.17.
 */
public class Step {


    @Given("^I am on ([^\\\"]*) page$")
    public void preconditionExecute(String pageName){
        // Initialize driver
        init();
        // Move to given page
        if(pageName.equals("Home")){
            moveToMainPage();
        } else {
            System.err.println("No page found.");
            System.exit(0);
        }
    }


    @When("^I type credentials as ([^\\\"]*)/([^\\\"]*)$")
    public void loginExecute(String userName, String userPassword){
        // Setting userName
        WebElement textField = driver.findElement(By.id("Login"));
        textField.sendKeys(userName);
        //Setting userPassword
        textField = driver.findElement(By.id("Password"));
        textField.sendKeys(userPassword);
        // Click login button
        driver.findElement(By.className("uui-button")).click();
    }

    @Then("^Login ([^\\\"]*)$")
    public void checkStatus(String status) throws Throwable {
        boolean isCorrect = false;
        if(status.equals("succeeded")){
            isCorrect = true;
        }
        // Checking login status
        Assert.assertTrue(driver.findElement(By.cssSelector(".profile-photo span")).isDisplayed() == isCorrect);
    }

    private WebDriver driver;

    private void init() {
        System.setProperty("webdriver.gecko.driver", "/home/sergeybp/geckodriver");
        driver = new FirefoxDriver();
    }

    private void moveToMainPage(){
        // Moving to epam test site
        driver.navigate().to("https://epam.github.io/JDI/");
        // Wait for page loading complete
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("profile-photo"))).click();
    }

}
