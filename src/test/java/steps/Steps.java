package steps;

import com.google.common.io.Files;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;

/**
 * Created by sergeybp on 05.06.17.
 */
public class Steps {


    @Step("Opening Home page.")
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


    @Step("Typing credentials.")
    @When("^I type credentials as ([^\\\"]*)/([^\\\"]*)$")
    public void loginExecute(String userName, String userPassword){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Login")));
        // Setting userName
        WebElement textField = driver.findElement(By.id("Login"));
        textField.sendKeys(userName);
        //Setting userPassword
        textField = driver.findElement(By.id("Password"));
        textField.sendKeys(userPassword);
    }

    @Step("Pressing Login button.")
    @When("^I press ([^\\\"]*) button$")
    public void pressButtonExecute(String buttonName){
        switch (buttonName){
            case "Login":
                // Click login button
                driver.findElement(By.className("uui-button")).click();
                break;
            default:
                System.err.println("No button found");
                System.exit(0);
        }
    }


    @Step("Checking Login status.")
    @Then("^Login ([^\\\"]*)$")
    public void checkStatus(String status) throws Throwable {
        boolean isCorrect = false;
        screenshot();
        switch (status){
            case "succeeded":
                isCorrect = true;
                break;
            case "failure":
                isCorrect = false;
                break;
            default:
                System.err.println("Wrong status.");
                System.exit(0);
        }
        // Checking login status
        Assert.assertTrue(driver.findElement(By.cssSelector(".profile-photo span")).isDisplayed() == isCorrect);
        driver.close();
    }

    private PhantomJSDriver driver;

    private void init() {
        System.setProperty("phantomjs.binary.path", "/home/sergeybp/phantomjs");
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        driver = new PhantomJSDriver(capabilities);
    }

    private void moveToMainPage(){
        // Moving to epam test site
        driver.navigate().to("https://epam.github.io/JDI/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        // Wait for page loading complete
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("profile-photo"))).click();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        return Files.toByteArray(scrFile);
    }


}
