package app.wgx.com.aiYa.callback;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import app.wgx.com.aiYa.assistTool.JsonTool;
import app.wgx.com.aiYa.assistTool.MyLogger;
import okhttp3.Response;

/**
 * 自定义Callback
 */
public abstract class HttpCallBack extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        String result = response.body().string();
        MyLogger.json(result);
        String sss= (String) JsonTool.getKeyValue(result,"msg");
        return result;
    }

}
