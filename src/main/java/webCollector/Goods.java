package webCollector;

public class Goods {
    private String platform;
    private String url;
    private String name;
    private Float price;
    private String commit;

    public Goods() {
    }

    public Goods(String platform, String url, String name, Float price, String commit) {

        this.platform = platform;
        this.url = url;
        this.name = name;
        this.price = price;
        this.commit = commit;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "platform='" + platform + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", commit=" + commit +
                '}';
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
}
