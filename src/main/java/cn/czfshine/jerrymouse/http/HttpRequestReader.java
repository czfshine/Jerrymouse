package cn.czfshine.jerrymouse.http;

import javax.servlet.http.HttpServletRequest;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 从缓冲区读并解析http请求
 */
public class HttpRequestReader {

    /**
     * 从缓冲区读数据，组合成request对象
     * @param buffer 缓冲区
     * @return 读入的字节数
     */
    public int read(ByteBuffer buffer) throws DamagedDataException {
        int count =0;
        synchronized (buffer){
            if(isReadContenting){

            }else{
                //读请求头，意味着一行一行读
                StringBuilder sb =new StringBuilder();
                ArrayList<Byte> alb= new ArrayList<>();

                byte cur = buffer.get();
                count++;
                if(cur=='\r' ){
                    byte last =buffer.get();
                    count++;
                    if(last!='\n'){
                        throw new DamagedDataException();
                    }

                    Object[] objects = alb.toArray();
                }else{
                    alb.add(cur);
                }
            }
        }
        return count;
    }
    /**
     * 从数组读数据，组合成request对象
     * @param buffer 缓冲区数组
     * @return 读入的字节数
     */
    public int read(Byte[] buffer){
        return 0;
    }

    /**判断是否完整读入一个请求
     * @return 请求是否已经完整
     */
    public boolean isDone(){
        return false;
    }

    /**
     * 获取读入的request
     * @return 读入的request，null表示请求还未完整
     */
    public HttpServletRequest getRequest(){
        return null;
    }

    /**
     * 清空和初始化状态，准备再次读入下一个请求
     * @return 上次读入的request是否完整并被get了一次，理论上应该是true
     */
    public boolean clean(){
        return false;
    }


    /*状态*/

    private HttpServletRequest httpServletRequest; //读入的请求对象
    private int contentLength = 0; //预期的请求体长度
    private int readedContentLength= 0;//已读入的请求体长度
    private boolean isReadContenting=false;


    private void init(){
        //todo
        //httpServletRequest=new HttpServletRequest();
        contentLength=0;
        readedContentLength=0;
    }

}
