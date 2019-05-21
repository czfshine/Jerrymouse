package cn.czfshine.jerrymouse.http.http11;

import cn.czfshine.jerrymouse.http.HttpLineParser;
import cn.czfshine.jerrymouse.http.HttpRequest;
import cn.czfshine.jerrymouse.http.RequestHeaderNotSupportedException;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求头解析器
 */
public class Http11LineParser implements HttpLineParser {

    /**
     * 解析一行请求头数据压入request里面.
     * http 1.1 支持的头部如下，具体看文档
     * Request-header = Accept ; 14.1节
     * | Accept-Charset ; 14.2节
     * | Accept-Encoding ; 14.3节
     * | Accept-Language ; 14.4节
     * | Authorization ; 14.8节
     * | Expect ; 14.20节
     * | From ; 14.22节
     * | Host ; 14.23节
     * | If-Match ; 14.24节
     * | If-Modified-Since ; 14.25节
     * | If-None-Match ; 14.26节
     * | If-Range ; 14.27节
     * | If-Unmodified-Since ; 14.28节
     * | Max-Forwards ; 14.31节
     * | Proxy-Authorization ; 14.34节
     * | Range ; 14.35节
     * | Referer ; 14.36节
     * | TE ; 14.39节
     * | User-Agent ; 14.43节
     *
     * entity-header域定义关于entity-body的元信息，
     * 或当主体不存在时关于请求标识的资源。
     * 该元信息中的一些是【可选的】；
     * 一些可能是【要求的】，
     * 由该规范的各部分定义。
     * entity-header = Allow ; 14.7节
     * | Content-Encoding ; 14.11节
     * | Content-Language ; 14.12节
     * | Content-Length ; 14.13节
     * | Content-Location ; 14.14节
     * | Content-MD5 ; 14.15节
     * | Content-Range ; 14.16节
     * | Content-Type ; 14.17节
     * | Expires ; 14.21节
     * | Last-Modified ; 14.29节
     * | extension-header
     * extension-header = message-header
     * extension-header机制允许定义额外的entity-header域而不改变协议，
     * 但不能假设接收方认识这些域。接收方【应该】乎略不认识的头部域，且透明代理【必须】转发它。
     * @param hsr
     * @param line
     */
    @Override
    public void parser(@NotNull HttpRequest hsr, @NotNull String line) throws Exception {
        //todo 优化写法？map加函数?
        if(line.startsWith("Host:")){
            parserHost(hsr,line);
        }else if(line.startsWith("Content-Length")){
            parserContantLength(hsr, line);
        }else{
            throw new RequestHeaderNotSupportedException();
        }
    }

    /**
     * @param hsr
     * @param line
     */
    private void parserHost(@NotNull HttpRequest hsr, @NotNull String line){
        String hostname = line.substring(5);//“Host:”  五个字节
        //todo 没搞明白HOST应该对应request的哪个属性或方法，hhhhh
    }
    private void parserContantLength( @NotNull HttpRequest hsr, @NotNull String line){
        String lengthstr=line.substring("Content-Length".length());
        long len =Long.valueOf(lengthstr);
        hsr.setContentLength(len);
    }
}
