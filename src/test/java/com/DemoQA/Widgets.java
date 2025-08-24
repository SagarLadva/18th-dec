package com.DemoQA;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;

public class Widgets {

    private WebDriver driver;
    private WebDriverWait wait;
    Actions actions;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void navigateBase() {
        driver.get("https://demoqa.com/widgets");
    }

    @Test
    public void testWidgets() throws InterruptedException {
		/*
		 * driver.findElement(By.xpath(
		 * "/html/body/div[2]/div/div/div/div[1]/div/div/div[3]/span/div/div[1]")).click
		 * (); driver.findElement(By.xpath(
		 * "/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/span/div/div[1]")).click
		 * (); Thread.sleep(2000); driver.findElement(By.xpath(
		 * "/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[1]")).click();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * for (int i = 1; i <= 3; i++) { WebElement heading =
		 * driver.findElement(By.id("section" + i + "Heading")); heading.click();
		 * System.out.println("Clicked on: " + heading.getText());
		 * 
		 * // Wait until content of that section becomes visible WebElement content =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated( By.id("section" + i
		 * + "Content") ));
		 * 
		 * System.out.println("Content: " + content.getText()); }
		 * driver.findElement(By.xpath(
		 * "/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[2]")).click();
		 * // Open Auto Complete page
		 * driver.findElement(By.xpath("//span[text()='Auto Complete']")).click();
		 * Thread.sleep(1000);
		 * 
		 * // Enter multiple values into Auto Complete WebElement
		 * autoCompleteMultipleInput =
		 * driver.findElement(By.id("autoCompleteMultipleInput"));
		 * autoCompleteMultipleInput.sendKeys("Red");
		 * autoCompleteMultipleInput.sendKeys(Keys.ENTER); // ✅ This actually adds the
		 * chip autoCompleteMultipleInput.sendKeys("Blue");
		 * autoCompleteMultipleInput.sendKeys(Keys.ENTER);
		 * 
		 * // Now wait for labels to appear List<WebElement> selectedColors =
		 * wait.until( ExpectedConditions.presenceOfAllElementsLocatedBy(
		 * By.cssSelector(".auto-complete__multi-value__label") ) );
		 * 
		 * // Print selected colors for (WebElement color : selectedColors) {
		 * System.out.println("Selected: " + color.getText()); } WebElement
		 * autoCompleteSingleInput =
		 * driver.findElement(By.id("autoCompleteSingleInput"));
		 * autoCompleteSingleInput.sendKeys("Blue");
		 * autoCompleteSingleInput.sendKeys(Keys.ENTER);
		 * 
		 * WebElement singleSelected = wait.until(
		 * ExpectedConditions.visibilityOfElementLocated(
		 * By.cssSelector(".auto-complete__single-value")) );
		 * System.out.println("Single-selected color: " + singleSelected.getText());
		 */

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[3]")).click();
		Thread.sleep(2000);
		 //Date Piker************************
		 WebElement dateInput = driver.findElement(By.id("datePickerMonthYearInput"));
        dateInput.click();

     // Select year
        WebElement yearDropdown = driver.findElement(By.cssSelector(".react-datepicker__year-select"));
        new Select(yearDropdown).selectByVisibleText("1999");

        // Select month
        WebElement monthDropdown = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
        new Select(monthDropdown).selectByVisibleText("December");

        // Select a day (e.g., 15th)
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='15']")).click();

        String selectedDate = dateInput.getAttribute("value");
        System.out.println("Selected Date: " + selectedDate);
		 
		 //Date and Time
        WebElement dateTimeInput = driver.findElement(By.id("dateAndTimePickerInput"));
        dateTimeInput.click();

