package cn.czfshine.jerrymouse.http.http11;

import org.jetbrains.annotations.Nullable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public class HttpResponse implements HttpServletResponse {

    private ServletOutputStream servletOutputStream;
    private int statusCode=500;

    /**
     * Sets the status code for this response.
     *
     * <p>This method is used to set the return status code when there is
     * no error (for example, for the SC_OK or SC_MOVED_TEMPORARILY status
     * codes).
     * <p>This method preserves any cookies and other response headers.
     * @param    sc    the status code
     * @see #sendError
     */
    @Override
    public void setStatus(int sc) {
        this.statusCode=sc;
    }

    private String statusMessage="";
    /**
     * @param    sc    the status code
     * @param    sm    the status message
     * @deprecated As of version 2.1, due to ambiguous meaning of the
     * message parameter. To set a status code
     * use <code>setStatus(int)</code>, to send an error with a description
     * use <code>sendError(int, String)</code>.
     * <p>
     * Sets the status code and message for this response.
     */
    @Override
    public void setStatus(int sc, @Nullable String sm) {
        this.statusCode=sc;
        this.statusMessage=sm;
    }
    private String protocol;


    private String getStatusLine(){
        return protocol +" "+statusCode+" "+statusMessage+" "+"\r\n";
    }

    private boolean commited =false;
    public void commitHead() throws IOException {
        servletOutputStream.print(getStatusLine());
        sendAllHeadLine();
        servletOutputStream.print("\r\n");
    }
    private void sendLine(String line) throws IOException {
        servletOutputStream.print(line);
        servletOutputStream.print("\r\n");
    }
    private HashMap<String,Object> headData=new HashMap<>();
    private void sendAllHeadLine() throws IOException {
        for (String key:headData.keySet()
             ) {
            String line = key+":"+headData.get(key).toString();
            sendLine(line);
        }
    }
    /**
     * Returns a boolean indicating if the response has been
     * committed.  A committed response has already had its status
     * code and headers written.
     *
     * @return a boolean indicating if the response has been
     * committed
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #flushBuffer
     * @see #reset
     */
    @Override
    public boolean isCommitted() {
        //todo
        return false;
    }

    private  long contentLength=0;
    /**
     * Sets the length of the content body in the response
     * In HTTP servlets, this method sets the HTTP Content-Length header.
     *
     * @param len an integer specifying the length of the
     *            content being returned to the client; sets the Content-Length header
     */
    @Override
    public void setContentLength(int len) {
        contentLength=len;
        headData.put("Content-Length",len);
    }

    /**
     * Sets the length of the content body in the response
     * In HTTP servlets, this method sets the HTTP Content-Length header.
     *
     * @param len a long specifying the length of the
     *            content being returned to the client; sets the Content-Length header
     * @since Servlet 3.1
     */
    @Override
    public void setContentLengthLong(long len) {
        contentLength=len;
        headData.put("Content-Length",len);
    }


    private String contentType="";

    /**
     * Sets the content type of the response being sent to
     * the client, if the response has not been committed yet.
     * The given content type may include a character encoding
     * specification, for example, <code>text/html;charset=UTF-8</code>.
     * The response's character encoding is only set from the given
     * content type if this method is called before <code>getWriter</code>
     * is called.
     * <p>This method may be called repeatedly to change content type and
     * character encoding.
     * This method has no effect if called after the response
     * has been committed. It does not set the response's character
     * encoding if it is called after <code>getWriter</code>
     * has been called or after the response has been committed.
     * <p>Containers must communicate the content type and the character
     * encoding used for the servlet response's writer to the client if
     * the protocol provides a way for doing so. In the case of HTTP,
     * the <code>Content-Type</code> header is used.
     *
     * @param type a <code>String</code> specifying the MIME
     *             type of the content
     * @see #setLocale
     * @see #setCharacterEncoding
     * @see #getOutputStream
     * @see #getWriter
     */
    @Override
    public void setContentType(String type) {
        contentType=type;
        headData.put("Content-Type",type);
    }

    /********************************************/

    /**
     * Adds the specified cookie to the response.  This method can be called
     * multiple times to set more than one cookie.
     *
     * @param cookie the Cookie to return to the client
     */
    @Override
    @Deprecated
    public void addCookie(Cookie cookie) {

    }

    /**
     * Returns a boolean indicating whether the named response header
     * has already been set.
     *
     * @param    name    the header name
     * @return        <code>true</code> if the named response header
     * has already been set;
     * <code>false</code> otherwise
     */
    @Override
    @Deprecated
    public boolean containsHeader(String name) {
        return false;
    }

    /**
     * Encodes the specified URL by including the session ID,
     * or, if encoding is not needed, returns the URL unchanged.
     * The implementation of this method includes the logic to
     * determine whether the session ID needs to be encoded in the URL.
     * For example, if the browser supports cookies, or session
     * tracking is turned off, URL encoding is unnecessary.
     *
     * <p>For robust session tracking, all URLs emitted by a servlet
     * should be run through this
     * method.  Otherwise, URL rewriting cannot be used with browsers
     * which do not support cookies.
     *
     * <p>If the URL is relative, it is always relative to the current
     * HttpServletRequest.
     *
     * @throws IllegalArgumentException if the url is not valid
     * @param    url    the url to be encoded.
     * @return the encoded URL if encoding is needed;
     * the unchanged URL otherwise.
     */
    @Override
    @Deprecated
    public String encodeURL(String url) {
        return null;
    }

    /**
     * Encodes the specified URL for use in the
     * <code>sendRedirect</code> method or, if encoding is not needed,
     * returns the URL unchanged.  The implementation of this method
     * includes the logic to determine whether the session ID
     * needs to be encoded in the URL.  For example, if the browser supports
     * cookies, or session tracking is turned off, URL encoding is
     * unnecessary.  Because the rules for making this determination can
     * differ from those used to decide whether to
     * encode a normal link, this method is separated from the
     * <code>encodeURL</code> method.
     *
     * <p>All URLs sent to the <code>HttpServletResponse.sendRedirect</code>
     * method should be run through this method.  Otherwise, URL
     * rewriting cannot be used with browsers which do not support
     * cookies.
     *
     * <p>If the URL is relative, it is always relative to the current
     * HttpServletRequest.
     *
     * @throws IllegalArgumentException if the url is not valid
     * @param    url    the url to be encoded.
     * @return the encoded URL if encoding is needed;
     * the unchanged URL otherwise.
     * @see #sendRedirect
     * @see #encodeUrl
     */
    @Override
    @Deprecated
    public String encodeRedirectURL(String url) {
        return null;
    }

    /**
     * @throws IllegalArgumentException if the url is not valid
     * @deprecated As of version 2.1, use encodeURL(String url) instead
     * @param    url    the url to be encoded.
     * @return the encoded URL if encoding is needed;
     * the unchanged URL otherwise.
     */
    @Override
    @Deprecated
    public String encodeUrl(String url) {
        return null;
    }

    /**
     * @throws IllegalArgumentException if the url is not valid
     * @deprecated As of version 2.1, use
     * encodeRedirectURL(String url) instead
     * @param    url    the url to be encoded.
     * @return the encoded URL if encoding is needed;
     * the unchanged URL otherwise.
     */
    @Override
    @Deprecated
    public String encodeRedirectUrl(String url) {
        return null;
    }

    /**
     * <p>Sends an error response to the client using the specified
     * status and clears the buffer.  The server defaults to creating
     * the response to look like an HTML-formatted server error page
     * containing the specified message, setting the content type to
     * "text/html".  The caller is <strong>not</strong> responsible for
     * escaping or re-encoding the message to ensure it is safe with
     * respect to the current response encoding and content type.  This
     * aspect of safety is the responsibility of the container, as it is
     * generating the error page containing the message.  The server
     * will preserve cookies and may clear or update any headers needed
     * to serve the error page as a valid response.</p>
     *
     * <p>If an error-page declaration has been made for the web
     * application corresponding to the status code passed in, it will
     * be served back in preference to the suggested msg parameter and
     * the msg parameter will be ignored.</p>
     *
     * <p>If the response has already been committed, this method throws
     * an IllegalStateException.
     * After using this method, the response should be considered
     * to be committed and should not be written to.
     *
     * @param    sc    the error status code
     * @param    msg    the descriptive message
     * @exception IOException    If an input or output exception occurs
     * @exception IllegalStateException    If the response was committed
     */
    @Override
    @Deprecated
    public void sendError(int sc, String msg) throws IOException {

    }

    /**
     * Sends an error response to the client using the specified status
     * code and clears the buffer.
     * <p>
     * The server will preserve cookies and may clear or
     * update any headers needed to serve the error page as a valid response.
     * <p>
     * If an error-page declaration has been made for the web application
     * corresponding to the status code passed in, it will be served back
     * the error page
     *
     * <p>If the response has already been committed, this method throws
     * an IllegalStateException.
     * After using this method, the response should be considered
     * to be committed and should not be written to.
     *
     * @param    sc    the error status code
     * @exception IOException    If an input or output exception occurs
     * @exception IllegalStateException    If the response was committed
     * before this method call
     */
    @Override
    @Deprecated
    public void sendError(int sc) throws IOException {

    }

    /**
     * Sends a temporary redirect response to the client using the
     * specified redirect location URL and clears the buffer. The buffer will
     * be replaced with the data set by this method. Calling this method sets the
     * status code to {@link #SC_FOUND} 302 (Found).
     * This method can accept relative URLs;the servlet container must convert
     * the relative URL to an absolute URL
     * before sending the response to the client. If the location is relative
     * without a leading '/' the container interprets it as relative to
     * the current request URI. If the location is relative with a leading
     * '/' the container interprets it as relative to the servlet container root.
     * If the location is relative with two leading '/' the container interprets
     * it as a network-path reference (see
     * <a href="http://www.ietf.org/rfc/rfc3986.txt">
     * RFC 3986: Uniform Resource Identifier (URI): Generic Syntax</a>, section 4.2
     * &quot;Relative Reference&quot;).
     *
     * <p>If the response has already been committed, this method throws
     * an IllegalStateException.
     * After using this method, the response should be considered
     * to be committed and should not be written to.
     *
     * @param        location    the redirect location URL
     * @exception IOException    If an input or output exception occurs
     * @exception IllegalStateException    If the response was committed or
     * if a partial URL is given and cannot be converted into a valid URL
     */
    @Override
    @Deprecated
    public void sendRedirect(String location) throws IOException {

    }

    /**
     * Sets a response header with the given name and
     * date-value.  The date is specified in terms of
     * milliseconds since the epoch.  If the header had already
     * been set, the new value overwrites the previous one.  The
     * <code>containsHeader</code> method can be used to test for the
     * presence of a header before setting its value.
     *
     * @param    name    the name of the header to set
     * @param    date    the assigned date value
     * @see #containsHeader
     * @see #addDateHeader
     */
    @Override
    @Deprecated
    public void setDateHeader(String name, long date) {

    }

    /**
     * Adds a response header with the given name and
     * date-value.  The date is specified in terms of
     * milliseconds since the epoch.  This method allows response headers
     * to have multiple values.
     *
     * @param    name    the name of the header to set
     * @param    date    the additional date value
     * @see #setDateHeader
     */
    @Override
    @Deprecated
    public void addDateHeader(String name, long date) {

    }

    /**
     * Sets a response header with the given name and value.
     * If the header had already been set, the new value overwrites the
     * previous one.  The <code>containsHeader</code> method can be
     * used to test for the presence of a header before setting its
     * value.
     *
     * @param    name    the name of the header
     * @param    value    the header value  If it contains octet string,
     * it should be encoded according to RFC 2047
     * (http://www.ietf.org/rfc/rfc2047.txt)
     * @see #containsHeader
     * @see #addHeader
     */
    @Override
    @Deprecated
    public void setHeader(String name, String value) {

    }

    /**
     * Adds a response header with the given name and value.
     * This method allows response headers to have multiple values.
     *
     * @param    name    the name of the header
     * @param    value    the additional header value   If it contains
     * octet string, it should be encoded
     * according to RFC 2047
     * (http://www.ietf.org/rfc/rfc2047.txt)
     * @see #setHeader
     */
    @Override
    @Deprecated
    public void addHeader(String name, String value) {

    }

    /**
     * Sets a response header with the given name and
     * integer value.  If the header had already been set, the new value
     * overwrites the previous one.  The <code>containsHeader</code>
     * method can be used to test for the presence of a header before
     * setting its value.
     *
     * @param    name    the name of the header
     * @param    value    the assigned integer value
     * @see #containsHeader
     * @see #addIntHeader
     */
    @Override
    @Deprecated
    public void setIntHeader(String name, int value) {

    }

    /**
     * Adds a response header with the given name and
     * integer value.  This method allows response headers to have multiple
     * values.
     *
     * @param    name    the name of the header
     * @param    value    the assigned integer value
     * @see #setIntHeader
     */
    @Override
    @Deprecated
    public void addIntHeader(String name, int value) {

    }



    /**
     * Gets the current status code of this response.
     *
     * @return the current status code of this response
     * @since Servlet 3.0
     */
    @Override
    @Deprecated
    public int getStatus() {
        return 0;
    }

    /**
     * Gets the value of the response header with the given name.
     *
     * <p>If a response header with the given name exists and contains
     * multiple values, the value that was added first will be returned.
     *
     * <p>This method considers only response headers set or added via
     * {@link #setHeader}, {@link #addHeader}, {@link #setDateHeader},
     * {@link #addDateHeader}, {@link #setIntHeader}, or
     * {@link #addIntHeader}, respectively.
     *
     * @param name the name of the response header whose value to return
     * @return the value of the response header with the given name,
     * or <tt>null</tt> if no header with the given name has been set
     * on this response
     * @since Servlet 3.0
     */
    @Override
    @Deprecated
    public String getHeader(String name) {
        return null;
    }

    /**
     * Gets the values of the response header with the given name.
     *
     * <p>This method considers only response headers set or added via
     * {@link #setHeader}, {@link #addHeader}, {@link #setDateHeader},
     * {@link #addDateHeader}, {@link #setIntHeader}, or
     * {@link #addIntHeader}, respectively.
     *
     * <p>Any changes to the returned <code>Collection</code> must not
     * affect this <code>HttpServletResponse</code>.
     *
     * @param name the name of the response header whose values to return
     * @return a (possibly empty) <code>Collection</code> of the values
     * of the response header with the given name
     * @since Servlet 3.0
     */
    @Override
    @Deprecated
    public Collection<String> getHeaders(String name) {
        return null;
    }

    /**
     * Gets the names of the headers of this response.
     *
     * <p>This method considers only response headers set or added via
     * {@link #setHeader}, {@link #addHeader}, {@link #setDateHeader},
     * {@link #addDateHeader}, {@link #setIntHeader}, or
     * {@link #addIntHeader}, respectively.
     *
     * <p>Any changes to the returned <code>Collection</code> must not
     * affect this <code>HttpServletResponse</code>.
     *
     * @return a (possibly empty) <code>Collection</code> of the names
     * of the headers of this response
     * @since Servlet 3.0
     */
    @Override
    @Deprecated
    public Collection<String> getHeaderNames() {
        return null;
    }

    /**
     * Returns the name of the character encoding (MIME charset)
     * used for the body sent in this response.
     * The following methods for specifying the response character encoding are
     * consulted, in decreasing order of priority: per request, perweb-app
     * (using {@link ServletContext#setResponseCharacterEncoding}, deployment
     * descriptor), and per container (for all web applications deployed in
     * that container, using vendor specific configuration).
     * The first one of these methods that yields a result is returned.
     * Per-request, the charset for the response can be specified explicitly
     * using the {@link setCharacterEncoding} and {@link setContentType}
     * methods, or implicitly using the setLocale(java.util.Locale) method.
     * Explicit specifications take precedence over implicit specifications.
     * Calls made to these methods after <code>getWriter</code> has been
     * called or after the response has been committed have no
     * effect on the character encoding. If no character encoding
     * has been specified, <code>ISO-8859-1</code> is returned.
     * <p>See RFC 2047 (http://www.ietf.org/rfc/rfc2047.txt)
     * for more information about character encoding and MIME.
     *
     * @return a <code>String</code> specifying the name of
     * the character encoding, for example, <code>UTF-8</code>
     */
    @Override
    @Deprecated
    public String getCharacterEncoding() {
        return null;
    }

    /**
     * Returns the content type used for the MIME body
     * sent in this response. The content type proper must
     * have been specified using {@link #setContentType}
     * before the response is committed. If no content type
     * has been specified, this method returns null.
     * If a content type has been specified, and a
     * character encoding has been explicitly or implicitly
     * specified as described in {@link #getCharacterEncoding}
     * or {@link #getWriter} has been called,
     * the charset parameter is included in the string returned.
     * If no character encoding has been specified, the
     * charset parameter is omitted.
     *
     * @return a <code>String</code> specifying the content type,
     * for example, <code>text/html; charset=UTF-8</code>, or null
     * @since Servlet 2.4
     */
    @Override
    @Deprecated
    public String getContentType() {
        return null;
    }

    /**
     * Returns a {@link ServletOutputStream} suitable for writing binary
     * data in the response. The servlet container does not encode the
     * binary data.
     *
     * <p> Calling flush() on the ServletOutputStream commits the response.
     * <p>
     * Either this method or {@link #getWriter} may
     * be called to write the body, not both, except when {@link #reset}
     * has been called.
     *
     * @return a {@link ServletOutputStream} for writing binary data
     * @throws IllegalStateException if the <code>getWriter</code> method
     *                               has been called on this response
     * @throws IOException           if an input or output exception occurred
     * @see #getWriter
     * @see #reset
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        commitHead();
        return servletOutputStream;
    }

    /**
     * Returns a <code>PrintWriter</code> object that
     * can send character text to the client.
     * The <code>PrintWriter</code> uses the character
     * encoding returned by {@link #getCharacterEncoding}.
     * If the response's character encoding has not been
     * specified as described in <code>getCharacterEncoding</code>
     * (i.e., the method just returns the default value
     * <code>ISO-8859-1</code>), <code>getWriter</code>
     * updates it to <code>ISO-8859-1</code>.
     * <p>Calling flush() on the <code>PrintWriter</code>
     * commits the response.
     * <p>Either this method or {@link #getOutputStream} may be called
     * to write the body, not both, except when {@link #reset}
     * has been called.
     *
     * @return a <code>PrintWriter</code> object that
     * can return character data to the client
     * @throws UnsupportedEncodingException if the character encoding returned
     *                                      by <code>getCharacterEncoding</code> cannot be used
     * @throws IllegalStateException        if the <code>getOutputStream</code>
     *                                      method has already been called for this response object
     * @throws IOException                  if an input or output exception occurred
     * @see #getOutputStream
     * @see #setCharacterEncoding
     * @see #reset
     */
    @Override
    @Deprecated
    public PrintWriter getWriter() throws IOException {
        return null;
    }

    /**
     * Sets the character encoding (MIME charset) of the response
     * being sent to the client, for example, to UTF-8.
     * If the response character encoding has already been set by the
     * {@link ServletContext#setResponseCharacterEncoding},
     * deployment descriptor, or using the setContentType() or setLocale()
     * methods, the value set in this method overrides any of those values.
     * Calling {@link #setContentType} with the <code>String</code>
     * of <code>text/html</code> and calling
     * this method with the <code>String</code> of <code>UTF-8</code>
     * is equivalent with calling
     * <code>setContentType</code> with the <code>String</code> of
     * <code>text/html; charset=UTF-8</code>.
     * <p>This method can be called repeatedly to change the character
     * encoding.
     * This method has no effect if it is called after
     * <code>getWriter</code> has been
     * called or after the response has been committed.
     * <p>Containers must communicate the character encoding used for
     * the servlet response's writer to the client if the protocol
     * provides a way for doing so. In the case of HTTP, the character
     * encoding is communicated as part of the <code>Content-Type</code>
     * header for text media types. Note that the character encoding
     * cannot be communicated via HTTP headers if the servlet does not
     * specify a content type; however, it is still used to encode text
     * written via the servlet response's writer.
     *
     * @param charset a String specifying only the character set
     *                defined by IANA Character Sets
     *                (http://www.iana.org/assignments/character-sets)
     * @see #setContentType
     * @see #setLocale
     * @since Servlet 2.4
     */
    @Override
    @Deprecated
    public void setCharacterEncoding(String charset) {

    }


    /**
     * Sets the preferred buffer size for the body of the response.
     * The servlet container will use a buffer at least as large as
     * the size requested.  The actual buffer size used can be found
     * using <code>getBufferSize</code>.
     *
     * <p>A larger buffer allows more content to be written before anything is
     * actually sent, thus providing the servlet with more time to set
     * appropriate status codes and headers.  A smaller buffer decreases
     * server memory load and allows the client to start receiving data more
     * quickly.
     *
     * <p>This method must be called before any response body content is
     * written; if content has been written or the response object has
     * been committed, this method throws an
     * <code>IllegalStateException</code>.
     *
     * @param size the preferred buffer size
     * @throws IllegalStateException if this method is called after
     *                               content has been written
     * @see #getBufferSize
     * @see #flushBuffer
     * @see #isCommitted
     * @see #reset
     */
    @Override
    @Deprecated
    public void setBufferSize(int size) {

    }

    /**
     * Returns the actual buffer size used for the response.  If no buffering
     * is used, this method returns 0.
     *
     * @return the actual buffer size used
     * @see #setBufferSize
     * @see #flushBuffer
     * @see #isCommitted
     * @see #reset
     */
    @Override
    @Deprecated
    public int getBufferSize() {
        return 0;
    }

    /**
     * Forces any content in the buffer to be written to the client.  A call
     * to this method automatically commits the response, meaning the status
     * code and headers will be written.
     *
     * @throws IOException if the act of flushing the buffer cannot be
     *                     completed.
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #isCommitted
     * @see #reset
     */
    @Override
    @Deprecated
    public void flushBuffer() throws IOException {

    }

    /**
     * Clears the content of the underlying buffer in the response without
     * clearing headers or status code. If the
     * response has been committed, this method throws an
     * <code>IllegalStateException</code>.
     *
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #isCommitted
     * @see #reset
     * @since Servlet 2.3
     */
    @Override
    @Deprecated
    public void resetBuffer() {

    }


    /**
     * Clears any data that exists in the buffer as well as the status code,
     * headers.  The state of calling {@link #getWriter} or
     * {@link #getOutputStream} is also cleared.  It is legal, for instance,
     * to call {@link #getWriter}, {@link #reset} and then
     * {@link #getOutputStream}.  If {@link #getWriter} or
     * {@link #getOutputStream} have been called before this method,
     * then the corrresponding returned Writer or OutputStream will be
     * staled and the behavior of using the stale object is undefined.
     * If the response has been committed, this method throws an
     * <code>IllegalStateException</code>.
     *
     * @throws IllegalStateException if the response has already been
     *                               committed
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #flushBuffer
     * @see #isCommitted
     */
    @Override
    @Deprecated
    public void reset() {

    }

    /**
     * Sets the locale of the response, if the response has not been
     * committed yet. It also sets the response's character encoding
     * appropriately for the locale, if the character encoding has not
     * been explicitly set using {@link #setContentType} or
     * {@link #setCharacterEncoding}, <code>getWriter</code> hasn't
     * been called yet, and the response hasn't been committed yet.
     * If the deployment descriptor contains a
     * <code>locale-encoding-mapping-list</code> element, and that
     * element provides a mapping for the given locale, that mapping
     * is used. Otherwise, the mapping from locale to character
     * encoding is container dependent.
     * <p>This method may be called repeatedly to change locale and
     * character encoding. The method has no effect if called after the
     * response has been committed. It does not set the response's
     * character encoding if it is called after {@link #setContentType}
     * has been called with a charset specification, after
     * {@link #setCharacterEncoding} has been called, after
     * <code>getWriter</code> has been called, or after the response
     * has been committed.
     * <p>Containers must communicate the locale and the character encoding
     * used for the servlet response's writer to the client if the protocol
     * provides a way for doing so. In the case of HTTP, the locale is
     * communicated via the <code>Content-Language</code> header,
     * the character encoding as part of the <code>Content-Type</code>
     * header for text media types. Note that the character encoding
     * cannot be communicated via HTTP headers if the servlet does not
     * specify a content type; however, it is still used to encode text
     * written via the servlet response's writer.
     *
     * @param loc the locale of the response
     * @see #getLocale
     * @see #setContentType
     * @see #setCharacterEncoding
     */
    @Override
    @Deprecated
    public void setLocale(Locale loc) {

    }

    /**
     * Returns the locale specified for this response
     * using the {@link #setLocale} method. Calls made to
     * <code>setLocale</code> after the response is committed
     * have no effect. If no locale has been specified,
     * the container's default locale is returned.
     *
     * @return the Locale for this response.
     * @see #setLocale
     */
    @Override
    @Deprecated
    public Locale getLocale() {
        return null;
    }


    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setServletOutputStream(ServletOutputStream servletOutputStream) {
        this.servletOutputStream = servletOutputStream;
    }
}
