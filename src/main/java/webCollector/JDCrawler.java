package webCollector;

import cn.edu.hfut.dmic.webcollector.crawler.Crawler;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpRequester;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;
import cn.edu.hfut.dmic.webcollector.net.RandomProxyGenerator;
import cn.edu.hfut.dmic.webcollector.util.Config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JDCrawler extends ECCrawler {

    private GoodsList goodsList;

    public JDCrawler(String crawlPath, String seedFormate) {
        super(crawlPath, seedFormate);
        goodsList = new JDGoodsList();
    }

    @Override
    public int getTotalPage(Page page) {
        return super.getTotalPage(page);
    }

    @Override
    public void visit(Page page, Links link) {
        goodsList.addGoods(page);
    }

    public static void main(String[] args) {
        for(int i = 1;i<3;i++){
            JDCrawler jdCrawler = new JDCrawler("D:/test/crawler/jd/", "https://list.jd.com/list.html?cat=1319,1523,7052&page=3&sort=sort_rank_asc&trans="+i+"&JL=6_0_0#J_main");
            jdCrawler.setThreads(100);
            //连接超时
            Config.TIMEOUT_CONNECT = 5000;
            //读取超时
            Config.TIMEOUT_READ = 20000;
            //可失败重复次数
            Config.MAX_RETRY = 30;
            //超时停止
            Config.requestMaxInterval = 1000 * 60 * 2;
        /*随机代理生成器,RandomProxyGenerator是WebCollector代理切换的一个插件
        用户可以根据自己的业务需求，定制代理切换的插件，代理切换插件需要实现ProxyGenerator*/
         /*随机代理RandomProxyGenerator是一种比较差的策略，
                  我们建议用户自己编写符合自己业务的ProxyGenerator。
                  编写ProxyGenerator主要实现ProxyGenerator中的next方法。*/
       /* RandomProxyGenerator proxyGenerator = new RandomProxyGenerator() {
            public void markGood(Proxy proxy, String url) {
                InetSocketAddress address = (InetSocketAddress) proxy.address();
                System.out.println("Good Proxy:" + address.toString() + "   " + url);
            }
            public void markBad(Proxy proxy, String url) {
                InetSocketAddress address = (InetSocketAddress) proxy.address();
                System.out.println("Bad Proxy:" + address.toString() + "   " + url);
        }
    };
        for(int i = 1;i<=5;i++){
            try {
                addProxy("http://proxy.com.ru/list_" + i + ".html", proxyGenerator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
            /*获取爬虫的http请求器*/
            HttpRequesterImpl requester = (HttpRequesterImpl) jdCrawler.getHttpRequester();
            /*设置http请求器的随机代理请求器*/
            //requester.setProxy("172.18.1.219", 8658,Proxy.Type.SOCKS);
            jdCrawler.start(2);
            jdCrawler.print();
        }

    }
    private void print(){
        for(Goods g:goodsList){
            System.out.println(g);
        }
    }
public static void addProxy(String url, RandomProxyGenerator proxyGenerator) throws Exception {

        HttpRequest request = new HttpRequest(url);
    for (int i = 0; i <= 3; i++) {
        try {
            String html = request.getResponse().getHtmlByCharsetDetect();
            String regex = "([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}).+?([0-9]{1,4})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                System.out.println("add proxy:" + matcher.group(1) + ":" + matcher.group(2));
                String ip = matcher.group(1);
                int port = Integer.valueOf(matcher.group(2));
                proxyGenerator.addProxy(ip, port);
            }
            break;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
}
