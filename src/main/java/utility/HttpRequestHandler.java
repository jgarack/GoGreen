package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ServerStatusException;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** Handler for HTTP requests.
 * Returns a BufferedReader with the response from the server
 * @author awjvanvugt
 */
public abstract class HttpRequestHandler {
    //private final static Logger LOGGER = Logger
    // .getLogger(HttpRequestHandler.class.getName());
    /** The default User-Agent to use in HTTP requests.**/
    private static final String USER_AGENT_MOZILLA = "Mozilla/5.0";
    /** The default url to use in HTTP requests. **/
    private static final String DOMAIN_INDEX = "http://localhost:8080";
    /**Sends an HTTP GET request to the server which requests the home
     * page of the default domain.
     * Default User-Agent
     * @author awjvanvugt
     * @return BufferedReader containing the HTTP response from the server
     * @throws Exception at readRes
     */
    public static BufferedReader reqGetHome() throws ServerStatusException,
            IOException {
        return reqGet(DOMAIN_INDEX, USER_AGENT_MOZILLA);
    }

    /**Sends an HTTP GET request to the server which requests the home page
     * of the default domain.
     * @author awjvanvugt
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response from the server
     * @throws Exception at readRes
     */
    public static BufferedReader reqGetHome(final String userAgent)
            throws ServerStatusException, IOException {
        return reqGet(DOMAIN_INDEX, userAgent);
    }

    /**Sends an HTTP GET request to the server which requests the specified
     * page.
     * Default User-Agent
     * @author awjvanvugt
     * @param url URL for the GET request
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqGet(final String url) throws
            ServerStatusException, IOException {
        return reqGet(url, USER_AGENT_MOZILLA);
    }

    /**Sends an HTTP GET request to the server which requests the specified
     * page.
     * @author awjvanvugt
     * @param url URL for the GET request
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqGet(final String url,
                                final String userAgent)
                                throws ServerStatusException, IOException {
        URL inputUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) inputUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);

        return readRes(con);
    }

    /**Sends an HTTP POST request to the server on the specified page.
     * Default User-Agent
     * @author awjvanvugt
     * @param url URL for the GET request
     * @param message the Object to POST
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqPost(final String url, final Object message)
            throws ServerStatusException, IOException {
        return reqPost(url, message, USER_AGENT_MOZILLA);
    }

    /**Sends an HTTP POST request to the server on the specified page.
     * @author awjvanvugt
     * @param url URL for the GET request
     * @param message the Object to POST
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqPost(final String url, final Object message,
             final String userAgent) throws ServerStatusException, IOException {

        URL inputUrl = new URL(url);
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
        //TODO: clean up exceptions
    }

    /** Private helper method to read the response from the server.
     * @author awjvanvugt
     * @param con connection to the server
     * @return BufferedReader containing the response from the server
     * @throws Exception If the status code is other than HTTP OK (200)
     */
    private static BufferedReader readRes(final HttpURLConnection con)
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
            //TODO: Implement logging to file
            /*
            File log = new File(filepath);
            log.mkdirs();
            FileHandler handler = new FileHandler(filepath);
            */
            String line;
            StringBuilder logtxt = new StringBuilder();
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
