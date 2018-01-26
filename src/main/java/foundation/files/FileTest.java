package foundation.files;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author:zhoufan
 * @date :2018-01-23 15:58
 */
public class FileTest {

    @Test
    public void readFile() {
        Path path = null;
        try {
            path = Paths.get(new URI("file://D:/workspce/myPorject/TestCase"));
            Path file = Files.createFile(path);
            System.out.println(file.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getClassName() + "\t" + stackTraceElement.getMethodName());
        }
    }

    @Test
    public void test() {
        List<String> db = Lists.newArrayList("333");
        List<String> request = Lists.newArrayList("111", "222", "333");
        db.addAll(request);
        System.out.println(request);
        System.out.println(db);
        List<String> collect = db.stream().filter(s -> request.indexOf(s) < 0).collect(Collectors.toList());

        for (String s : db) {
            if (request.indexOf(s) >= 0) {
                System.out.println(s);
            }
        }
        System.out.println(collect);
    }


}
