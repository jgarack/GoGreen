package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ServerStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/** Handler for HTTP requests.
 * Returns a BufferedReader with the response from the server
 * @author awjvanvugt
 */
public class HttpRequestHandler {
    //private final static Logger LOGGER = Logger
    // .getLogger(HttpRequestHandler.class.getName());
    /** The default User-Agent to use in HTTP requests. (Chrome 60)**/
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT "
        + "10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
        + "Chrome/60.0.3112.113 Safari/537.36";
    /** The default url to use in HTTP requests. **/
    private String domain;

    /**
     * Class Constructor for domain.
     * @param host domain to contact
     */
    public HttpRequestHandler(final String host) {
        domain = host;
    }

    /**Sends an HTTP GET request to the server which requests the home
     * page of the default domain.
     * Default User-Agent
     * @author awjvanvugt
     * @return BufferedReader containing the HTTP response from the server
     * @throws ServerStatusException readRes
     * @throws IOException readRes
     */
    public BufferedReader reqGetHome() throws ServerStatusException,
            IOException {
        return reqGet("/", USER_AGENT);
    }

    /**Sends an HTTP GET request to the server which requests the home page
     * of the default domain.
     * @author awjvanvugt
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response from the server
     * @throws ServerStatusException at readRes
     * @throws IOException readRes
     */
    public BufferedReader reqGetHome(final String userAgent)
            throws ServerStatusException, IOException {
        return reqGet("/", userAgent);
    }

    /**Sends an HTTP GET request to the server which requests the specified
     * page.
     * Default User-Agent
     * @author awjvanvugt
     * @param route URL addition for the GET request
     * @return BufferedReader containing the HTTP response
     * @throws ServerStatusException at readRes
     * @throws IOException readRes
     */
    public BufferedReader reqGet(final String route) throws
            ServerStatusException, IOException {
        System.out.println("first reqGet");
        return reqGet(route, USER_AGENT);
    }

    /**Sends an HTTP GET request to the server which requests the specified
     * page.
     * @author awjvanvugt
     * @param route URL addition for the GET request
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response
     * @throws ServerStatusException at readRes
     * @throws IOException readRes
     */
    public BufferedReader reqGet(final String route,
                                final String userAgent)
                                throws ServerStatusException, IOException {
        System.out.println("reqGet");
        URL inputUrl = new URL(domain + route);
        HttpURLConnection con = (HttpURLConnection) inputUrl.openConnection();
        System.out.println(con);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);
        System.out.println(inputUrl);
        return readRes(con);
    }

    /**Sends an HTTP POST request to the server on the specified page.
     * Default User-Agent
     * @author awjvanvugt
     * @param route URL addition for the GET request
     * @param message the Object to POST
     * @return BufferedReader containing the HTTP response
     * @throws ServerStatusException at readRes
     * @throws IOException readRes
     */
    public BufferedReader reqPost(final String route, final Object message)
            throws ServerStatusException, IOException {
        return reqPost(route, message, USER_AGENT);
    }

    /**Sends an HTTP POST request to the server on the specified page.
     * @author awjvanvugt
     * @param route URL addition for the GET request
     * @param message the Object to POST
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response
     * @throws ServerStatusException at readRes
     * @throws IOException readRes
     */
    public BufferedReader reqPost(final String route, final Object message,
             final String userAgent) throws ServerStatusException, IOException {
        System.out.println(route);
        URL inputUrl = new URL(domain + route);
        HttpURLConnection con = (HttpURLConnection) inputUrl.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", userAgent);
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream out = con.getOutputStream();
        ObjectMapper parser = new ObjectMapper();
        out.write(parser.writeValueAsString(message).getBytes());
        out.flush();
        out.close();

        return readRes(con);

    }

    /** Private helper method to read the response from the server.
     * @author awjvanvugt
     * @param con connection to the server
     * @return BufferedReader containing the response from the server
     * @throws ServerStatusException If the status code is other than HTTP OK (200)
     * @throws IOException at readRes
     */
    private BufferedReader readRes(final HttpURLConnection con)
            throws ServerStatusException, IOException {
        int responsecode = con.getResponseCode();
        if (responsecode == HttpURLConnection.HTTP_OK) {
            return new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
        }

        throw new ServerStatusException(con.getURL().toString(), responsecode);
    }

    /** Creates or replaces a .txt file at the specified path with the text
     * in the BufferedReader.
     * @author awjvanvugt
     * @param message the BufferedReader of which the content should be logged
     * @param filepath the path to the file
     * @return a String object containing the response
     */
    public static String resLog(final BufferedReader message,
                                final String filepath) {
        try {
            String line;
            StringBuilder logtxt = new StringBuilder();
            System.out.println(message);
            while ((line = message.readLine()) != null) {
                logtxt.append(line);
            }
            String result = logtxt.toString();
            System.out.println(result);
            /*handler.publish(new LogRecord(Level.FINE, result));*/
            return result;
        } catch (IOException ioe) {
            System.out.println("IOException occured, check filepath\n"
                    + ioe.getMessage());
            ioe.printStackTrace();
            return "Failed to create log.";
        }
    }
}
