package cn.czfshine.jerrymouse.start;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 连接池,封装了专门处理连接的几个线程池.
 *
 * @author czfshine
 */
public class ConnectionPool {

    /**
     * 正常的短连接的线程池，<10s
     */
    private ThreadPoolExecutor normalPool;
    /**
     * 需要保持长连接的线程池，>10s <10m
     * 为了保证不被滥用，最多10分钟就超时，客户端可超时重连
     */
    private ThreadPoolExecutor longPool;
    public ConnectionPool (){
        normalPool =new ThreadPoolExecutor(5,50,
                10, SECONDS, new ArrayBlockingQueue<>(50));
        longPool = new ThreadPoolExecutor(5,200,
                10,MINUTES,new ArrayBlockingQueue<>(200));
    }

    /**
     * 提交一个新的连接到连接池
     */
    public void submitConnection(Connection connection){
        Future<?> submit = normalPool.submit(connection);
        //todo submit?
    }

    @Deprecated
    public void stopConnection(Connection connection){
        normalPool.remove(connection);//todo?
    }
}
