import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {
    @Test
    @DisplayName("Move to Terms page")
    @Description("Redirect to 'Terms' page via button link")
    public void MoveToTerms(){
        LandingPage page = new LandingPage(driver);
        String URL = "https://www.softr.io/terms";
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);
        String first_handle = driver.getWindowHandle();
        registration.clickOnTermsButton();

        Set<String> allHandles = driver.getWindowHandles();
        for(String winHandle: allHandles)
        {
            if (!first_handle.equalsIgnoreCase(winHandle))
            {
                driver.switchTo().window(winHandle);
            }
        }
//        registration.loadPage();
        assertEquals(URL, registration.getURL());
    }

    @Test
    @DisplayName("Move to Policy page")
    @Description("Redirect to 'Privacy Policy' page via button link")
    public void MoveToPolicy(){
        LandingPage page = new LandingPage(driver);
        String URL = "https://www.softr.io/policy";
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);
        String first_handle = driver.getWindowHandle();
        registration.clickOnPolicyButton();

        Set<String> allHandles = driver.getWindowHandles();
        for(String winHandle: allHandles)
        {
            if (!first_handle.equalsIgnoreCase(winHandle))
            {
                driver.switchTo().window(winHandle);
            }
        }
        assertEquals(URL, registration.getURL());
    }

    @Test
    @DisplayName("Check showing of password")
    @Description("Checking show of password by clicking 'eye'")
    public void CheckEyeTest(){
        LandingPage page = new LandingPage(driver);
        page.clickOnSignUpButton();
        page.loadPage();
        RegistrationPage registration = new RegistrationPage(driver);

        assertEquals("password", registration.getPasswordType());
        registration.clickOnPasswordEye();
        assertEquals("text", registration.getPasswordType());
    }

    @Test
    @DisplayName("Check valid Teacher registration")
    @Description("Checking registration of Teacher role account with valid data")
    public void ValidTeacherSignUp(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        String email = "bmw3@gmail.com";
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();
        registration.enterTextToElement("Popovics Andrey", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // seconds
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));

        assertEquals(LandingURL, registration.getURL());
        Requests requests = new Requests();
        requests.deleteRequest("/users/"+email, 200);
    }

    @Test
    @DisplayName("Check valid Student registration")
    @Description("Checking registration of Student role account with valid data")
    public void ValidStudentSignUp(){
        LandingPage page = new LandingPage(driver);
        String LandingURL = page.getURL();
        String email = "bmw5@gmail.com";
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("student")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();
        registration.enterTextToElement("Popovics Adam", registration.getFullNameField());
        registration.enterTextToElement(email, registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // seconds
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.MuiButtonBase-root:first-of-type")));

        assertEquals(LandingURL, registration.getURL());
        Requests requests = new Requests();
        requests.deleteRequest("/users/"+email, 200);
    }

    @Test
    @DisplayName("Check empty role registration")
    @Description("Checking registration account with empty role")
    public void EmptyRoleSignUp(){
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement("bmw6@gmail.com", registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }

    @Test
    @DisplayName("Check empty fullname Teacher registration")
    @Description("Checking registration of Teacher role account with empty fullname")
    public void EmptyFullnameTeacherSignUp(){
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("bmw6@gmail.com", registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }

    @Test
    @DisplayName("Check empty Agreement checkbox registration")
    @Description("Checking registration of account with empty Agreement checkbox")
    public void EmptyAgreementCheckboxTeacherSignUp(){
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement("bmw6@gmail.com", registration.getEmailField());
        registration.enterTextToElement("abc456", registration.getPasswordField());
        registration.clickOnSignUpButton();

        assertEquals("Please make sure there are no empty required fields.", registration.getRequiredError().getText());
    }

    @Test
    @DisplayName("Check previously used Email Teacher registration")
    @Description("Checking registration of Teacher account with previously used Email")
    public void PreviouslyUsedEmailTeacherSignUp(){
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement("bmw6@gmail.com", registration.getEmailField());
        registration.enterTextToElement("123456", registration.getPasswordField());
        registration.clickCheckBoxAgree();
        registration.clickOnSignUpButton();

        assertEquals("User by given email already exists.", registration.getSignUpError().getText());
    }

    @Test
    @DisplayName("Check valid Email invalid Password registration")
    @Description("Checking registration of account with valid Email and invalid Password checkbox")
    public void ValidEmailInvalidPasswordSignUp(){
        LandingPage page = new LandingPage(driver);
        page.loadPage();
        page.clickOnSignUpButton();
        RegistrationPage registration = new RegistrationPage(driver);

        registration.clickOnRoleDropdown();
        Optional<WebElement> TeacherOptional = registration.getDropdownOptions().stream()
                .filter(Option -> Option.getText().equalsIgnoreCase("teacher")).findFirst();
        assertTrue(TeacherOptional.isPresent());
        TeacherOptional.get().click();

        registration.enterTextToElement("Popovics Anna", registration.getFullNameField());
        registration.enterTextToElement("bmw6@gmail.com", registration.getEmailField());
        registration.enterTextToElement("1", registration.getPasswordField());
        registration.clickOnSignUpButton();

        assertEquals("Password must contain at least 6 characters", registration.getValidationError().getText());
    }
}