        WebElement yearDropdown2 = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__year-read-view"))
        );
        yearDropdown2.click();
        
        WebElement targetYear = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='react-datepicker__year-option' and text()='2021']")
                )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetYear);
        targetYear.click();
        
        // Open month/year selector
        WebElement monthDropdown2 = driver.findElement(By.cssSelector(".react-datepicker__month-read-view"));
        monthDropdown2.click();
        driver.findElement(By.xpath("//div[text()='December']")).click();

        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='20']")).click();
        driver.findElement(By.xpath("//li[text()='10:30']")).click();
        String selectedDateTime = dateTimeInput.getAttribute("value");
        System.out.println("Selected Date & Time: " + selectedDateTime);
       
		 
		 //Slider
		 driver.findElement(By.xpath( "/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[4]")).click();
		 Thread.sleep(1000);
		 
		 // Locate the slider element 
		 WebElement slider = driver.findElement(By.xpath("//input[@type='range']")); 
		 // Drag the slider by offset (horizontal)
		 actions.clickAndHold(slider).moveByOffset(50,0).release().perform(); 
		 // adjustoffset as needed
		 Thread.sleep(1000);
		 
		 //Progress bar
		 driver.findElement(By.xpath(
		 "/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[5]")).click();
		 Thread.sleep(2000);
		 
		 driver.findElement(By.id("startStopButton")).click(); Thread.sleep(5000);
		 
		 driver.findElement(By.id("startStopButton")).click();
		 
		 // Get and print progress value 
		 String progress = driver.findElement(By.cssSelector(".progress-bar.bg-info")).getText();
		 System.out.println("Progress: " + progress);
		 
		 
		 //Tabs
		 driver.findElement(By.xpath("//span[text()='Tabs']/ancestor::li")).click();
		 Thread.sleep(5000);
		 
		 //click on "use"
		 driver.findElement(By.id("demo-tab-use")).click();
		 WebElement useContent = driver.findElement(By.id("demo-tabpane-use")); String
		 useText = useContent.getText(); System.out.println("Use Tab Text: " +
		 useText);
		 
		 //Click on Origin
		 driver.findElement(By.id("demo-tab-origin")).click();
		 WebElement originContent = driver.findElement(By.id("demo-tabpane-origin"));
		 String originText = useContent.getText(); System.out.println("Use Tab Text: "
		 + originText);
		 
		 //Click on What
		 driver.findElement(By.id("demo-tab-what")).click();
		 WebElement WhatContent = driver.findElement(By.id("demo-tabpane-what"));
		 String WhatText = useContent.getText(); System.out.println("Use Tab Text: " +
		 originText);
		 
		 
		 
		 //Tool Tips
		 driver.findElement(By.xpath( "/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[7]")).click();
		 Thread.sleep(2000);
		 
		 actions.moveToElement(driver.findElement(By.id("toolTipButton"))).build().perform
		 (); Thread.sleep(2000);
		 
		 actions.moveToElement(driver.findElement(By.id("toolTipTextField"))).build().
		 perform(); Thread.sleep(2000);
		 
		 actions.moveToElement(driver.findElement(By.xpath(
		 "//*[@id=\"texToolTopContainer\"]/a[1]"))).build().perform();
		 Thread.sleep(2000);
		 
		 
		 //Menu
		 driver.findElement(By.xpath( "/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[8]")).click();
		 
		 actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"nav\"]/li[1]/a"))).
		 build().perform(); Thread.sleep(1000);
		 actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"nav\"]/li[2]/a"))).
		 build().perform(); Thread.sleep(1000);
		 actions.moveToElement(driver.findElement(By.xpath(
		 "//*[@id=\"nav\"]/li[2]/ul/li[1]/a"))).build().perform(); Thread.sleep(1000);
		 actions.moveToElement(driver.findElement(By.xpath(
		 "//*[@id=\"nav\"]/li[2]/ul/li[2]/a"))).build().perform(); Thread.sleep(1000);
		 actions.moveToElement(driver.findElement(By.xpath(
		 "//*[@id=\"nav\"]/li[2]/ul/li[3]/a"))).build().perform(); Thread.sleep(1000);
		 actions.moveToElement(driver.findElement(By.xpath(
		 "//*[@id=\"nav\"]/li[2]/ul/li[3]/ul/li[1]/a"))).build().perform();
		 Thread.sleep(1000); actions.moveToElement(driver.findElement(By.xpath(
		 "//*[@id=\"nav\"]/li[2]/ul/li[3]/ul/li[2]/a"))).build().perform();
		 Thread.sleep(1000);
		 actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"nav\"]/li[3]/a"))).
		 build().perform(); Thread.sleep(1000);
		 

		// Select Menu
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div[4]/div/ul/li[9]")).click();

		// 1. Select Value (React Select)
		WebElement selectValueInput = driver.findElement(By.id("react-select-2-input"));
		selectValueInput.sendKeys("Group 1, option 1");
		selectValueInput.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		// 2. Select One
		WebElement selectOneInput = driver.findElement(By.id("react-select-3-input"));
		selectOneInput.sendKeys("Dr.");
		selectOneInput.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		Thread.sleep(1000);

		// 4. Multi Select Dropdown (React)
		WebElement multiSelectInput = driver.findElement(By.id("react-select-4-input"));
		multiSelectInput.sendKeys("Green");
		multiSelectInput.sendKeys(Keys.ENTER);
		multiSelectInput.sendKeys("Blue");
		multiSelectInput.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		// 5. Standard Multi Select (HTML <select multiple>)
		Select standardMultiSelect = new Select(driver.findElement(By.id("cars")));
		standardMultiSelect.selectByVisibleText("Volvo");
		standardMultiSelect.selectByVisibleText("Opel");
		Thread.sleep(1000);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}

