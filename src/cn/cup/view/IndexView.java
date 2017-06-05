package cn.cup.view;

import java.util.ArrayList;
/**
 * Created by yc on 2017/2/24.
 */
public class IndexView {
    private ArrayList<GoodsView> newCups;
    private ArrayList<GoodsView> hotCups;
    private ArrayList<GoodsView> typeCups;

    public IndexView(ArrayList<GoodsView> newCups, ArrayList<GoodsView> hotCups, ArrayList<GoodsView> typeCups) {
        this.newCups = newCups;
        this.hotCups = hotCups;
        this.typeCups = typeCups;
    }

    public IndexView() {
    }

    public ArrayList<GoodsView> getNewCups() {
        return newCups;
    }

    public void setNewCups(ArrayList<GoodsView> newCups) {
        this.newCups = newCups;
    }

    public ArrayList<GoodsView> getHotCups() {
        return hotCups;
    }

    public void setHotCups(ArrayList<GoodsView> hotCups) {
        this.hotCups = hotCups;
    }

    public ArrayList<GoodsView> getTypeCups() {
        return typeCups;
    }

    public void setTypeCups(ArrayList<GoodsView> typeCups) {
        this.typeCups = typeCups;
    }
}
