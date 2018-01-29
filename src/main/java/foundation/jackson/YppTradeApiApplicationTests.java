package foundation.jackson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.util.TypeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class YppTradeApiApplicationTests {

    @Test
    public void test1() {
        HashMap<String, Object> objectHashMap = Maps.newHashMap();
        objectHashMap.put("1", BigDecimal.ZERO);
        objectHashMap.put("2", new Date());
        objectHashMap.put("3", 3L);
        objectHashMap.put("4", new Integer(1));
        objectHashMap.put("5", new Double(5.1));
        objectHashMap.put("6", Maps.newConcurrentMap());
        objectHashMap.put("7", Lists.newArrayList(1, 2.3));
        objectHashMap.put("8", Arrays.asList(1, 2, 3));
        objectHashMap.put("9", Byte.MAX_VALUE);
        objectHashMap.put("9", Boolean.FALSE);


        ValueFilter filter = (object, name, value) -> {
            if (ClassUtils.isPrimitiveWrapper(value.getClass())) {
                return String.valueOf(value);
            } else if (value instanceof Collection) {
                return JSON.toJSONString(value);
            } else if (value instanceof Object[]) {
                return JSON.toJSONString(value);
            }

            return value;

        };

        String s = JSON.toJSONString(objectHashMap, filter, SerializerFeature.SkipTransientField,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNonStringValueAsString);
        System.out.println(s);


    }


    @Test
    public void test2() {
        HashMap<String, Object> objectHashMap = Maps.newHashMap();
      /*  objectHashMap.put("1", BigDecimal.ZERO);
        objectHashMap.put("2", new Date());
        objectHashMap.put("3", 3L);
        objectHashMap.put("4", new Integer(1));
        objectHashMap.put("5", new Double(5.1));
        objectHashMap.put("6", Maps.newConcurrentMap());*/
        objectHashMap.put("4", new Integer(1));
        objectHashMap.put("7", Lists.newArrayList(1, 2, 3));
      /*  objectHashMap.put("8", Arrays.asList(1, 2, 3));
        objectHashMap.put("9", Byte.MAX_VALUE);
        objectHashMap.put("9", Boolean.FALSE);*/
     /*   GodCityDto objectHashMap = new GodCityDto();
        objectHashMap.setOrderno(1);
        objectHashMap.setHot(Byte.MAX_VALUE);*/
        ObjectSerializer objectSerializer = (serializer, object, fieldName, fieldType, features) -> {
            SerializeWriter out = serializer.getWriter();
            if (object == null) {
                out.writeString(StringUtils.EMPTY);
                return;
            }
            if (ClassUtils.isPrimitiveWrapper(TypeUtils.getClass(fieldType))) {
                out.writeString(String.valueOf(object));
            }
            if (fieldType instanceof List) {
                boolean writeClassName = serializer.out.isEnabled(SerializerFeature.WriteClassName);
                out.append('[');
                List list = (List) object;
                for (int i = 0, size = list.size(); i < size; ++i) {
                    Object item = list.get(i);
                    if (i != 0) {
                        out.append(',');
                    }

                    if (item == null) {
                        out.append("null");
                    } else {
                        Class<?> clazz = item.getClass();
/*
                        if (clazz == Integer.class) {
                            out.writeInt(((Integer) item).intValue());
                        } else if (clazz == Long.class) {
                            long val = ((Long) item).longValue();
                            if (writeClassName) {
                                out.writeLong(val);
                                out.write('L');
                            } else {
                                out.writeLong(val);
                            }
                        }*/
                        if (item instanceof Number) {
                            out.writeString(String.valueOf(item));
                        }
                    }
                }
                out.append(']');
            }

        };

        SerializeConfig config = new SerializeConfig();
      /*  config.put(Integer.class,objectSerializer);
        config.put(Byte.class,objectSerializer);*/
        config.put(Collection.class, ListSerializer.instance);
//        config.put(Boolean.class,objectSerializer);
        String s = JSON.toJSONString(objectHashMap, config);
        System.out.println(s);


    }

    @Test
    public void test3() throws JsonProcessingException {

        HashMap<String, Object> objectHashMap = Maps.newHashMap();
        objectHashMap.put("1", BigDecimal.ZERO);
        objectHashMap.put("2", new Date());
        objectHashMap.put("3", 3L);
        objectHashMap.put("4", new Integer(3));
        objectHashMap.put("5", new Double(5.1));
        objectHashMap.put("6", ImmutableMap.of("key","values"));
        objectHashMap.put("7", Lists.newArrayList("one"));
        objectHashMap.put("8", Byte.MAX_VALUE);
        objectHashMap.put("9", Boolean.FALSE);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Number.class, ToStringSerializer.instance);
        module.addSerializer(Integer.class, ToStringSerializer.instance);
        module.addSerializer(Boolean.class, ToStringSerializer.instance);
        module.addSerializer(List.class, ToStringSerializer.instance);
        module.addSerializer(Byte.class, ToStringSerializer.instance);
        module.addSerializer(Date.class, new DateSerializer(false,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(module);
        System.out.println(mapper.writeValueAsString(objectHashMap));
    }

    @Test
    public void test4() throws FileNotFoundException {

        File file = new File("D:\\workspace\\companyProjects\\ypp-trade-api\\src\\test\\java\\cn\\yupaopao\\ypptradeapi\\a.txt");

        byte[] bytes = new byte[100];
        try (InputStream is = new FileInputStream(file)) {
            while (is.read(bytes) != -1) {
                System.out.write(bytes);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() throws FileNotFoundException {

        File file = new File("D:\\workspace\\companyProjects\\ypp-trade-api\\src\\test\\java\\cn\\yupaopao\\ypptradeapi\\a.txt");

        char[] chars = new char[100];
        int charren = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((charren = reader.read(chars)) != -1) {
                System.out.println(charren);
                System.out.println(chars);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() throws NoSuchFieldException, IllegalAccessException {

      /*  System.out.println(Byte.MAX_VALUE);

        System.out.println((Integer) 100 == (Integer) 100);
        System.out.println(new Integer(100).equals(new Integer(100)));
        System.out.println((Integer) 1000 == (Integer) 1000);
        System.out.println(new Integer(1000).equals(new Integer(1000)));
*/
/*
        Integer a = 1000, b = 1000;
        System.out.println(a == b);*/

        Class cache = Integer.class.getDeclaredClasses()[0]; //1
        Field myCache = cache.getDeclaredField("cache"); //2
        myCache.setAccessible(true);//3
        Integer[] newCache = (Integer[]) myCache.get(cache); //4
        newCache[132] = newCache[133]; //5
        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b); //
    }

}
