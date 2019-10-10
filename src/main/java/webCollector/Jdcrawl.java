package webCollector;


import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Jdcrawl extends BreadthCrawler {
    public Jdcrawl(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        //this.addRegex("https://blog.csdn.net/.*?");
        //每层爬取最大的URL数量
        this.setTopN(1000);
    }

    public void visit(Page page, Links links) {
      /*  Document doc = page.getDoc();
       *//* String title = doc.title();
        System.out.println("标题："+title);*//*
        WebDriver webDriver = PageUtils.getWebDriver(page);
        List<WebElement> elements = webDriver.findElements(By.cssSelector(".feedlist_mod home"));
        for(WebElement ele:elements){
            String title = ele.findElement(By.className("title")).findElement(By.tagName("a")).getText();
            System.out.println(title);
        }*/
//        if (page.getUrl().equals("http://blog.csdn.net/.*/article/details/.*")) {
//            String title = page.getDoc().select("div[class=article_title]").first().text();
//            String author = page.getDoc().select("div[id=blog_userface]").first().text();
//            System.out.println("title:" + title + "\tauthor:" + author);
//        }
    }

  /*  public void visit(Page page, CrawlDatums crawlDatums) {

    }*/

    public static void main(String[] args) throws Exception {
        Jdcrawl crawl = new Jdcrawl("crawl", true);
        //添加爬虫的种子页面
        crawl.addSeed("https://blog.csdn.net/");
        crawl.addRegex("http://blog.csdn.net/.*/article/details/.*");
        //默认为50
        crawl.setThreads(10);
        crawl.setResumable(false);

        //爬取深度
        crawl.start(2);
    }
}
