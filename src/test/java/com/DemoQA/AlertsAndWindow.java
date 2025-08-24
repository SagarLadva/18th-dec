
package com.DemoQA;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;
	import io.github.bonigarcia.wdm.WebDriverManager;

	import java.time.Duration;

	public class AlertsAndWindow {
	    private WebDriver driver;
	    private WebDriverWait wait;

	    @BeforeMethod
	    public void setupDriver() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        new Actions(driver);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    @Test
	    public void testAlertsAndWindows() {
	        driver.get("https://demoqa.com/alertsWindows");

	        // Navigate to Browser Windows
	        driver.findElement(By.xpath("//span[text()='Browser Windows']")).click();

	        // Handle Tab button
	        String parent = driver.getWindowHandle();
	        driver.findElement(By.id("tabButton")).click();
	        for (String win : driver.getWindowHandles()) {
	            if (!win.equals(parent)) {
	                driver.switchTo().window(win).close();
	                driver.switchTo().window(parent);
	            }
	        }

	        // Handle Window button
	        driver.findElement(By.id("windowButton")).click();
	        for (String win : driver.getWindowHandles()) {
	            if (!win.equals(parent)) {
	                driver.switchTo().window(win).close();
	                driver.switchTo().window(parent);
	            }
	        }

	        // Handle Message Window
	        driver.findElement(By.id("messageWindowButton")).click();
	        for (String win : driver.getWindowHandles()) {
	            if (!win.equals(parent)) {
	                driver.switchTo().window(win).close();
	                driver.switchTo().window(parent);
	            }
	        }

	        // Go to Alerts
	        driver.findElement(By.xpath("//span[text()='Alerts']")).click();

	        // Simple alert
	        driver.findElement(By.id("alertButton")).click();
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();

	        // Timed alert
	        driver.findElement(By.id("timerAlertButton")).click();
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();

	        // Confirm alert
	        driver.findElement(By.id("confirmButton")).click();
	        Alert confirm = wait.until(ExpectedConditions.alertIsPresent());
	        confirm.accept();
	        Assert.assertTrue(driver.findElement(By.id("confirmResult")).getText().contains("Ok"));

	        // Prompt alert
	        driver.findElement(By.id("promtButton")).click();
	        Alert prompt = wait.until(ExpectedConditions.alertIsPresent());
	        prompt.sendKeys("Vishal");
	        prompt.accept();
	        Assert.assertTrue(driver.findElement(By.id("promptResult")).getText().contains("Vishal"));
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}



