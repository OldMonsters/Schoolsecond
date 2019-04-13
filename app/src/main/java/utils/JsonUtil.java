package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class JsonUtil {

    /** @version 1.0
     *
     * bean实现该接口，反序列化结束的时候回调
     */
    public interface IOnAfterParseObject {
        void onAfterParseObject();
    }
    /** @version 1.0
     * bean实现该接口，序列化开始之前的时候回调
     */
    public interface IOnBeforToJson {
        void onBeforeToJson();
    }

    // 定义jackson对象
    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * 将对象转换成json字符串
     * @param data
     * @return
     */
    public static String toJSONString(Object data) {
        try {
            //检测类是否实现接口，若是则调用
            if(IOnBeforToJson.class.isAssignableFrom(data.getClass())){
                ((IOnBeforToJson)data).onBeforeToJson();
            }
            String string = mapper.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> T parseObject(String jsonData, Class<T> beanType) {
        try {
            T t = mapper.readValue(jsonData, beanType);
            //检测类是否实现接口，若是则调用
            if(IOnAfterParseObject.class.isAssignableFrom(beanType)){
                ((IOnAfterParseObject)t).onAfterParseObject();
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成list
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> parseArray(String jsonData, Class<T> beanType) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = mapper.readValue(jsonData, javaType);
            //检测类是否实现接口，若是则调用
            if(IOnAfterParseObject.class.isAssignableFrom(beanType)){
                for (T t : list) {
                    ((IOnAfterParseObject)t).onAfterParseObject();
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
