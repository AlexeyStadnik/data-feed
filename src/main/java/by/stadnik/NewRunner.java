package by.stadnik;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by aliaksei on 5/4/17.
 */
public class NewRunner {
  public static void main(String[] args) {
    FirefoxDriver ff = new FirefoxDriver();

    ff.navigate().to("http://www.polyvore.com/outfits/search.sets?date=day&item_count.from=4&item_count.to=10");

    while(ff.findElements(By.className("product-list-item")) == null || ff.findElements(By.className("product-list-item")).isEmpty()) {
      ff.executeScript("window.scrollBy(100000000, 10000000000);");
      System.out.println(ff.findElements(By.className("type_set")).size());
    }

  }
}
