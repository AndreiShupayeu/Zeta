import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;
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

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testChangingLanguage() {
        WebElement languageEnglish = driver.findElement(By.xpath("//div[@id='app']/header//div[1]/div[2]/p"));
        languageEnglish.click();
        WebElement wordInEnglish = driver.findElement(By.xpath("//span[text()='About Us']"));
        Assert.assertEquals(wordInEnglish.getText(), "About Us");
        WebElement languageRussian = driver.findElement(By.xpath("//*[@id=\"app\"]/header/div/div/div[1]/div[1]/p"));
        languageRussian.click();
        WebElement wordInRussian = driver.findElement(By.xpath("//span[text()='О нас']"));
        System.out.println(wordInRussian.getText());
        Assert.assertEquals(wordInRussian.getText(), "О нас");
    }

    @Test
    public void testVacancies() {
        WebElement vacancies = driver.findElement(By.xpath("//span[text()='Вакансии']"));
        vacancies.click();
        WebElement name = driver.findElement(By.id("vacancies__name1"));
        name.sendKeys("Name");
        WebElement position = driver.findElement(By.className("vacancies__select1"));
        position.click();
        WebElement oneOfPosition = driver.findElement(By.xpath("//div[contains(@class,'1 active')]/p[text()='Java Developer']"));
        oneOfPosition.click();
        WebElement fonOrLinkedin = driver.findElement(By.xpath("//input[@id=\"vacancies__linked1\"]"));
        fonOrLinkedin.sendKeys("12345");
        WebElement approval = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/form/div[4]/label[1]"));
        approval.click();
        WebElement toSend = driver.findElement(By.xpath("//div[@id=\"app\"]/div[2]/div/div[2]/form/button"));
        toSend.click();
        WebElement ok = driver.findElement(By.xpath("//div[@class='modal']//button"));
        Assert.assertEquals(ok.getText(), "Хорошо");
    }

    @Test
    public void testPrivacyPolicy() {
        WebElement vacancies = driver.findElement(By.xpath("//span[text()=\"Вакансии\"]"));
        vacancies.click();
        WebElement termsOfPersonalDataProcessing = driver.findElement(By.xpath("//span[text()=\"Условиями обработки персональных данных\"]"));
        termsOfPersonalDataProcessing.click();
        WebElement privacyPolicy = driver.findElement(By.xpath("//div[@class='modal']//div[@class='privacy__body']/h3[@class='privacy__body_title']"));
        Assert.assertEquals(privacyPolicy.getText(), "Политика по сбору и обработке персональных данных");
    }

    @Test
    public void testInternships() {
        WebElement internships = driver.findElement(By.xpath("//span[text()=\"Стажировки\"]"));
        internships.click();
        WebElement name = driver.findElement(By.xpath("//div[@class=\"vacancies__name\"]/input[@type=\"text\"]"));
        name.sendKeys("Vasily");
        WebElement position = driver.findElement(By.xpath("//p[@class='vacancies__select1_placeholder']"));
        position.click();
        WebElement firstPosition = driver.findElement(By.xpath("//div[@class='vacancies__body1 active']/p[1]"));
        firstPosition.click();
        WebElement telOrLinkedin = driver.findElement(By.id("vacancies__linked1"));
        telOrLinkedin.sendKeys("12345");
        WebElement approval = driver.findElement(By.xpath("//label[@class='check_hover']"));
        approval.click();
        WebElement send = driver.findElement(By.className("add__vacancies"));
        send.click();
        WebElement ok = driver.findElement(By.xpath("//div[@class='modal']//button"));
        Assert.assertEquals(ok.getText(), "Хорошо");
    }

    @Test
    public void testContactUs() {
        WebElement contactUs = driver.findElement(By.xpath("//span[text()='Связаться с нами']"));
        contactUs.click();
        WebElement feedback = driver.findElement(By.xpath("//div[@class='container']/h2"));
        Assert.assertEquals(feedback.getText(), "Обратная связь");
        WebElement email = driver.findElement(By.xpath("//div[@class='about__feedback_info']/a[1]"));
        Assert.assertEquals(email.getText(), "info@zetasolutions.io");
        WebElement companyLinkedin = driver.findElement(By.xpath("//div[@class='about__feedback_info']/a[2]"));
        Set<String> oldWindowsSet = driver.getWindowHandles();
        companyLinkedin.click();
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newWindowHandle = newWindowsSet.iterator().next();
        driver.switchTo().window(newWindowHandle);
        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"));
    }

    @Test
    public void testLinkLinkedinHR() {
        WebElement linkedinHR = driver.findElement(By.xpath("//div[@class='link_hr']/a/p"));
        Set<String> oldWindowsSet = driver.getWindowHandles();
        linkedinHR.click();
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newWindowHandle = newWindowsSet.iterator().next();
        driver.switchTo().window(newWindowHandle);
        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"));
    }

    @Test
    public void testLogo() {
        WebElement logo = driver.findElement(By.xpath("//img[@src='/img/Logo.2cb35532.svg']"));
        logo.click();
        WebElement vacancies = driver.findElement(By.xpath("//span[text()=\"Вакансии\"]"));
        vacancies.click();
        logo.click();
        WebElement aboutUs = driver.findElement(By.xpath("//h2[@class='text__about_us']"));
        Assert.assertEquals(aboutUs.getText(), "Разрабатываем сервисы в области социальных сетей");
    }
}
