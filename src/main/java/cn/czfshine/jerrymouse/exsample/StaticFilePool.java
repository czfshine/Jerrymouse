package cn.czfshine.jerrymouse.exsample;

import java.io.File;
import java.net.URL;

public class StaticFilePool {
    public static File fromUrlGetFile(String url){
        if(url.equals("/")){
            url = "/index.html";
        }
        URL resource = StaticFilePool.class.getResource(url);
        if(resource == null){
            return null;
        }
        String file = resource.getFile();
        return  new File(file);
    }

}
