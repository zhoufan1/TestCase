package foundation;

/**
 * @author:zhoufan
 * @date :2018-02-07 16:30
 */
public class CompileTest {
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
