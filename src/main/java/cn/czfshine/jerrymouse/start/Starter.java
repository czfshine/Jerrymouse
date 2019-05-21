package cn.czfshine.jerrymouse.start;

public class Starter {

    public static void main(String[] args) {
        //连接池
        ConnectionPool connectionPool = new ConnectionPool();
        new TcpBioListener(connectionPool).startListen();
    }
}
