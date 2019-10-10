package webCollector;

import cn.edu.hfut.dmic.webcollector.model.Page;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.*;
import java.sql.DriverManager;

public class PageUtils {

    /**
    * @Description:  获取框架自带的HtmlUnitDriver实例（模拟默认的浏览器）
    * @Param:
    * @return:
    * @Author: GongJian
    * @Date:
    */
    public static HtmlUnitDriver getDriver(Page page){
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
        htmlUnitDriver.setJavascriptEnabled(true);
        htmlUnitDriver.get(page.getUrl());
        return htmlUnitDriver;
    }


    public static HtmlUnitDriver getDriver(Page page,BrowserVersion browserVersion){
        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(browserVersion);
        htmlUnitDriver.setJavascriptEnabled(true);
        htmlUnitDriver.get(page.getUrl());
        return htmlUnitDriver;
    }
/** 
* @Description: 获取driver 
* @Param:  
* @return:  
* @Author: GongJian
* @Date:  
*/
    public static WebDriver getWebDriver(Page page){
        System.setProperty("phantomjs.binary.path", "D:\\Program Files\\phantomjs-2.1.1-windows/bin/phantomjs.exe");
        WebDriver driver = new PhantomJSDriver();
        driver.get(page.getUrl());
        return driver;
    }
/**
* @Description: 直接调用phantomJS
* @Param:  page
* @return:
* @Author: GongJian
* @Date:
*/
    public static String getPhantomJSDriver(Page page){
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        try {
            process = rt.exec("D:\\Program Files\\phantomjs-2.1.1-windows/bin/phantomjs.exe"+"D:\\work\\webCollector\\src\\main\\resources\\parser.js"+page.getUrl().trim());
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in,"UTF-8");
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sbf = new StringBuffer();
            String tmp = "";
            while((tmp = br.readLine())!=null){
                sbf.append(tmp);

        }return sbf.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    }
