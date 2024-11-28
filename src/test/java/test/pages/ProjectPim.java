package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.common.ValidateHelpers;

import java.time.Duration;

public class ProjectPim {
    private WebDriver driver;
    private ValidateHelpers validateHelpers;
    private By addpim= By.xpath("//button[normalize-space()='Add']");
    private WebDriverWait wait;


    public ProjectPim(WebDriver driver){
        this.driver= driver;
        validateHelpers = new ValidateHelpers(driver);


    }
    public void addPim(){
        wait= new WebDriverWait(driver, Duration.ofSeconds(30));
        validateHelpers.Clickelement(addpim);
        wait.until(ExpectedConditions.urlContains("pim/addEmployee"));
        System.out.println("tôi đã được chạy");
    }
}
