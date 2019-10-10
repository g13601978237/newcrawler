package webCollector;

import cn.edu.hfut.dmic.webcollector.model.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JDGoodsList extends GoodsList {
    public static final String JD="JD";
    public void addGoods(Page page) {
        WebDriver driver = null;
        driver  = PageUtils.getWebDriver(page);
        List<WebElement> elements = driver.findElements(By.cssSelector("li.gl-item"));
        if(!elements.isEmpty()){
            for(WebElement ele:elements){
                Goods goods = new Goods();
                //商品平台
                goods.setPlatform(JD);
                //获取商品价格
                String priceStr = ele.findElement(By.className("p-price"))
                        .findElement(By.className("J_price"))
                        .findElement(By.tagName("i")).getText();
                if(!priceStr.equals("")){
                    goods.setPrice(Float.parseFloat(priceStr));
                }else{
                    goods.setPrice(-1F);
                }
                //获取商品名字
                String name = ele.findElement(By.className("p-name")).findElement(By.tagName("em")).getText();
                goods.setName(name);
                //获取评价
                String commitStr =ele.findElement(By.className("p-commit")).findElement(By.tagName("a")).getText();
                goods.setCommit(commitStr);
                add(goods);
            }
        }else{
            System.out.println("empty");
        }

    }
}
