package webCollector;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ECCrawler extends DeepCrawler {
    public static final Logger LOG  = LoggerFactory.getLogger(ECCrawler.class);
    private String seedFormat;
    protected RegexRule regexRule;

    public RegexRule getRegexRule() {
        return regexRule;
    }

    public void setRegexRule(RegexRule regexRule) {
        this.regexRule = regexRule;
    }

    public void addRegex(String urlRegex) {
        this.regexRule.addRule(urlRegex);
    }

    public ECCrawler(String crawlPath,String seedFormate) {
        super(crawlPath);
        this.seedFormat = seedFormate;
        this.regexRule = new RegexRule();
    }
    public void visit(Page page,Links link){}
    /** 
    * @Description:  获取总页数
    * @Param:  
    * @return:  
    * @Author: GongJian
    * @Date:  
    */
    public int getTotalPage(Page page){
        return 1;
    }
    /** 
    * @Description:  访问page且获取下次
    * @Param:  
    * @return:  
    * @Author: GongJian
    * @Date:  
    */
    public Links visitAndGetNextLinks(Page page) {
        Links links = new Links();
        String contentType = page.getResponse().getContentType();
        if (contentType != null && contentType.contains("text/html")) {
            org.jsoup.nodes.Document doc = page.getDoc();
            if (doc != null)
                links.addAllFromDocument(page.getDoc(), regexRule);
        }
        try{
            visit(page,links);
        }catch (Exception e){
            LOG.info("EXCEPTION",e);
        }
        return links;
    }

    public void start(int dept){
        addSeed();
        try {
            super.start(dept);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** 
    * @Description:  添加种子
    * @Param:  
    * @return:  
    * @Author: GongJian
    * @Date:  
    */
    public void addSeed(){
        int totalPage=getTotalPage(getPage(getSeed(seedFormat, 1)));
        for(int page=1;page<=totalPage;page++){
            this.addSeed(getSeed(seedFormat, page));
        }
    }
    /** 
    * @Description:  根据url获取page
    * @Param:  
    * @return:  
    * @Author: GongJian
    * @Date:  
    */
    public Page getPage(String url){
        Page page = null;
        try {
            HttpRequest httpRequest = new HttpRequest(url);
            HttpResponse response = httpRequest.getResponse();
            page = new Page();
            page.setUrl(url);
            page.setHtml(response.getHtmlByCharsetDetect());
            page.setResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
/**
* @Description:  获取seed
* @Param:
* @return:
* @Author: GongJian
* @Date:
*/
    public String getSeed(String seedFormat,Object ... page){
        return String.format(seedFormat, page);
    }
}
