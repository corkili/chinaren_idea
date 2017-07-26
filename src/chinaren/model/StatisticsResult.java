package chinaren.model;

import java.util.List;

/**
 * Created by 李浩然 on 2017/7/26.
 */
public class StatisticsResult {
    private List<String> dataString;

    private List<String> classNames;

    private int[][] counts;

    public StatisticsResult(List<String> dataString, List<String> classNames, int[][] counts) {
        this.dataString = dataString;
        this.classNames = classNames;
        this.counts = counts;
    }

    public List<String> getDataString() {
        return dataString;
    }


    public List<String> getClassNames() {
        return classNames;
    }


    public int[][] getCounts() {
        return counts;
    }

}
