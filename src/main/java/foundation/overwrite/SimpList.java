package foundation.overwrite;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author:zhoufan
 * @date :2018-01-29 18:06
 */
public class SimpList<T> implements Serializable {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("1");
        arrayList.add("1");
        arrayList.trimToSize();
        ArrayList clone = (ArrayList)arrayList.clone();
        System.out.println(clone.toString());
        System.out.println(arrayList.size() +" \t" + arrayList.toString());
    }

}
