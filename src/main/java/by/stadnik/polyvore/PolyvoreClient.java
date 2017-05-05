package by.stadnik.polyvore;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public class PolyvoreClient {

  private static final String POLYVORE_URL =
      "http://www.polyvore.com/cgi/search.sets?.in=json" +
          "&.out=jsonx&request={\"query\":\"*\",\".search_src\":\"masthead_search\",\"page\":null,\".passback\":{\"next_token\":{\"limit\":\"30\",\"start\":%s}}}&.locale=ru";


  public List<String> retrieveHrefs() {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setJavascriptEnabled(true);
    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/aliaksei/Soft/phantomjs-2.1.1-macosx/bin/phantomjs");

    WebDriver driver = new PhantomJSDriver(caps);
    Gson gson = new Gson();
    for(int i = 0; i <= 900; i=i+30) {
      driver.navigate().to(String.format(POLYVORE_URL, i));

      JSONObject json = new JSONObject(driver.getPageSource().substring(84));
      String result =  ((JSONObject)json.get("result")).getString("html");
      Elements select = Jsoup.parse(StringEscapeUtils.unescapeHtml(result)).select(".type_set");

      for (Element element : select) {
        System.out.println(element.child(0).child(0).attr("href"));
      }
    }
    return null;
  }

}
