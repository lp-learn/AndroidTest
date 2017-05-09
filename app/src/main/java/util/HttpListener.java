package util;

import java.io.IOException;

/**
 * 请求的相应接口
 * Created by OKMAN on 2017/5/9.
 */

public interface HttpListener {
    /**
     * 请求正常完成
     */
    void onSuccess(String response);

    /**
     * 请求过程发生异常
     * @param e
     */
    void onFail(IOException e);
}
