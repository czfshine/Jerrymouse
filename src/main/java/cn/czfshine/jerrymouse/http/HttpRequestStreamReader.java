package cn.czfshine.jerrymouse.http;


import cn.czfshine.jerrymouse.http.http11.HttpLineParserFactory;

import java.io.*;

/**
 * 使用InputStream解析http请求，显然是阻塞式的
 */
public class HttpRequestStreamReader {


    private BufferedReader bufferedReader;
    /**将解析器与请求数据流绑定，需手动调用waitHeadDone解析头部，剩下的请求体通过request对象的getInputStream()获取.
     * @see HttpRequest#getInputStream()
     * @see HttpRequestStreamReader#waitHeadDone()
     * @param is
     */
    public HttpRequestStreamReader(InputStream is) throws HTTPVersionNotSupportedException, IOException, MethodNotAllowedException {
        this.is=is;
        bufferedReader = new BufferedReader(new InputStreamReader(is));
        checkRequestLine();
    }

    private boolean headDone=false;
    /**是否读取到完整的数据头.
     * @return 为true说明可以拿到可用的request对象了
     */
    @Deprecated
    public boolean isHeadDone(){
        return headDone;
    }

    /**
     * 阻塞直到http请求的头部读完
     */
    @Deprecated
    public void waitHeadDone() throws Exception {
        parserAllHead();
        headDone=true;
    }

    /**获取http请求对象
     * @return null说明对象还未组装完成
     */
    public HttpRequest getRequest(){
        if(headDone){
            return httpRequest;
        }
        return null;
    }

    private HttpLineParser httpLineParser;
    private HttpRequest httpRequest;
    private InputStream is;

    /**
     * 对请求行进行校验
     * @throws IOException 网断了？
     * @throws HTTPVersionNotSupportedException 目前不支持这个http版本
     * @throws MethodNotAllowedException 目前不支持这个请求方法
     */
    private void checkRequestLine() throws IOException, HTTPVersionNotSupportedException, MethodNotAllowedException {
        String head = bufferedReader.readLine();
        
        //@doc http1.1/p28
        //Request-Line = Method SP Request-URI SP HTTP-Version
        String[] ss = head.split(" ");
        String method =ss[0];
        String requestUri =ss[1];
        String httpVersion=ss[2];
        httpRequest = RequestFactory.createRequest(method, httpVersion);
        httpRequest.setURI(requestUri);
        httpLineParser = HttpLineParserFactory.get(httpVersion);
    }

    /**解析所有请求头
     * @throws Exception
     */
    private void parserAllHead() throws Exception {
        while (true){
            String line = bufferedReader.readLine();
            if(line.isEmpty()){
                break;
            }
            httpLineParser.parser(httpRequest,line);
        }
    }
}
