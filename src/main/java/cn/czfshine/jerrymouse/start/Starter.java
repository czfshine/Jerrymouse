package cn.czfshine.jerrymouse.start;

import cn.czfshine.jerrymouse.exsample.EchoServlet;
import cn.czfshine.jerrymouse.exsample.StaticServlet;
import cn.czfshine.jerrymouse.router.Router;

import javax.servlet.http.HttpServlet;

public class Starter {

    public static void main(String[] args) {

        Router router = Router.getRouter();
        router.addRouteItem(".*", StaticServlet.class);
        //连接池
        ConnectionPool connectionPool = new ConnectionPool();
        new TcpBioListener(connectionPool).startListen();
    }
}
