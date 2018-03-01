package foundation.strem;

import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * int操作
 *
 * @author:zhoufan
 * @date :2018-03-01 17:52
 */
public class IntStreamTest {
    int[] arr = {1, 4, 1, 4, 2, 5, 4, 5, 8, 7, 8, 77, 88, 5, 4, 9, 6, 2, 4, 1, 5};

    /**
     * 统计函数操作
     * 计数，求和，最小，最大，平均
     */
    @Test
    public void IntSummaryStatistics() {
        IntSummaryStatistics collect = IntStream.of(arr).boxed().collect(Collectors.summarizingInt(w -> w));
        System.out.println(collect);
    }

    /**
     * 查看重复记录并计数
     */
    @Test
    public void findRepeatAndCount() {
        Map<Integer, Long> collect = IntStream.of(arr).boxed().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println(collect.toString());
    }
}
