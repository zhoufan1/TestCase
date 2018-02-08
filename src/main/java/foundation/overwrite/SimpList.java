package foundation.overwrite;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author:zhoufan
 * @date :2018-01-29 18:06
 */
public class SimpList<T> implements Serializable {

    // 元素
    private transient Object[] elementData;

    /** 大小 */
    private int size;

    private static final int defaultSize = 10;

    private static  final Object[] EmptyData = {};

    public SimpList() {
        this.elementData = EmptyData;
    }

    public SimpList(int initCapacity) {
        if (initCapacity > 0) {
            this.elementData = new Object[initCapacity];
        } else if (initCapacity == 0) {
            this.elementData = EmptyData;
        }
        else {
            throw new IllegalArgumentException("this is initCapacity error , capacity " + initCapacity);
        }
        this.size = initCapacity;
    }

    public SimpList(T[] data) {
        if ((size = data.length) != 0) {
            this.elementData = data;
        }else{
            this.elementData = EmptyData;
        }
    }

    private void ensureCapacity(int  capacity){

    }

    @Override
    public String toString() {
        return "SimpList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }

    public void put(T value){
        size++;
        elementData[size++] = value;
    }

    public static void main(String[] args) {
        SimpList<String> stringSimpList = new SimpList<>(10);
        stringSimpList.put("hello");
        System.out.println(stringSimpList);

        String[] s ={"1","2","3"};
        SimpList<String> stringSimpList1 = new SimpList<>(s);
        System.out.println(stringSimpList1.toString());

        System.out.println(new SimpList<>());
        int max = Math.max(10, 20);
        System.out.println(max);
    }



}
