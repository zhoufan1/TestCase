package foundation;

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
        String str = "world";
        switch (str) {
            case "hello":
                System.out.println("hello");
                break;
            case "world":
                System.out.println("world");
                break;
            default:
                break;
        }
    }
}
