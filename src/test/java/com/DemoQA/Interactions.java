package com.DemoQA;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Interactions {

    private WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get("https://demoqa.com/interaction");
    }

    @Test
    public void testSortable() throws InterruptedException {
        // Click "Sortable" from sidebar
        WebElement sortableLink = driver.findElement(By.xpath("//span[text()='Sortable']"));
        sortableLink.click();

        // Wait for URL
        wait.until(ExpectedConditions.urlContains("sortable"));

        // ********** SORT LIST **********
        List<WebElement> listItems = driver.findElements(By.cssSelector("#demo-tabpane-list .list-group-item"));
        WebElement itemOne = listItems.get(0); // "One"
        WebElement itemSix = listItems.get(5); // "Six"

        // Drag "One" after "Six"
        actions.clickAndHold(itemOne).moveToElement(itemSix).release().build().perform();
        Thread.sleep(1500);

        // ********** SORT GRID **********
        WebElement gridTab = driver.findElement(By.id("demo-tab-grid"));
        gridTab.click();
        wait.until(ExpectedConditions.attributeContains(gridTab, "class", "active"));

        List<WebElement> gridItems = driver.findElements(By.cssSelector("#demo-tabpane-grid .list-group-item"));
        WebElement gridTwo = gridItems.get(1); // "Two"
        WebElement gridSix = gridItems.get(5); // "Six"

        // Drag "Two" after "Six"
        actions.clickAndHold(gridTwo).moveToElement(gridSix).release().build().perform();
        Thread.sleep(1500);
    }
    
    @Test(dependsOnMethods = "testSortable")
    public void testSelectable() throws InterruptedException {
        // Click "Selectable" from sidebar
        WebElement selectableLink = driver.findElement(By.xpath("//span[text()='Selectable']"));
        selectableLink.click();

        wait.until(ExpectedConditions.urlContains("selectable"));

        // ********** LIST TAB **********
        List<WebElement> listItems = driver.findElements(By.cssSelector("#demo-tabpane-list .list-group-item"));
        WebElement firstItem = listItems.get(0);  // Cras justo odio
        WebElement thirdItem = listItems.get(2);  // Morbi leo risus

        firstItem.click();
        thirdItem.click();

        Assert.assertTrue(firstItem.getAttribute("class").contains("active"), "First list item not selected!");
        Assert.assertTrue(thirdItem.getAttribute("class").contains("active"), "Third list item not selected!");

        Thread.sleep(1000);

        // ********** GRID TAB **********
        WebElement gridTab = driver.findElement(By.id("demo-tab-grid"));
        gridTab.click();
        wait.until(ExpectedConditions.attributeContains(gridTab, "class", "active"));

        List<WebElement> gridItems = driver.findElements(By.cssSelector("#demo-tabpane-grid .list-group-item"));
        WebElement gridOne = gridItems.get(0); // One
        WebElement gridNine = gridItems.get(8); // Nine

        gridOne.click();
        gridNine.click();

        Assert.assertTrue(gridOne.getAttribute("class").contains("active"), "Grid item One not selected!");
        Assert.assertTrue(gridNine.getAttribute("class").contains("active"), "Grid item Nine not selected!");

        Thread.sleep(1000);
    }
    
    @Test(dependsOnMethods = "testSelectable")
    public void testResizable() throws InterruptedException {
        WebElement resizableLink = driver.findElement(By.xpath("//span[text()='Resizable']"));
        resizableLink.click();

        wait.until(ExpectedConditions.urlContains("resizable"));

        // ********** BOX 1 (with constraints) **********
        WebElement resizeHandle1 = driver.findElement(By.cssSelector("#resizableBoxWithRestriction .react-resizable-handle"));
        actions.clickAndHold(resizeHandle1).moveByOffset(100, 50).release().build().perform();
        Thread.sleep(1500);

        // ********** BOX 2 (without constraints) **********
        WebElement resizeHandle2 = driver.findElement(By.cssSelector("#resizable .react-resizable-handle"));
        actions.clickAndHold(resizeHandle2).moveByOffset(150, 100).release().build().perform();
        Thread.sleep(1500);
    }
    
    @Test(dependsOnMethods = "testResizable")
    public void testDroppable() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Droppable']")).click();
        wait.until(ExpectedConditions.urlContains("droppable"));

        // Simple
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(drag, drop).perform();
        Assert.assertEquals(drop.getText(), "Dropped!");

        // Accept tab
        driver.findElement(By.id("droppableExample-tab-accept")).click();
        WebElement notAccept = driver.findElement(By.id("notAcceptable"));
        WebElement accept = driver.findElement(By.id("acceptable"));
        WebElement target = driver.findElement(By.cssSelector("#acceptDropContainer #droppable"));

        actions.dragAndDrop(notAccept, target).perform();
        Assert.assertEquals(target.getText(), "Drop here");
        actions.dragAndDrop(accept, target).perform();
        Assert.assertEquals(target.getText(), "Dropped!");
    }

    // --- TEST 5: Dragabble ---
//    @Test(dependsOnMethods = "testDroppable")
    @Test
    public void testDragabble() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Dragabble']")).click();
        wait.until(ExpectedConditions.urlContains("dragabble"));

        // Simple tab
        WebElement simpleBox = driver.findElement(By.id("dragBox"));
        actions.dragAndDropBy(simpleBox, 100, 50).perform();
        Thread.sleep(500);

        // Axis Restricted tab
        driver.findElement(By.id("draggableExample-tab-axisRestriction")).click();
        WebElement onlyX = driver.findElement(By.id("restrictedX"));
        WebElement onlyY = driver.findElement(By.id("restrictedY"));
        actions.dragAndDropBy(onlyX, 100, 0).perform();
        actions.dragAndDropBy(onlyY, 0, 100).perform();
        Thread.sleep(500);

        // Container Restricted tab
        driver.findElement(By.id("draggableExample-tab-containerRestriction")).click();
     // Box restricted draggable
        WebElement insideBox = driver.findElement(By.cssSelector("#containmentWrapper > div"));

        // Parent restricted draggable
        WebElement insideParent = driver.findElement(By.xpath("//*[@id=\"draggableExample-tabpane-containerRestriction\"]/div[2]"));

        actions.dragAndDropBy(insideBox, 50, 30).perform();
        actions.dragAndDropBy(insideParent, 50, 30).perform();
        Thread.sleep(500);

        // Cursor Style tab
        driver.findElement(By.id("draggableExample-tab-cursorStyle")).click();
        WebElement cursorCenter = driver.findElement(By.id("cursorCenter"));
        WebElement cursorTopLeft = driver.findElement(By.id("cursorTopLeft"));
        WebElement cursorBottom = driver.findElement(By.id("cursorBottom"));
        actions.dragAndDropBy(cursorCenter, 60, 40).perform();
        actions.dragAndDropBy(cursorTopLeft, 60, 40).perform();
        actions.dragAndDropBy(cursorBottom, 60, 40).perform();
        Thread.sleep(500);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
