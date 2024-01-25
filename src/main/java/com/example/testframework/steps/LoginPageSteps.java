package com.example.testframework.steps;

import com.example.testframework.pages.LoginPage;

public class LoginPageSteps extends StepBase {
    private LoginPage loginPage;

    @Override
    protected void initPages() {
        loginPage = new LoginPage(driver);
    }

    public void validateLogoIsVisible() {
        softAssert.assertEquals(loginPage.isLogoVisible(), true,
                "Logo is not visible.");
    }

    public void validateUsernameOrEmailAddressFieldAppearance() {
        softAssert.assertEquals(loginPage.isUsernameOrEmailAddressLabelVisible(), true,
                "Username or email address label is not visible.");
        softAssert.assertEquals(loginPage.getUsernameOrEmailAddressLabelText(), "Username or email address",
                "Username or email address label is incorrect.");
    }

    public void validatePasswordFieldAppearance() {
        softAssert.assertEquals(loginPage.isPasswordLabelVisible(), true,
                "Password label is not visible.");
        softAssert.assertEquals(loginPage.getPasswordLabelText(), "Password",
                "Password Label is incorrect.");
    }

    public void validateSignInButtonText() {
        softAssert.assertEquals(loginPage.getSignInButtonText(), "Sign in",
                "Sign in button text is incorrect.");
    }

    public void validateSignInWithAPasskeyText() {
        softAssert.assertEquals(loginPage.getSignInWithAPasskeyText(), "Sign in with a passkey",
                "Sign in with a passkey text is incorrect.");
    }

    public void validateForgotYourPasswordText() {
        softAssert.assertEquals(loginPage.getForgetPasswordText(), "Forgot password?",
                "Password reset link text is incorrect.");
    }

    public void validateCreateAnAccountText(String expectedText) {
        softAssert.assertEquals(loginPage.getCreateAnAccountLblText(), expectedText,
                "Create an account text is incorrect.");
    }

    public void enterEmail(String email) {
        loginPage.enterEmail(email);
    }

    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    public void validateErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getErrorMessage();
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}
