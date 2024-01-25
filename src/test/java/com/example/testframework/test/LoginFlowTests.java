package com.example.testframework.test;

import com.example.testframework.steps.CommonSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.example.testframework.steps.LoginPageSteps;

public class LoginFlowTests extends TestBase {
    private CommonSteps commonSteps;
    private LoginPageSteps loginPageSteps;

    @BeforeMethod(dependsOnMethods = "testBaseBeforeMethod")
    public void beforeMethod() {
        commonSteps = new CommonSteps();
        loginPageSteps = new LoginPageSteps();
    }

    @Test
    public void testValidateLoginPageElements() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.validateLogoIsVisible();
        loginPageSteps.validateUsernameOrEmailAddressFieldAppearance();
        loginPageSteps.validatePasswordFieldAppearance();
        loginPageSteps.validateForgotYourPasswordText();
        loginPageSteps.validateSignInButtonText();
        loginPageSteps.validateSignInWithAPasskeyText();
        loginPageSteps.validateCreateAnAccountText("New to GitHub? Create an account");
        assertAll();
    }

    @Test
    public void testLoginWithValidEmailAndValidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("validEmail@gmail.com");
        loginPageSteps.enterPassword("validPassword");
        loginPageSteps.clickLoginButton();
        commonSteps.validateSuccessfullyLoggedIn();
        commonSteps.signOut();
    }

    @Test
    public void testLoginWithValidEmailAndInvalidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("validEmail@gmail.com");
        loginPageSteps.enterPassword("invalidPassword");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithValidEmailAndWithoutPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("validEmail@gmail.com");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailAndValidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("invalidEmail@gmail.com");
        loginPageSteps.enterPassword("validPassword");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailAndInvalidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("invalidEmail@gmail.com");
        loginPageSteps.enterPassword("invalidPassword");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailAndWithoutPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("invalidEmail@gmail.com");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithoutEmailAndValidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterPassword("validPassword");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithoutEmailAndInvalidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterPassword("invalidPassword");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithoutEmailAndWithoutPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailFormatAndValidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("invalidFormat");
        loginPageSteps.enterPassword("validPassword");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailFormatAndInvalidPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("invalidFormat");
        loginPageSteps.enterPassword("invalidPassword");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }

    @Test
    public void testLoginWithInvalidEmailFormatAndWithoutPassword() {
        commonSteps.navigateToPage("home");
        commonSteps.navigateToPage("Sign in");
        loginPageSteps.enterEmail("invalidFormat");
        loginPageSteps.clickLoginButton();
        loginPageSteps.validateErrorMessage("Incorrect username or password.");
        assertAll();
    }
}
