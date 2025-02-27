package Workplaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        // Tentukan lokasi chromedriver
        System.setProperty("webdriver.chrome.driver", "H:\\Dataon\\Automation\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        // Go to Login Page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sfworkplaze.dataon.com"); // Ganti dengan URL yang sesuai
    }

    @Test (priority = 0)
    private void LoginPage() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='userName']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//span[normalize-space()='Login']"));
        //Input Username dan password
        passwordField.sendKeys("password123");
        Thread.sleep(3000);
        usernameField.sendKeys("gordon");
        Thread.sleep(3000);
        loginButton.click();
        Thread.sleep(20000);
    }

    @Test (priority = 1)
    public void verifylogin() throws InterruptedException {
        //Verify Simple Type 1
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='bio-desc-name']")).getText(), "Gordon Enns");
        //Verify dengan alert
        //WebElement dataElement = driver.findElement(By.xpath("//div[@class='bio-desc-name']"));
        // Ambil teks dari elemen tersebut
        //String actualData = dataElement.getText();
        //String expectedData = "Gordon Enns";
        //Assert.assertEquals(actualData, expectedData, "Verifikasi data gagal!");
        Thread.sleep(10000);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
