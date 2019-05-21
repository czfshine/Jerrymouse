package test.cn.czfshine.jerrymouse.http; 

import cn.czfshine.jerrymouse.http.HTTPVersionNotSupportedException;
import cn.czfshine.jerrymouse.http.HttpRequestStreamReader;
import cn.czfshine.jerrymouse.http.MethodNotAllowedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import test.cn.czfshine.jerrymouse.http.mock.HttpRequestMockData;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static test.cn.czfshine.jerrymouse.http.mock.HttpRequestMockData.getSimplePost;

/** 
* HttpRequestStreamReader Tester. 
* 
* @author <Authors name> 
* @since <pre>May 21, 2019</pre> 
* @version 1.0 
*/ 
public class HttpRequestStreamReaderTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: isHeadDone() 
* 
*/ 
@Test
public void testIsHeadDone() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: waitHeadDone() 
* 
*/ 
@Test
public void testWaitHeadDone() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRequest() 
* 
*/ 
@Test
public void testGetRequest() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: checkRequestLine() 
* 
*/ 
@Test
public void testCheckRequestLine() {

    byte[] simpleNoBodyLineGet = HttpRequestMockData.getSimpleNoBodyLineGet();
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(simpleNoBodyLineGet);
    try {
        new HttpRequestStreamReader(byteArrayInputStream);
    } catch (Exception e) {
        Assert.fail("get supported");
    }
    try {
        new HttpRequestStreamReader(new ByteArrayInputStream(getSimplePost()));
    } catch (Exception e) {
        Assert.assertTrue(e instanceof MethodNotAllowedException);
    }

} 

/** 
* 
* Method: parserAllHead() 
* 
*/ 
@Test
public void testParserAllHead() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = HttpRequestStreamReader.getClass().getMethod("parserAllHead"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
