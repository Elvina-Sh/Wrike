import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

public class RegistrationPage {

    public WebDriver driver;

    //@FindBy(xpath="//div[contains(@class,'header__mobile-content')]//button")
    @FindBy(xpath="/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button")
    private WebElement bGetStarted;

    @FindBy(xpath="//*[@id=\"modal-pro\"]/form/label[2]/button")
    private WebElement bCreate;

    @FindBy(xpath="/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/button")
    private WebElement bSubmit;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/div/h3")
    public WebElement bSubmitSuccess;

    @FindBy(xpath = "//*[@id=\"modal-pro\"]/form/label[1]/input")
    private WebElement email;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[3]/button")
    private WebElement bOther;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[3]/button/span/input")
    private WebElement bOther1;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]")
    private WebElement option1;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[2]")
    private WebElement option2;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]")
    private WebElement option3;


    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void registerUser(User user){
        bGetStarted.click();
        email.sendKeys(user.email);
        bCreate.click();
    }

    private void randomClick(WebElement option){
        List<WebElement> options = option.findElements(By.className("switch"));
        Random random1 = new Random();
        int index = random1.nextInt(options.size());
        if ("Other".equals(options.get(index).getText())){
            options.get(index).click();
            options.set(index, bOther1);
            options.get(index).sendKeys("Other");
        }
        else
            options.get(index).click();
    }

    public void randomClicks(){
        randomClick(option1);
        randomClick(option2);
        randomClick(option3);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(bSubmit));
        element.click();
    }
}
