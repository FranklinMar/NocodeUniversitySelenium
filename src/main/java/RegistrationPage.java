import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegistrationPage extends BasePage {

    @Getter
//    @FindBy(partialLinkText = "Terms")
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'terms')]")
    private WebElement TermsButton;

    @Getter
//    @FindBy(partialLinkText = "Privacy Policy")
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'privacy policy')]")
    private WebElement PolicyButton;

    @Getter
    @FindBy(name = "full_name")
    private WebElement FullNameField;

    @Getter
    @FindBy(name = "static_email")
    private WebElement EmailField;

    @Getter
    @FindBy(name = "password")
    private WebElement PasswordField;

    @Getter
    @FindBy(css = ".checkmark.position-relative.sw-checkbox")
    private WebElement CheckBoxAgree;

    @Getter
    @FindBy(className = "show-password")
    private WebElement PasswordEye;

    @Getter
    @FindBy(xpath = "//button[@title='Select your role']")
    private WebElement RoleDropdown;

    @FindBy(css = "a[role=\"option\"]")
    private List<WebElement> DropdownOptions;

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]")
    private WebElement SignInButton;

    @Getter
    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign up')]")
    private WebElement SignUpButton;

    @Getter
    @FindBy(className = "required-errors")
    private WebElement RequiredError;

    @Getter
    @FindBy(className = "signup-error")
    private WebElement SignUpError;

    @Getter
    @FindBy(className = "validation-message")
    private WebElement ValidationError;

    public RegistrationPage(WebDriver driver) {
        super(driver);
//        getDropdownOptions();
//        driver.get(BASE_URL);
        //waitPageLoading();
    }

    public void clickOnTermsButton() {
        clickOnTheElement(TermsButton);
    }

    public void clickOnPolicyButton() {
        clickOnTheElement(PolicyButton);
    }

    public void clickOnPasswordEye() {
        clickOnTheElement(PasswordEye);
    }

    public void clickOnRoleDropdown() {
        clickOnTheElement(RoleDropdown);
    }

    public void clickCheckBoxAgree() {
        clickOnTheElement(CheckBoxAgree);
    }

    public void clickOnSignUpButton(){
        clickOnTheElement(SignUpButton);
    }

    public void clickOnSignInButton(){
        clickOnTheElement(SignInButton);
    }

    public List<WebElement> getDropdownOptions(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a[role=\"option\"]")));
        DropdownOptions = driver.findElements(By.cssSelector("a[role=\"option\"]"));
        return DropdownOptions;
    }

    public String getPasswordType() {
        return PasswordField.getAttribute("type");
    }
}
