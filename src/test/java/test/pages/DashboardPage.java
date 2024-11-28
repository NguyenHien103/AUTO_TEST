package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.common.ValidateHelpers;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ValidateHelpers validateHelpers;
    private String url="/dashboard/index";
    private By pim= By.xpath("//span[normalize-space()='PIM']");
    private By titlepim= By.xpath("//h6[normalize-space()='PIM']");
    public DashboardPage(WebDriver driver){
        this.driver= driver;
        validateHelpers = new ValidateHelpers(driver);
    }
    public ProjectPim opendPIM(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.urlContains("dashboard/index"));
        Assert.assertTrue(validateHelpers.verifyURL(url), "this is not Dashboard");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        validateHelpers.Clickelement(pim);
        Assert.assertTrue(validateHelpers.verifyelementText(titlepim,"PIM"),"Title wrong");
        return new ProjectPim(this.driver);
    }
}
