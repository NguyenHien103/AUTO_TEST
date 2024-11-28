package test.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ValidateHelpers {
    private WebDriverWait wait;
    private WebDriver driver;
    // tiếp nhận giá trị
// truyền giá trị vào để gán cho webdriver
    public ValidateHelpers( WebDriver _driver){
        driver= _driver;// nếu mà driver thì mình phải dùng this.driver= driver;
    }
    public  void setText(By element, String value){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).sendKeys(value);

    }
    public  void Clickelement(By element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }
    public boolean verifyURL(String url) {

        System.out.print(driver.getCurrentUrl());
        System.out.print(url);

        return driver.getCurrentUrl().contains(url);
    }
    public boolean verifyelementText(By element, String valuetext){
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(valuetext);
    }


}
