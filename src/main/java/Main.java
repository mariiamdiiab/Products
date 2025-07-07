import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Main {
    protected WebDriver driver;

    public Main() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().window().maximize();
    }

    public void addProductsToCart(List<String> productNames) {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='product']"));

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            String productName = product.findElement(By.className("product-name")).getText();

            for (int j = 0; j < productNames.size(); j++) {
                String targetName = productNames.get(j);

                if (productName.equalsIgnoreCase(targetName)) {
                    WebElement addToCartBtn = product.findElement(By.tagName("button"));
                    addToCartBtn.click();
                    System.out.println("Added to cart: " + targetName);
                    break;
                }
            }
        }
    }

    public void openCart() {
        driver.findElement(By.xpath("//a[@class='cart-icon']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
    }

    public void addPromo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement promoTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='promoCode']")));
        promoTxt.sendKeys("Promo");
        driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
        String invalidTxt=wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[@class='promoInfo']")))).getText();
        System.out.println(invalidTxt);
    }

    public void teardown(){
        driver.quit();
    }

    public static void main(String[] args) {
        Main main = new Main();

        List<String> productsToBuy = Arrays.asList(
                "Cucumber - 1 Kg",
                "Carrot - 1 Kg",
                "Beans - 1 Kg"
        );

        main.addProductsToCart(productsToBuy);
        main.openCart(); 
        main.addPromo();
        main.teardown();
    }
}
