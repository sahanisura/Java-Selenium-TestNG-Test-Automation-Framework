package com.example.testframework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NavigationMenuPage extends PageBase {
    @FindBy(className = "header-menu-wrapper")
    private WebElement navigationMenu;
    @FindBy(xpath = "//button[contains(text(),'Solutions')]")
    private WebElement solutionsMenuItem;

    public NavigationMenuPage(WebDriver driver) {
        super(driver);
    }

    public void moveMousePointerToSolutionsMenuItem() {
        Actions actions = new Actions(driver);
        actions.moveToElement(solutionsMenuItem).perform();
    }

    public boolean isNavigationMenuVisibleInViewport() {
        String script =
                "var elem = arguments[0],                           " +
                        "  box = elem.getBoundingClientRect(),      " +
                        "  cx = box.left + box.width / 2,           " +
                        "  cy = box.top + box.height / 2,           " +
                        "  efp = document.elementFromPoint(cx, cy); " +
                        "for (; elem; elem = elem.parentElement) {  " +
                        "  if (elem === efp) return true;           " +
                        "}                                          " +
                        "return false;                              ";

        return (boolean) executeJavaScript(script, navigationMenu);
    }
}
