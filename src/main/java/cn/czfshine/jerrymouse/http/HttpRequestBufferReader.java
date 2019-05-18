package cn.czfshine.jerrymouse.http;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static java.nio.charset.Charset.forName;

/**
 * 从缓冲区读并解析http请求
 */
public class HttpRequestBufferReader {

    /**
     * 从缓冲区读数据，组合成request对象
     * @param buffer 缓冲区
     * @return 读入的字节数
     */
    public int read(ByteBuffer buffer) throws Exception {
        int count =0; //已读入
        int concount=0;//已处理
        synchronized (buffer){
            if(isReadContenting){

            }else{
                //读请求头，意味着一行一行读
                ArrayList<Byte> alb= new ArrayList<>();

                while(buffer.remaining()>0){
                    byte cur = buffer.get();
                    count++;
                    if (cur != '\r') {
                        alb.add(cur);
                    } else {
                        byte last =buffer.get();
                        count++;
                        if(last!='\n'){
                            //理论上应该是回车换行CRLF，
                            // 如果不是数据就出错了，该连接直接返回400
                            throw new DamagedDataException();
                        }
                        concount=count;

                        if(alb.isEmpty()){
                            //空行
                            isReadContenting=true;
                            return concount;
                        }

                        Byte[] objects = alb.toArray(new Byte[0]);
                        byte[] bytes = new byte[objects.length];
                        for (int i = 0; i < objects.length; i++) {
                            bytes[i]=objects[i];
                        }

                        String s = new String(bytes, "UTF-8");
                        try {
                            HttpLineParser.parser(httpServletRequest,s);
                        } catch (Exception e) {
                            e.printStackTrace();
                            //todo
                            throw e;
                        }
                        alb.clear();

                    }
                }
            }
            //调整缓冲区位置到未读的字节
            buffer.position(concount);
            buffer.compact();

        }
        return concount;
    }
    /**
     * 从数组读数据，组合成request对象
     * @param buffer 缓冲区数组
     * @return 读入的字节数
     */
    @Deprecated
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


    public HttpRequestBufferReader() {
        init();
    }

    private void init(){
        //todo
        httpServletRequest=new HttpRequest();
        contentLength=0;
        readedContentLength=0;
    }

}
