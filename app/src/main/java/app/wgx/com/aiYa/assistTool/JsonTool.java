package app.wgx.com.aiYa.assistTool;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json 数据解析
 */
public class JsonTool {
    /**
     * 把一个map变成json字符串
     *
     * @param map 封装了数据的map集合
     * @return 数据对应的Json字符串形式
     */
    public static String parseMapToJson(Map<?, ?> map) {
        try {
            Gson gson = new Gson();
            return gson.toJson(map);
        } catch (Exception e) {
            MyLogger.e(e.getMessage().toString());
        }
        return "";
    }


    /**
     * 将一个json字符串转换成指定的JavaBean对象
     *
     * @param json 需要转换的json字符串
     * @param cls  json字符串对应的JavBean
     * @param <T>  指定的数据类型
     * @return 封装好数据的JavaBean对象
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(json, cls);
        } catch (Exception e) {
            MyLogger.e(e.getMessage().toString());
        }
        return t;
    }

    /**
     * 把json字符串变成map形式
     *
     * @param json 需要转换的Json
     * @return map封装的数据
     */
    public static HashMap<String, Object> parseJsonToMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, Object> map = null;
        try {
            map = gson.fromJson(json, type);
        } catch (Exception e) {
            MyLogger.e(e.getMessage().toString());
        }
        return map;
    }


    /**
     * 把json字符串变成集合<br>
     * 参数示例：new TypeToken&lt;List&lt;YourBean&gt;&gt;(){}.getType()
     *
     * @param json 需要转换的Json字符串
     * @param type 示例：new TypeToken&lt;List&lt;YourBean&gt;&gt;(){}.getType()
     * @return 数据集合
     */
    public static List<?> parseJsonToList(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * 获取json串中某个字段的值，注意，只能获取同一层级的value
     *
     * @param json json字符串
     * @param key  json中下一级的bean
     * @return key对应的值
     */
    public static Object getKeyValue(String json, String key) {
        if (TextUtils.isEmpty(json))
            return null;
        if (!json.contains(key))
            return "";
        JSONObject jsonObject;
        Object value = null;
        try {
            jsonObject = new JSONObject(json);
            value = jsonObject.get(key);
        } catch (JSONException e) {
            MyLogger.e(e.getMessage().toString());
        }
        return value;
    }

}
