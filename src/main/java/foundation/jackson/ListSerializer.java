package foundation.jackson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;
import org.apache.commons.lang3.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhoufan on 2017/7/14.
 */
public class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        boolean writeClassName = serializer.out.isEnabled(SerializerFeature.WriteClassName);
        SerializeWriter out = serializer.out;

        Type elementType = null;
        if (writeClassName) {
            elementType = TypeUtils.getCollectionItemType(fieldType);
        }

        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }

        List<?> list = (List<?>) object;

        if (list.size() == 0) {
            out.append("[]");
            return;
        }

        SerialContext context = serializer.getContext();
        serializer.setContext(context, object, fieldName, 0);
        ObjectSerializer itemSerializer = null;
        try {
            out.append('[');
            for (int i = 0, size = list.size(); i < size; ++i) {
                Object item = list.get(i);
                if (i != 0) {
                    out.append(',');
                }

                if (item == null) {
                    out.append("null");
                } else {
                    Class<?> clazz = item.getClass();

                    if (ClassUtils.isPrimitiveWrapper(clazz)) {
                        out.writeString(String.valueOf(item));
                    }
                    if (serializer.containsReference(item)) {
                        serializer.writeReference(item);
                    } else {
                        itemSerializer = serializer.getObjectWriter(item.getClass());
                        itemSerializer.write(serializer, item, i, elementType, 0);
                    }
                }
            }
            out.append(']');
        } finally {
            serializer.setContext(context);
        }
    }
}
