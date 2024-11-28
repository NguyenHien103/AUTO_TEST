package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.common.ValidateHelpers;

public class SignPage {
    private WebDriver driver;
    private ValidateHelpers validateHelpers;

    private By uname = By.xpath("//input[@placeholder='Username']");
    private By pass = By.xpath("//input[@placeholder='Password']");
    private By loginbtn = By.xpath("//button[normalize-space()='Login']");

// contructor yêu cầu nhận 1 đối tượng webdriver để làm tham số
    public SignPage(WebDriver driver) {
        this.driver = driver;// this biến thành viên
        validateHelpers= new ValidateHelpers(driver);// driver này dùng để truyền cho contructor 1 đối tượng webdriver
//driver giữ giá trị webdriver để có thể sử dụng các method của class
    }
    public DashboardPage SignIn(String username, String password){
        Assert.assertTrue(validateHelpers.verifyelementText(loginbtn,"Login"),"not is Sigin page");
//        validateHelpers.waitForPageLoaded();
        validateHelpers.setText(uname,username);
        validateHelpers.setText(pass, password);
        validateHelpers.Clickelement(loginbtn);
        System.out.println("Current URL after login: " + driver.getCurrentUrl());

        return new DashboardPage(this.driver);

    }
}
