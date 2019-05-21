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
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    public static byte[] getSimplePost(){
//        //文本形式的
//        String source="POST /Home/LogOn HTTP/1.1\n" +
//                "Host: 202.116.161.73\n" +
//                "User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:65.0) Gecko/20100101 Firefox/65.0\n" +
//                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n" +
//                "Accept-Language: en-US,en;q=0.5\n" +
//                "Accept-Encoding: gzip, deflate\n" +
//                "Referer: http://202.116.161.73/Home/LogOn\n" +
//                "Content-Type: application/x-www-form-urlencoded\n" +
//                "Content-Length: 77\n" +
//                "Connection: keep-alive\n" +
//                "Cookie: ASP.NET_SessionId=cuinbxncidtdb4yp2clb2p3y\n" +
//                "Upgrade-Insecure-Requests: 1\n" +
//                "\n" +
//                "UserNumber=201625010204&Password=aaaabf06aaaa8e59cc0ff60d361e7336&vCode=75582";
        String s="504f5354202f486f6d652f4c6f674f6e20485454502f312e310d0a486f73743a203230322e3131362e3136312e37330d0a557365722d4167656e743a204d6f7a696c6c612f352e3020285831313b205562756e74753b204c696e7578207838365f36343b2072763a36352e3029204765636b6f2f32303130303130312046697265666f782f36352e300d0a4163636570743a20746578742f68746d6c2c6170706c69636174696f6e2f7868746d6c2b786d6c2c6170706c69636174696f6e2f786d6c3b713d302e392c696d6167652f776562702c2a2f2a3b713d302e380d0a4163636570742d4c616e67756167653a20656e2d55532c656e3b713d302e350d0a4163636570742d456e636f64696e673a20677a69702c206465666c6174650d0a526566657265723a20687474703a2f2f3230322e3131362e3136312e37332f486f6d652f4c6f674f6e0d0a436f6e74656e742d547970653a206170706c69636174696f6e2f782d7777772d666f726d2d75726c656e636f6465640d0a436f6e74656e742d4c656e6774683a2037370d0a436f6e6e656374696f6e3a206b6565702d616c6976650d0a436f6f6b69653a204153502e4e45545f53657373696f6e49643d6375696e62786e63696474646234797032636c62327033790d0a557067726164652d496e7365637572652d52657175657374733a20310d0a0d0a";
        String body="557365724e756d6265723d3230313632353031303230342650617373776f72643d66636366626630363433643838653539636330666636306433363165343434342676436f64653d3735353832";
        return toBytes(s+body);
    }
}
