package cn.cup.view;

import cn.cup.bean.Cup;

/**
 * Created by Administrator on 2016/11/10.
 */
public class GoodsView {
    private Cup cup;

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public GoodsView(Cup cup) {
        this.cup = cup;
    }

    public GoodsView() {
    }

    public String getFirstImage(){
        return this.cup.getCupImages().split(",")[0];
    }
}
