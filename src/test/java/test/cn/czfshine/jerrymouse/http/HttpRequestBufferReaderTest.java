package test.cn.czfshine.jerrymouse.http;

import cn.czfshine.jerrymouse.http.HttpRequestBufferReader;
import cn.czfshine.jerrymouse.http.http11.Http11LineParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.cn.czfshine.jerrymouse.http.mock.HttpRequestMockData;

import java.nio.ByteBuffer;

/**
 * HttpRequestBufferReader Tester.
 *
 * @author czfshine
 * @version 1.0
 * @since <pre>May 18, 2019</pre>
 */
public class HttpRequestBufferReaderTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: read(ByteBuffer buffer)
     */
    @Test
    public void testReadBuffer() throws Exception {
        byte[] simple = HttpRequestMockData.getSimpleNoBodyLineGet();
        ByteBuffer bb = ByteBuffer.allocate(128);
        Http11LineParser httpLineParser = new Http11LineParser();
        HttpRequestBufferReader httpRequestBufferReader = new HttpRequestBufferReader(httpLineParser);


        // 1 two line
        // write
        int simplepos=0;
        bb.put(simple,simplepos,128);
        simplepos+=128;
        bb.flip();
        // 1 read
        int res1 = httpRequestBufferReader.read(bb);
        //1 test 55+22 == 77
        Assert.assertEquals(77,res1);

        //2 one line
        // write
        bb.put(simple,simplepos,res1);
        simplepos+=res1;
        bb.flip();
        //2 read
        int res2 = httpRequestBufferReader.read(bb);
        // 2 test
        Assert.assertEquals(90,res2);

        // 3 big line
        bb.put(simple,simplepos,res2);
        simplepos+=res2;
        bb.flip();

        //todo ?nio 使用buffer读太复杂了..
        //Assert.fail();
    }

    /**
     * Method: isDone()
     */
    @Test
    public void testIsDone() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRequest()
     */
    @Test
    public void testGetRequest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: clean()
     */
    @Test
    public void testClean() throws Exception {
//TODO: Test goes here...

    }


    /**
     * Method: init()
     */
    @Test
    public void testInit() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = HttpRequestBufferReader.getClass().getMethod("init");
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    @Test
    public void readArray() {
    }
}
