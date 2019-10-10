package webCollector;

import cn.edu.hfut.dmic.webcollector.model.Page;

import java.util.ArrayList;

public abstract class GoodsList extends ArrayList<Goods>{
     public abstract void addGoods(Page page);
}
