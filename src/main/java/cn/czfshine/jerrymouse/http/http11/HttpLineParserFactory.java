package cn.czfshine.jerrymouse.http.http11;

import cn.czfshine.jerrymouse.http.HTTPVersionNotSupportedException;
import cn.czfshine.jerrymouse.http.HttpLineParser;

public class HttpLineParserFactory {

    public static Http11LineParser http11LineParser=new Http11LineParser();
    public static HttpLineParser get(String version) throws HTTPVersionNotSupportedException {
        if(version.equals("HTTP/1.1")){
            return http11LineParser;
        }else{
            throw new HTTPVersionNotSupportedException();
        }
    }
}
