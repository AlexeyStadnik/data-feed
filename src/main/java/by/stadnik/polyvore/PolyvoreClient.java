package by.stadnik.polyvore;


import by.stadnik.polyvore.model.Item;
import by.stadnik.polyvore.model.Outfit;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PolyvoreClient {

  private static final String POLYVORE_URL =
      "http://www.polyvore.com/cgi/search.sets?.in=json" +
          "&.out=jsonx&request={\"query\":\"%s\",\".search_src\":\"masthead_search\",\"page\":null,\".passback\":{\"next_token\":{\"limit\":\"30\",\"start\":%s}}}&.locale=ru";

  private static final String PREFIX = "http://www.polyvore.com";

  WebDriver driver;
  WebDriver driverForItems;
  DesiredCapabilities caps;

  {
    caps = new DesiredCapabilities();
    caps.setJavascriptEnabled(true);
    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/aliaksei/Soft/phantomjs-2.1.1-macosx/bin/phantomjs");

    driver = new PhantomJSDriver(caps);
    driverForItems = new PhantomJSDriver(caps);
  }

  public List<String> retrieveHrefs(String criteria) {
    ArrayList<String> hrefs = new ArrayList<>();

    Gson gson = new Gson();
    for (int i = 0; i <= 900; i = i + 30) {
      driver.navigate().to(String.format(POLYVORE_URL, criteria, i));

      JSONObject json = new JSONObject(driver.getPageSource().substring(84));
      String result = ((JSONObject) json.get("result")).getString("html");
      Elements select = Jsoup.parse(StringEscapeUtils.unescapeHtml(result)).select(".type_set");

      for (Element element : select) {
        hrefs.add(element.child(0).child(0).attr("href"));
      }
    }
    return hrefs.parallelStream().map(str -> PREFIX + str.substring(2)).collect(Collectors.toList());
  }

  public List<Outfit> retrieveOutfits(List<String> hrefs) {
    return hrefs.stream().map(this::retrieveSingleOutfit).filter(Objects::nonNull).collect(Collectors.toList());
  }

  private Outfit retrieveSingleOutfit(String href) {
    try {
      driver.navigate().to(href);
      Outfit outfit = new Outfit();

      List<WebElement> mainImg = driver.findElements(By.className("main_img"));

      outfit.setMainImage(retrieveImage(mainImg.get(1).getAttribute("src")));


      String favCount = driver.findElements(By.className("fav_count")).get(0).getText();
      try {
        outfit.setLikes(Integer.parseInt(favCount.replace(",", "")));
      } catch (Exception e) {
        e.printStackTrace();
      }

      outfit.setOutfitName(driver.getTitle().replaceAll("\\\\", " "));

//    WebElement webElement = driver.findElements(By.xpath("//div[@class='grid_item hover_container type_thing span1w span1h']")).get(0);
//
//    List<WebElement> itemElements = webElement.findElements(By.xpath(".//li"));

      ArrayList<Item> items = new ArrayList<>();

      driver.findElements(By.className("img_size_m")).forEach(img -> {
        WebElement parent = img.findElement(By.xpath(".."));
        String itemHref = parent.getAttribute("href");
        Item item = retrieveItem(driverForItems, itemHref);
        if (item != null) {
          items.add(item);
        }
      });

      outfit.setItemList(items);
      PolyvoreRunner.persistToFileSystem(outfit);
      return outfit;
    }catch (Exception e) {
      return null;
    }
  }

  private Item retrieveItem(WebDriver driver, String itemHref) {
    driver.navigate().to(itemHref);
    Item item = new Item();
    List<WebElement> crumb = driver.findElements(By.className("crumb"));
    if(crumb.size() < 2) {
      return null;
    }
    List<String> category = new ArrayList<>();
    crumb.forEach(cat -> {
      category.add(cat.findElement(By.xpath(".//span")).getText());
    });
    if(category.contains("Home") || category.contains("Home Decor")) {
      return null;
    }
    item.setCategories(category);

    WebElement thingImg = driver.findElement(By.id("thing_img")).findElement(By.xpath(".//a")).findElement(By.xpath(".//img"));

    String imageHref = thingImg.getAttribute("src");
    String title = thingImg.getAttribute("title");

    List<WebElement> db = driver.findElements(By.className("bd"));
    WebElement element = db.get(1).findElement(By.xpath(".//div"));
    String desc = element.findElement(By.xpath(".//div")).getText();

    item.setItemImage(retrieveImage(imageHref));
    item.setItemTitle(title);
    item.setDesc(desc);

    return item;
  }


  private BufferedImage retrieveImage(String ref) {
    try {
      return ImageIO.read(new URL(ref));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
