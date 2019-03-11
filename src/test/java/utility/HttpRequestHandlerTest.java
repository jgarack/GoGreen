package utility;

import exceptions.ServerStatusException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.Buffer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class HttpRequestHandlerTest {
    private static final int GET_HTML_LINES = 208;
    private static final int GET_JSON_LINES = 10;
    private static final int POST_LINES = 16;

    /** The default User-Agent to use in HTTP requests. (Chrome 60)**/
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36";
    private static final String GET = "/get";
    private static final String POST = "/post";

    private static final Object POST_OBJ = new Integer(42);

    private static HttpRequestHandler httpHome, httpHandler;

    @BeforeAll
    public static void setUp() throws IOException {
        httpHome = new HttpRequestHandler("http://httpbin.org/get");
        httpHandler = new HttpRequestHandler("http://httpbin.org");
    }

    @Test
    public void getHome_default() {
        try {
            BufferedReader result = httpHome.reqGetHome();
            int i = 0;
            while(result.readLine() != null) {
                ++i;
            }
            assertEquals(GET_HTML_LINES, i, "http body not equal");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void getHome_userAgent() {
        try {
            BufferedReader result = httpHome.reqGetHome(USER_AGENT);
            int i = 0;
            while(result.readLine() != null) {
                ++i;
            }
            assertEquals(GET_HTML_LINES, i, "http body not equal");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void get_route() {
        try {
            BufferedReader result = httpHome.reqGet(GET);
            int i = 0;
            while(result.readLine() != null) {
                ++i;
            }
            assertEquals(GET_JSON_LINES, i, "http body not equal");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void get_route_userAgent() {
        try {
            BufferedReader result = httpHome.reqGet(GET, USER_AGENT);
            int i = 0;
            while(result.readLine() != null) {
                ++i;
            }
            assertEquals(GET_JSON_LINES, i, "http body not equal");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void post_route_msg() {
        try {
            BufferedReader result = httpHome.reqPost(POST, POST_OBJ);
            int i = 0;
            while(result.readLine() != null) {
                ++i;
            }
            assertEquals(POST_LINES, i, "http body not equal");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void post_route_msg_userAgent() {
        try {
            BufferedReader result = httpHome.reqPost(POST, POST_OBJ,
                    USER_AGENT);
            int i = 0;
            while(result.readLine() != null) {
                ++i;
            }
            assertEquals(POST_LINES, i, "http body not equal");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void getNotFound() {
        try {
            assertThrows(ServerStatusException.class,
                    () -> httpHandler.reqGet("/notfound"),
                    "expected status code 404");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void postNotFound() {
        try {
            assertThrows(ServerStatusException.class,
                    () -> httpHandler.reqPost("/notfound", POST_OBJ),
                    "expected status code 404");
        } catch(Exception exception){
            exception.printStackTrace();
            fail(exception.getMessage());
        }
    }
    @Test
    public void resLog() {
        BufferedReader testReader = new BufferedReader(
                new StringReader("hello"));
        assertEquals("hello", httpHandler.resLog(testReader, null));
    }
    @Test
    public void resLogException() throws IOException {
        BufferedReader testReader = new BufferedReader(
                new StringReader("hello"));
        testReader.close();
        ByteArrayOutputStream detectPrint = new ByteArrayOutputStream();
        PrintStream console = System.out;
        System.setOut(new PrintStream(detectPrint));
        httpHandler.resLog(testReader, null);
        System.setOut(console);
        System.out.println(detectPrint.toString());
        System.out.println("IOException occured, check "
                + "filepath\nStream closed");
        //TODO: same Strings not equal?
        assertTrue(detectPrint.toString().equals("IOException occured, check "
                + "filepath\n Stream closed"));
    }
}
