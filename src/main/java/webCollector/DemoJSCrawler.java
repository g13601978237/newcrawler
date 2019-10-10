package webCollector;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class DemoJSCrawler extends DeepCrawler {
    public DemoJSCrawler(String crawlPath) {
        super(crawlPath);
    }

    public Links visitAndGetNextLinks(Page page) {
        handleByPhantomJsDriver(page);
        return null;
    }

/**
* @Description: webcollector自带获取html
* @Param:
* @return:
* @Author: GongJian
* @Date:
*/
    protected void handleByHtmlUnitDriver(Page page){
        /*HtmlUnitDriver可以抽取JS生成的数据*/
        HtmlUnitDriver driver=PageUtils.getDriver(page,BrowserVersion.CHROME);
      /*HtmlUnitDriver也可以像Jsoup一样用CSS SELECTOR抽取数据
        关于HtmlUnitDriver的文档请查阅selenium相关文档*/
        print(driver);
    }

    /**
    * @Description:  phantomjs获取html
    * @Param:
    * @return:
    * @Author: GongJian
    * @Date:
    */
    protected void handleByPhantomJsDriver(Page page){
        WebDriver driver=PageUtils.getWebDriver(page);
        print(driver);
        driver.quit();
    }

    /** 
    * @Description: 解析过程
    * @Param:  
    * @return:  
    * @Author: GongJian
    * @Date:  
    */
    protected void print(WebDriver driver){
        List<WebElement> divInfos = driver.findElements(By.cssSelector("li.gl-item"));
        for(WebElement divInfo:divInfos){
            WebElement price=divInfo.findElement(By.className("J_price"));
            System.out.println(price+":"+price.getText());
        }
    }

    public static void main(String[] args) throws Exception {
        DemoJSCrawler crawler=new DemoJSCrawler("D:/test/crawler/jd/");
        crawler.addSeed("http://list.jd.com/list.html?cat=1319,1523,7052&page=1&go=0&JL=6_0_0");
        crawler.start(1);
    }
}
