/*
package webCollector;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;

import javax.swing.text.Document;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProxyIpFetcher {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(15);

    private static class ValidateCallable implements Callable<HttpConnection.KeyVal>{
        String ip;
        String port;
        String targetUrl;
        String[] keyWords;

        public ValidateCallable(String ip, String port, String targetUrl, String[] keyWords) {
            this.ip = ip;
            this.port = port;
            this.targetUrl = targetUrl;
            this.keyWords = keyWords;
        }

        public HttpConnection.KeyVal call() throws Exception {

            return null;
        }

    }
    public static HttpConnection.KeyVal fetchOne(final String targetUrl, final String[] keyWords) {
        String dlWebUrl = "http://www.kuaidaili.com/free/inha/1";
        Document doc = null;
        try {
            doc = Jsoup.connect(dlWebUrl).timeout(5000).get();
        } catch (Exception e) {

        }
        Element listDiv = doc.getElementById("list");
        Elements trs = listDiv.select("table tbody tr");

        List<Future<KeyVal>> futureList = new ArrayList<Future<KeyVal>>(trs.size());
        for (final Element tr : trs) {
            String ip = tr.child(0).text();
            String port = tr.child(1).text();
            futureList.add(threadPool.submit(new ValidateCallable(ip, port,
                    targetUrl, keyWords)));
        }
        for (int i = 0; i < 3; i++) {
            Thread.yield();//尝试启动验证线程
        }

        for (Future<KeyVal> future : futureList) {
            try {
                KeyVal kv = future.get();
                if (kv == null) {
                    //继续验证,直到获取一个可用的代理
                    continue ;
                }
                return kv;
            } catch (InterruptedException e) {


            } catch (ExecutionException e) {

            }
        }
        return null;
    }

}
*/
