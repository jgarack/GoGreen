package client;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpRequestHandler {
    private final static String defUserAgent = "";
    private final static String home = "";

    public static BufferedReader reqGetHome(){
        return reqGet(home, defUserAgent);
    }

    public static BufferedReader reqGet(String url){
        return reqGet(url, defUserAgent);
    }

    public static BufferedReader reqGet(String url, String userAgent) throws Exception {
        URL inputUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) inputUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);
    }
}
