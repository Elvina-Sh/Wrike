import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class RegistrationPageTest {

    public static RegistrationPage page;
    public static User user;
    public static WebDriver driver;

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.wrike.com");
        page = new RegistrationPage(driver);
        user = new User();
        page.registerUser(user);
    }

    @Test
    public void registerUser() throws Throwable{
        Thread.sleep(5000);
        String actual = driver.getCurrentUrl();
        assertThat(actual,is("https://www.wrike.com/resend/"));
    }

    @Test
    public void randomClick() throws Throwable{
        page.randomClicks();
        Thread.sleep(3000);
        Boolean visible = page.bSubmitSuccess.isDisplayed();
        assertThat(visible,is(true));
    }

    @Test
    public void twitterButtonUrl() throws Throwable{
        Thread.sleep(2000);
        WebElement bTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]"));
        bTwitter.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String actual = driver.getCurrentUrl();
        assertThat(actual, is("https://twitter.com/wrike"));
    }
}