package foundation;

import java.lang.reflect.Array;
import java.time.Clock;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Objects;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author:zhoufan
 * @date :2018-02-07 16:30
 */
public class CompileTest {
    /**
     * javap 查看编译代码
     *
     * @param args
     */
    public static void main(String[] args) {
      /*  String str = "world";
        switch (str) {
            case "hello":
                System.out.println("hello");
                break;
            case "world":
                System.out.println("world");
                break;
            default:
                break;
        }*/

      /*  String[] s = new String[]{"1", "2"};
        List<String> strings = Arrays.asList(s);
//        strings.add("3");
        s[0] = "3";
        System.out.println(strings.toString());
        ArrayList<A> objects = Lists.newArrayList();
        LocalDate now = LocalDate.now();
        objects.add(new A(1, "1", now));
        //Thread.sleep(3000);
        objects.add(new A(2, "1", now));
        objects.add(new A(1, "1", now));
//        Comparator<A> comparator = (o1, o2) -> o1.getTime().isBefore(o2.getTime()) ? 1 : -1;
        Comparator<A> comparator = (o1, o2) -> o1.getAge() > o2.getAge() ? 1 : -1;
        objects.sort(comparator);
        System.out.println(JSON.toJSONString(objects));*/

        String s0 = "sa";
        String s = "s";
        String s1 = "a";
        String s2 = s + s1;
        String s3 = new String("sa");
        System.out.println(s3.intern() == s2);
        System.out.println(s2.intern() == s0);

        int[] arr = {1, 4, 1, 4, 2, 5, 4, 5, 8, 7, 8, 77, 88, 5, 4, 9, 6, 2, 4, 1, 5};
        IntStream arr1 = IntStream.of(arr);
        IntSummaryStatistics collect = arr1.boxed().collect(Collectors.summarizingInt(w -> w));
        System.out.println(collect);

        Map<Integer, Long> map = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println("map"+map.toString());
        Arrays.asList(arr).stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println();
    }

    static {
        System.out.println(System.currentTimeMillis());
//        System.out.println(Instant.now(). ());
        System.out.println(Clock.systemUTC().millis());
        System.out.println();
        System.out.println("1");
    }

    static {
        System.out.println("2");
    }

  /*  static class A {
        Integer age;
        String name;
        LocalDate time;

        public LocalDate getTime() {
            return time;
        }

        public void setTime(LocalDate time) {
            this.time = time;
        }

        public A(Integer age, String name, LocalDate time) {
            this.age = age;
            this.name = name;
            this.time = time;
        }

        public A() {
        }

        public A(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
*/
}
