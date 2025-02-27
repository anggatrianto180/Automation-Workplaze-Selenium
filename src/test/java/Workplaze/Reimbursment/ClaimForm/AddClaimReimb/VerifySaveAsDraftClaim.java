package Workplaze.Reimbursment.ClaimForm.AddClaimReimb;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class VerifySaveAsDraftClaim {
    WebDriver driver;
    WebDriver wait;

    @BeforeTest
    public void setup() throws InterruptedException {
        // Tentukan lokasi chromedriver
        System.setProperty("webdriver.chrome.driver", "H:\\Dataon\\Automation\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
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

        // Klik tombol Edit
        WebElement buttonSingleClaim = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//form[@id='formClaimToSubmit']//li[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//button[1]//span[1]//*[name()='svg']")));
        buttonSingleClaim.click();

    }

    @Test (priority = 1)
    public void verifyDataClaim() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement claimType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ClaimFormEdit_EXPENSENAME']")));
        String actualText = claimType.getAttribute("value");
        Assert.assertEquals("Medical Claim", actualText);

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ReimbFor = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ClaimFormEdit_REQUESTFOR_NAME']")));
        String actualText2 = ReimbFor.getAttribute("value");
        Assert.assertEquals("Employee", actualText2);

        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ReceiptDate = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ClaimFormEdit_REIM_RECEIPTDATE']")));
        String actualText3 = ReceiptDate.getAttribute("value");
        Assert.assertEquals("27 Feb 2025", actualText3);

    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
