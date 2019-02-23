package client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public abstract class HttpRequestHandler {
    private final static String defUserAgent = "Mozilla/5.0";
    private final static String home = "http://localhost:8080";

    /**Sends an HTTP GET request to the server which requests the home page of the default domain
     * Default User-Agent
     * @author awjvanvugt
     * @return BufferedReader containing the HTTP response from the server
     * @throws Exception at readRes
     */
    public static BufferedReader reqGetHome() throws Exception{
        return reqGet(home, defUserAgent);
    }

    /**Sends an HTTP GET request to the server which requests the home page of the default domain
     * @author awjvanvugt
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response from the server
     * @throws Exception at readRes
     */
    public static BufferedReader reqGetHome(String userAgent) throws Exception{
        return reqGet(home, userAgent);
    }

    /**Sends an HTTP GET request to the server which requests the specified page
     * Default User-Agent
     * @author awjvanvugt
     * @param url URL for the GET request
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqGet(String url) throws Exception{
        return reqGet(url, defUserAgent);
    }

    /**Sends an HTTP GET request to the server which requests the specified page
     * @author awjvanvugt
     * @param url URL for the GET request
     * @param userAgent the User-Agent to use
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqGet(String url, String userAgent) throws Exception {
        URL inputUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) inputUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);

        return readRes(con);
    }

    /**Sends an HTTP POST request to the server on the specified page
     * Default User-Agent
     * @author awjvanvugt
     * @param url URL for the GET request
     * @param message the Object to POST
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqPost(String url, Object message) throws Exception {
        return reqPost(url, message, defUserAgent);
    }

    /**Sends an HTTP POST request to the server on the specified page
     * @author awjvanvugt
     * @param url URL for the GET request
     * @param message the Object to POST
     * @param userAgent
     * @return BufferedReader containing the HTTP response
     * @throws Exception at readRes
     */
    public static BufferedReader reqPost(String url, Object message, String userAgent) throws Exception{
        URL inputUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) inputUrl.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", userAgent);
        con.setDoOutput(true);
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
     * @throws Exception If the status code is other than HTTP OK (200)
     */
    private static BufferedReader readRes(HttpURLConnection con) throws Exception{
        int responsecode = con.getResponseCode();
        if(responsecode == HttpURLConnection.HTTP_OK){
            return new BufferedReader(new InputStreamReader(con.getInputStream()));
        }
        throw new Exception("Unexpected response from server: "+responsecode);
    }

    /** Creates or replaces a .txt file at the specified path with the text in the BufferedReader
     * @author awjvanvugt
     * @param message the BufferedReader of which the content should be logged
     * @param filepath the path to the file
     */
    public static void resLog(BufferedReader message, String filepath){
        try {
            FileWriter log = new FileWriter(new File(filepath));
            String line;
            StringBuilder logtxt = new StringBuilder();
            while((line = message.readLine()) != null){
                logtxt.append(line);
            }
            log.write(logtxt.toString());
        }catch(IOException ioe){
            System.out.println("IOException occured, check filepath\n"+ioe.getMessage());
            ioe.printStackTrace();
        }
    }
}
