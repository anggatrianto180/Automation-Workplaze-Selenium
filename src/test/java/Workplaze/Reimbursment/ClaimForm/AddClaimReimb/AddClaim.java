package Workplaze.Reimbursment.ClaimForm.AddClaimReimb;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddClaim {
    WebDriver driver;
    WebDriver wait;

    @BeforeTest
    public void setup() throws InterruptedException {
        // Tentukan lokasi chromedriver
        System.setProperty("webdriver.chrome.driver", "H:\\Dataon\\Automation\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Mengatur opsi Chrome untuk mode headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");               // Menjalankan Chrome tanpa UI
        options.addArguments("--disable-gpu");              // Menonaktifkan GPU (opsional, terutama untuk Windows)
        options.addArguments("--no-sandbox");               // Opsi untuk lingkungan Linux/container
        options.addArguments("--disable-dev-shm-usage");     // Mengatasi masalah resource
        // Go to Login Page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sfworkplaze.dataon.com"); // Ganti dengan URL yang sesuai

        //Login
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='userName']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//span[normalize-space()='Login']"));
        //Input Username dan password
        passwordField.sendKeys("password123");
        Thread.sleep(2000);
        usernameField.sendKeys("gordon");
        Thread.sleep(2000);
        loginButton.click();
        Thread.sleep(10000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlMatches("https://sfworkplaze.dataon.com/standard/home"));

    }

    @Test (priority = 0)
    public void claimFormMenu() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Klik dropdown reimbursement
        WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='menuitem']//span[@aria-label='reimbursement']//*[name()='svg']")));
        dropDown.click();

        // Klik menu Claim Form
        WebElement dropDownMenuClaim = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Claim Form']")));
        dropDownMenuClaim.click();

        // Klik tombol Add Claim
        WebElement buttonAddClaim = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'ant-btn-primary') and contains(@class, 'ant-dropdown-trigger')]")));
        buttonAddClaim.click();

        // Klik tombol Add Single Claim
        WebElement buttonSingleClaim = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Add Single Claim']")));
        buttonSingleClaim.click();

    }

    @Test (priority = 1)
    public void inputFormClaimType() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement claimType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ClaimFormAdd_EXPENSECODE']")));
        claimType.click();
        claimType.sendKeys("Medical Claim");
        claimType.sendKeys(Keys.ENTER);

        WebElement claimDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ClaimFormAdd_ReimburseForDate_1']")));
        claimDate.sendKeys("27 Feb 2027");

        WebElement claimDateTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ClaimFormAdd_ReimburseForDate_2']")));
        claimDateTo.sendKeys("27 Feb 2027");

        WebElement Amount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@katalon-id='reimcost']//input[@value='0.00']")));
        Amount.sendKeys(Keys.ENTER);
        Amount.sendKeys(Keys.LEFT);
        Amount.sendKeys(Keys.LEFT);
        Amount.sendKeys(Keys.LEFT);
        Amount.sendKeys(Keys.LEFT);
        Amount.sendKeys("10000");

        // Input Remark
        WebElement remark = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@id='ClaimFormAdd_REMARK']")));
        remark.clear();
        remark.sendKeys("Test QA");
        Thread.sleep(3000);

        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Send To Approver']")));
        Submit.click();
        Thread.sleep(5000);

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://sfworkplaze.dataon.com/standard/hrm.reimbursement.claim";

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
