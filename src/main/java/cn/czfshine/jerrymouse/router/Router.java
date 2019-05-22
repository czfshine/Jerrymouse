package cn.czfshine.jerrymouse.router;

import cn.czfshine.jerrymouse.http.HttpRequest;

import javax.servlet.Servlet;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Router {

    private HashMap<String,Servlet> allrouter=new HashMap<>();

    private Router() {
    }

    private static Router router=new Router();
    public static Router getRouter() {
        return router;
    }

    public void addRouteItem(String pattern,Class<? extends Servlet> clazz){
        try {
            Servlet servlet = clazz.getConstructor().newInstance();
            allrouter.put(pattern,servlet);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public Servlet routeRequest(HttpRequest httpRequest){

        String requestURI = httpRequest.getRequestURI();
        for (String s: allrouter.keySet()
             ) {
            if(requestURI.matches(s)){
                return allrouter.get(s);
            }
        }
        return null;
    }
}
