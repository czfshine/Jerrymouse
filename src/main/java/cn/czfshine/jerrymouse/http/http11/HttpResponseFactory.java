package cn.czfshine.jerrymouse.http.http11;


import cn.czfshine.jerrymouse.http.HttpOutputStream;
import cn.czfshine.jerrymouse.http.HttpRequest;

import java.io.OutputStream;

public class HttpResponseFactory {

    /**
     * 从一个request创建一个response模板出来.
     * @param httpRequest
     * @return
     */
    public static HttpResponse createByRequest(HttpRequest httpRequest, OutputStream os){
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setServletOutputStream(new HttpOutputStream(os));
        httpResponse.setProtocol(httpRequest.getProtocol());

        return httpResponse;
    }
}
