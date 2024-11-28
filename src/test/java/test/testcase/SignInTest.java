package test.testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.pages.DashboardPage;
import test.pages.ProjectPim;
import test.pages.SignPage;

public class SignInTest {
    private WebDriver driver;
    private SignPage signInPage;
    private DashboardPage dashboardPage;
    private ProjectPim projectPim;

    @BeforeMethod
    public void setUp() {
        // Cài đặt ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Điều hướng tới trang đăng nhập
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Khởi tạo các đối tượng trang và trợ giúp
        signInPage = new SignPage(driver);
    }

    @Test(priority = 1)
    public void testSignInAndOpenPIM() {
        // Thực hiện đăng nhập với tài khoản Admin
        dashboardPage = signInPage.SignIn("Admin", "admin123");

        // Mở PIM và nhận đối tượng ProjectPim
        projectPim = dashboardPage.opendPIM();

        // Thêm PIM
        projectPim.addPim();
    }

    @Test(priority = 2)
    public void testSignInWithInvalidCredentials() {
        // Thực hiện đăng nhập với tài khoản không hợp lệ
        signInPage.SignIn("InvalidUser", "invalidPassword");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page.");
    }

    @Test(priority = 3)
    public void testSignInWithBlankCredentials() {
        // Thực hiện đăng nhập với tên người dùng và mật khẩu để trống
        signInPage.SignIn("", "");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page.");
    }
    @Test(priority = 4)
    public void testSignInWithWhitespaceAroundCredentials() {
        // Thực hiện đăng nhập với tên người dùng và mật khẩu có khoảng trắng ở đầu và cuối
        signInPage.SignIn("  Admin  ", "  admin123  ");

        // Kiểm tra nếu đăng nhập thành công và chuyển đến trang Dashboard
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Failed to log in with trimmed credentials.");
    }

    @Test(priority = 5)
    public void testSignInWithWhitespaceInUsername() {
        // Thực hiện đăng nhập với tên người dùng có khoảng trắng giữa
        signInPage.SignIn("Ad min", "admin123");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page with username containing whitespace.");
    }

    @Test(priority = 6)
    public void testSignInWithWhitespaceInPassword() {
        // Thực hiện đăng nhập với mật khẩu có khoảng trắng giữa
        signInPage.SignIn("Admin", "ad min123");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page with password containing whitespace.");
    }

    @Test(priority = 7)
    public void testSignInWithEmptyPassword() {
        // Thực hiện đăng nhập với tên người dùng hợp lệ nhưng bỏ trống mật khẩu
        signInPage.SignIn("Admin", "");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page with empty password.");
    }

    @Test(priority = 8)
    public void testSignInWithEmptyUsername() {
        // Thực hiện đăng nhập với mật khẩu hợp lệ nhưng bỏ trống tên người dùng
        signInPage.SignIn("", "admin123");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page with empty username.");
    }

    @Test(priority = 9)
    public void testSignInWithOnlyWhitespaceInUsername() {
        // Thực hiện đăng nhập với tên người dùng chỉ chứa khoảng trắng
        signInPage.SignIn("     ", "admin123");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page with username containing only whitespace.");
    }

    @Test(priority = 10)
    public void testSignInWithOnlyWhitespaceInPassword() {
        // Thực hiện đăng nhập với mật khẩu chỉ chứa khoảng trắng
        signInPage.SignIn("Admin", "     ");

        // Kiểm tra nếu trang vẫn là trang đăng nhập
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Failed to stay on login page with password containing only whitespace.");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Đảm bảo thoát khỏi trình duyệt sau khi hoàn thành bài test
        }
    }
}
