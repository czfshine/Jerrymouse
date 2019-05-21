package cn.czfshine.jerrymouse.start;

import cn.czfshine.jerrymouse.http.HTTPVersionNotSupportedException;
import cn.czfshine.jerrymouse.http.HttpRequest;
import cn.czfshine.jerrymouse.http.HttpRequestStreamReader;
import cn.czfshine.jerrymouse.http.MethodNotAllowedException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 一个连接，和一条TCP连接相对应的.
 * @author czfshine
 */
@Slf4j
public class Connection implements Runnable {
    private Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            log.info("connection {}:{}",socket.getInetAddress().getHostAddress(),socket.getPort());
            HttpRequestStreamReader httpRequestStreamReader = new HttpRequestStreamReader(inputStream);
            httpRequestStreamReader.waitHeadDone();
            HttpRequest request = httpRequestStreamReader.getRequest();
            System.out.println(request.getMethod());
            //todo 得等发送完才能结束
            socket.close();

        } catch (IOException e) {
            //e.printStackTrace();
        } catch (HTTPVersionNotSupportedException e) {
            e.printStackTrace();
        } catch (MethodNotAllowedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //处理结束保证关闭
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
