package com.redmondtest.app;

import android.content.ContentValues;
import android.net.Uri;
import android.text.format.Time;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by dds on 21.07.15.
 */
public class SearchEngine {
    private static final String LOG_TAG = SearchEngine.class.getSimpleName();

    private static final String SEARCH_TEXT_BASE_URL = "http://ajax.googleapis.com/ajax/services/search/web?";
    private static final String SEARCH_PICTURES_BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images?";
    private static final String QUERY_PARAM = "q";

    public List<SearchItem> searchText(String query) {
        Uri searchUri = Uri.parse(SEARCH_TEXT_BASE_URL).buildUpon().appendQueryParameter(QUERY_PARAM, query).build();

        String searchJsonStr = search(searchUri);



        return null;
    }

    private String search(Uri uri){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String searchResultStr = null;

        try {
            URL url = new URL(uri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            searchResultStr = buffer.toString();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return searchResultStr;
    }

    private List<SearchItem> getDataFromJson(String jsonStr)
            throws JSONException {

        final String RESPONSE_DATA = "responseData";
        final String RESULTS = "results";
        final String URL = "url";
        final String TITLE = "title";
        final String CONTENT = "content";

        List<SearchItem> searchItems = new ArrayList<SearchItem>();

        try {

            JSONObject searchJson = new JSONObject(jsonStr);
            JSONObject responseJson = searchJson.getJSONObject(RESPONSE_DATA);
            JSONArray resultsArray = searchJson.getJSONArray(RESULTS);

            for(int i = 0; i < resultsArray.length(); i++) {
                JSONObject resultJson = resultsArray.getJSONObject(i);

                String url = resultJson.getString(URL);
                String title = resultJson.getString(TITLE);
                String content = resultJson.getString(CONTENT);

                searchItems.add(new SearchItem(url, title, content));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return searchItems;
    }

}
