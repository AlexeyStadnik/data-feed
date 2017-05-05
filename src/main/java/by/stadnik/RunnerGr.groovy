package by.stadnik

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.jsoup.Jsoup

/**
 * Created by aliaksei on 3/31/17.
 */
class RunnerGr {
    public static void main(String[] args) {
        Jsoup.parse( new WebClient(BrowserVersion.CHROME).getPage("http://www.manning.com")
                .asInstanceOf[HtmlPage].asXml ).select("div.dotdbox").text
    }
}
