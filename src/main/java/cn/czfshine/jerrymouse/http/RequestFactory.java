package cn.czfshine.jerrymouse.http;

import cn.czfshine.jerrymouse.http.HTTPVersionNotSupportedException;
import cn.czfshine.jerrymouse.http.HttpRequest;
import cn.czfshine.jerrymouse.http.MethodNotAllowedException;

public class RequestFactory {
    public static HttpRequest createRequest(String method,String version)
            throws MethodNotAllowedException, HTTPVersionNotSupportedException {
        if(version.equals("HTTP/1.1") || version.equals("HTTP/1.0")){
            //todo 目前只支持http 1.1 的部分方法 23333333
            switch (method){
                case "GET":
                case "POST":
                case "PUT":
                case "HEAD":
                    break;
                    default:
                        throw  new MethodNotAllowedException();

            }
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setMethod(method);
            httpRequest.setProtocol(version);
            return httpRequest;
        }else{
            throw new HTTPVersionNotSupportedException();
        }

    }

}
