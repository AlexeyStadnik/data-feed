package by.stadnik;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

/**
 * Created by aliaksei on 3/31/17.
 */
public class SeleniumRunner {
  public static void main(String[] args) throws InterruptedException {

    //"/Users/aliaksei/Soft/phantomjs-2.1.1-macosx/bin/phantomjs"
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setJavascriptEnabled(true);
    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/aliaksei/Soft/phantomjs-2.1.1-macosx/bin/phantomjs");

    WebDriver driver = new PhantomJSDriver(caps);
    driver.navigate().to("http://www.polyvore.com/cgi/search.sets?.in=json&.out=jsonx&request={%22item_count.to%22:%2210:400%22,%22date%22:%22day%22,%22item_count.from%22:%224%22,%22page%22:null,%22.passback%22:{%22next_token%22:{%22limit%22:%22100%22,%22start%22:0}}}&.locale=ru");

    System.out.println(driver.getPageSource());


//    while(driver.findElements(By.className("product-list-item")) == null || driver.findElements(By.className("product-list-item")).isEmpty()) {
//
//      System.out.println(driver.findElements(By.className("type_set")).size());
//    }
//    List<WebElement> itemsList = driver.findElements(By.className("type_set"));

//    List<WebElement> elements = driver.findElements(By.className("product-list-item"));
//
//    for (WebElement webElement : elements) {
//      System.out.println(webElement.findElement(By.className("product-title")).getText());
//      System.out.println(webElement.findElement(By.className("price")).getText());
//      System.out.println(webElement.findElement(By.className("product-url")).getAttribute("href"));
//    }
  }
}

