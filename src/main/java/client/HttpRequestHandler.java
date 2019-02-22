package client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpRequestHandler {
    private final static String defUserAgent = "Mozilla/5.0";
    private final static String home = "http://localhost:8080";

    public static BufferedReader reqGetHome() throws Exception{
        return reqGet(home, defUserAgent);
    }

    public static BufferedReader reqGet(String url) throws Exception{
        return reqGet(url, defUserAgent);
    }

    public static BufferedReader reqGet(String url, String userAgent) throws Exception {
        URL inputUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) inputUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);

        return readRes(con);
    }

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

    private static BufferedReader readRes(HttpURLConnection con) throws Exception{
        int responsecode = con.getResponseCode();
        if(responsecode == HttpURLConnection.HTTP_OK){
            return new BufferedReader(new InputStreamReader(con.getInputStream()));
        }
        throw new Exception(""+responsecode);
    }
}
