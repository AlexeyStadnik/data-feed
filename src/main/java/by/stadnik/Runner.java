package by.stadnik;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by aliaksei on 3/30/17.
 */
public class Runner {
  public static void main(String[] args) {
    String searchQuery = "Iphone 6s";

    WebClient client = new WebClient(BrowserVersion.FIREFOX_3_6);
    client.setCssEnabled(false);
//    client.getOptions().setCssEnabled(false);
//    client.getOptions().setJavaScriptEnabled(true);
    try {
      String searchUrl = "http://www.hm.com/us/products/ladies" ;//+ URLEncoder.encode(searchQuery, "UTF-8");
      HtmlPage page = client.getPage(searchUrl);

      System.out.println(page.asText());
    } catch (Exception e) {
      e.printStackTrace();
    }

//    try {
//      System.out.println(new WebClient(BrowserVersion.CHROME).getPage("http://www.hm.com/us/products/ladies").getWebResponse().getContentAsString());
//
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

  }
}

