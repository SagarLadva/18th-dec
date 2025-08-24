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

public class Elements {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/elements");
    }

    @Test
    public void interactWithElements() throws InterruptedException {
        // Text Box
        driver.findElement(By.id("item-0")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName"))).sendKeys("jagruti");
        driver.findElement(By.id("userEmail")).sendKeys("jagu@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("surat");
        driver.findElement(By.id("permanentAddress")).sendKeys("surat");
        driver.findElement(By.id("submit")).click();

        // Checkbox
        driver.findElement(By.id("item-1")).click();
        driver.findElement(By.cssSelector(".rct-checkbox")).click();

        // Radio Button
        driver.findElement(By.id("item-2")).click();
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".custom-control.custom-radio.custom-control-inline"));
        for (WebElement rb : radioButtons) {
            rb.click();
            Thread.sleep(1000);
        }

        // Web Table
        driver.findElement(By.id("item-3")).click();
        driver.findElement(By.id("addNewRecordButton")).click();
        driver.findElement(By.id("firstName")).sendKeys("Demo");
        driver.findElement(By.id("lastName")).sendKeys("User");
        driver.findElement(By.id("userEmail")).sendKeys("demo@gmail.com");
        driver.findElement(By.id("age")).sendKeys("20");
        driver.findElement(By.id("salary")).sendKeys("500000");
        driver.findElement(By.id("department")).sendKeys("IT");
        driver.findElement(By.id("submit")).click();

        // Buttons
        driver.findElement(By.id("item-4")).click();
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(By.id("doubleClickBtn"))).perform();
        actions.contextClick(driver.findElement(By.id("rightClickBtn"))).perform();
        driver.findElement(By.xpath("//button[text()='Click Me']")).click();

        // Links
        driver.findElement(By.id("item-5")).click();
        List<WebElement> links = driver.findElement(By.id("linkWrapper")).findElements(By.tagName("a"));
        for (WebElement link : links) {
            link.click();
            Thread.sleep(1000);
            // close new tab if opened
            if (driver.getWindowHandles().size() > 1) {
                String mainWindow = driver.getWindowHandle();
                for (String win : driver.getWindowHandles()) {
                    if (!win.equals(mainWindow)) driver.switchTo().window(win).close();
                }
                driver.switchTo().window(mainWindow);
            }
        }

        // Broken Links
        driver.findElement(By.id("item-6")).click();
        driver.findElement(By.linkText("Click Here for Valid Link")).click();
        Thread.sleep(1000);
        driver.navigate().back();
        driver.findElement(By.linkText("Click Here for Broken Link")).click();
        driver.navigate().back();

        // Upload & Download
        driver.findElement(By.id("item-7")).click();
        driver.findElement(By.id("downloadButton")).click();
        driver.findElement(By.id("uploadFile")).sendKeys("D:\\test.txt");

        // Dynamic Properties
        driver.findElement(By.id("item-8")).click();
        Thread.sleep(3000);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
