package cn.czfshine.jerrymouse.start;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个监听tcp端口的监听器和调度器，使用BIO（阻塞同步IO）.
 * 注：目标是写一层很薄的TCP层（或者说和网络相关的），
 * 主要是网络很难单元测试，在测试内是不包括这一层的，
 * 所以尽量简单并简短，方便在其他模块的测试中模拟（MOCK）这一层
 * 注2：单元测试不包括这一层，但是在程序外部使用postman进行黑盒测试
 * todo 使用nio 和 aio 编写同样的代码
 */
@Slf4j
public class TcpBioListener extends Thread {

    /*配置项
    todo 配置文件
     */

    private final int port=8080;

    /**
     * 处理连接的连接池
     */
    private ConnectionPool connectionPool;

    public TcpBioListener(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;//直接注入的
    }
    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port, 500);
            log.info("Server start in {}",port);
            while (true){
                Socket accept = serverSocket.accept();
                Connection connection = new Connection(accept);
                connectionPool.submitConnection(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startListen(){
        setName("TBLT"); //Tcp Bio Listener Thread
        start();
    }
}
