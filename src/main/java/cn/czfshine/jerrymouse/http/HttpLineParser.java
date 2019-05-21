package cn.czfshine.jerrymouse.http;

public interface HttpLineParser {
    /**解析一行请求头数据到request里面.
     * @param hsr
     * @param line
     */
    void parser(HttpRequest hsr, String line) throws Exception;
}
