package test.cn.czfshine.jerrymouse.http.mock;

public class HttpRequestMockData {
    public static  byte[] getSimpleNoBodyLineGet(){
        //max buffer size 128
        String a= //55
                "GET /Home/Index?aspxerrorpath=/Account/LogOn HTTP/1.1\r\n" +
                //22
                "Host: 202.116.161.73\r\n" +
                        //90
                "User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:65.0) Gecko/20100101 Firefox/65.0\r\n" +
                        //big line >90
                "Cookie: ASP.NET_SessionId=aaaaa; .ASPXAUTH=aaa6A789E0aaFFB8A8007Baaa8Faa7D961aaaa1aaa093D40aaa456C09\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "\r\n"; //none line
        return a.getBytes();
    }
}
