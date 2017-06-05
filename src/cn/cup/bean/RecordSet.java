package cn.cup.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
public class RecordSet<T> {

    public RecordSet() {
        this.recordSet = new ArrayList<T>();
    }

    private int rowCount;
    private int pageSize;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCode() {
        return rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
    }

    private List<T> recordSet;

    public List<T> getRecordSet() {
        return recordSet;
    }

    public void setRecordSet(List<T> recordSet) {
        this.recordSet = recordSet;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}

