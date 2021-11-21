import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Zetreex {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://zetreex.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testChangingLanguage() {
        WebElement languageEnglish = driver.findElement(By.xpath("//*[@id=\"app\"]/header/div/div/div[1]/div[2]/p"));
        languageEnglish.click();
        WebElement wordInEnglish = driver.findElement(By.xpath("//*[@id=\"app\"]/header/div/div/nav/ul/li[1]/a/span"));
        System.out.println(wordInEnglish.getText());
        Assert.assertEquals(wordInEnglish.getText(), "About Us");
        WebElement languageRussian = driver.findElement(By.xpath("//*[@id=\"app\"]/header/div/div/div[1]/div[1]/p"));
        languageRussian.click();
        WebElement wordInRussian = driver.findElement(By.xpath("//*[@id=\"app\"]/header/div/div/nav/ul/li[1]/a/span"));
        System.out.println(wordInRussian.getText());
        Assert.assertEquals(wordInRussian.getText(), "О нас");
    }
}

