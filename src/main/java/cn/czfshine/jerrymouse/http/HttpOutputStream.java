
package cn.czfshine.jerrymouse.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 代理从socket拿出来的outputstream
 *
 */
public class HttpOutputStream extends ServletOutputStream {

    private BufferedOutputStream bufferedOutputStream;

    public HttpOutputStream(OutputStream os) {
        this.bufferedOutputStream = new BufferedOutputStream(os);
    }

    @Override
    public void write(int b) throws IOException {
        bufferedOutputStream.write(b);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
        bufferedOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
        bufferedOutputStream.close();
    }

    /**
     * This method can be used to determine if data can be written without blocking.
     *
     * @return <code>true</code> if a write to this <code>ServletOutputStream</code>
     * will succeed, otherwise returns <code>false</code>.
     * @since Servlet 3.1
     */
    @Override
    @Deprecated
    public boolean isReady() {
        return false;
    }

    /**
     * Instructs the <code>ServletOutputStream</code> to invoke the provided
     * {@link WriteListener} when it is possible to write
     *
     * @param writeListener the {@link WriteListener} that should be notified
     *                      when it's possible to write
     * @throws IllegalStateException if one of the following conditions is true
     *                               <ul>
     *                               <li>the associated request is neither upgraded nor the async started
     *                               <li>setWriteListener is called more than once within the scope of the same request.
     *                               </ul>
     * @throws NullPointerException  if writeListener is null
     * @since Servlet 3.1
     */
    @Override
    @Deprecated
    public void setWriteListener(WriteListener writeListener) {

    }
}
