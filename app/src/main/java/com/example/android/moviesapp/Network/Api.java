package com.example.android.moviesapp.Network;

import android.net.Uri;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Api {
    final static String BASE_URL="http://api.themoviedb.org/3/movie/";

    final static String PARAM_API_KEY="api_key";

    final static String API_KEY="45a6d4800ed1cb6aa715e07c245e6119";
    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w185" ;




    static final String PUPULAR_MOVIES="popular";
    static final String TOP_RATED="top_rated";

    public String PopularUrl="http://api.themoviedb.org/3/movie/popular?api_key=45a6d4800ed1cb6aa715e07c245e6119";
    public  String TopRated="http://api.themoviedb.org/3/movie/top_rated?api_key=45a6d4800ed1cb6aa715e07c245e6119";







    public static URL build_url(String path)
    {

        Uri builtUri=Uri.parse(BASE_URL).buildUpon().appendEncodedPath(path).
                appendQueryParameter(PARAM_API_KEY,API_KEY).build();

        URL url=null;

        try {
            url=new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    public static URL buildPosterUrl(String posterPath) {
        Uri builtUri = Uri.parse(IMAGE_URL).buildUpon()
                .appendEncodedPath(posterPath)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

}