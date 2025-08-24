package com.DemoQA;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Forms {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get(" https://demoqa.com/forms");
    }

    @Test
    public void fillForm() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div[2]/div/ul/li/span"))).click();

        // Fill form fields
        driver.findElement(By.id("firstName")).sendKeys("Demo");
        driver.findElement(By.id("lastName")).sendKeys("User");
        driver.findElement(By.id("userEmail")).sendKeys("Demouser@123");

        // Select gender
        List<WebElement> genderElements = driver.findElements(By.cssSelector(".custom-control.custom-radio.custom-control-inline"));
        for (WebElement gender : genderElements) {
            gender.click();
            Thread.sleep(500);
        }

        driver.findElement(By.id("userNumber")).sendKeys("9876543210");
        driver.findElement(By.id("dateOfBirthInput")).sendKeys("07 Jun 25");
        driver.findElement(By.id("subjects-label")).click();
        driver.findElement(By.id("subjectsInput")).sendKeys("Hello");

        // Select hobbies
        Thread.sleep(2000);
        List<WebElement> hobbies = driver.findElements(By.cssSelector(".custom-control.custom-checkbox.custom-control-inline"));
        for (WebElement hobby : hobbies) {
            hobby.click();
            Thread.sleep(500);
        }

        // Upload picture dynamically
        String filePath = System.getProperty("user.dir") + "/TestFile.png"; // place testfile.png in your project root
        driver.findElement(By.id("uploadPicture")).sendKeys(filePath);

        // Fill address
        driver.findElement(By.id("currentAddress")).sendKeys("Mota Varachha, Kamrej");

        // Scroll down so dropdowns are visible
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

        // Select State
        WebElement stateDropdown = driver.findElement(By.id("react-select-3-input"));
        stateDropdown.sendKeys("NCR"); // or "Uttar Pradesh", "Haryana", "Rajasthan"
        stateDropdown.sendKeys(Keys.ENTER);

        // Select City
        WebElement cityDropdown = driver.findElement(By.id("react-select-4-input"));
        cityDropdown.sendKeys("Delhi"); // or "Gurgaon", "Noida"
        cityDropdown.sendKeys(Keys.ENTER);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
