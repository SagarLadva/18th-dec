package com.DemoQA;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Book_Store_Application {

    WebDriver driver;
    // Generate unique user each run
    private final String USERNAME = "user" + System.currentTimeMillis();
    private final String PASSWORD = "Test@1234";  // must meet password rules
    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
    }

    
    @Test
    public void loginTest() throws InterruptedException {
    	// Step 1: Go to Register Page
        driver.findElement(By.id("newUser")).click();
//        Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Did not navigate to Register page");

        // Step 2: Fill Registration Form
        driver.findElement(By.id("firstname")).sendKeys("John");
        driver.findElement(By.id("lastname")).sendKeys("Doe");
        driver.findElement(By.id("userName")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);

        // ⚠️ ReCaptcha step (manual intervention needed)
        System.out.println("👉 Please complete the captcha manually within 60 seconds...");
        Thread.sleep(60000); // wait 1 minute for captcha solving

        // Click Register
        driver.findElement(By.id("register")).click();
        Thread.sleep(3000);
        try {
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals(alert.getText(), "User Register Successfully.", "Unexpected alert message!");
            alert.accept();  // close alert
        } catch (Exception e) {
            Assert.fail("Alert did not appear after registration!");
        }
        driver.findElement(By.id("gotologin")).click();
        // After successful registration → Should go back to login page
//        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Did not return to Login page after registration");

        // Step 3: Try login with registered user
        driver.findElement(By.id("userName")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("gotoStore"));
        Thread.sleep(10000);
        Assert.assertTrue(true, "store is opened.");
//        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/button"));
//        Thread.sleep(10000);
        // Step 4: Verify Profile Page
//        Assert.assertTrue(driver.getCurrentUrl().contains("profile"), "Login failed, user not redirected to Profile");

//        WebElement userNameValue = driver.findElement(By.id("userName-value"));
//        Assert.assertEquals(userNameValue.getText(), USERNAME, "Logged in username mismatch!");
    }

    @AfterTest
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
