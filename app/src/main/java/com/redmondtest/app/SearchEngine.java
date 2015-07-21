package com.redmondtest.app;

import android.net.Uri;

import java.util.List;

/**
 * Created by dds on 21.07.15.
 */
public class SearchEngine {

    private static final String SEARCH_TEXT_BASE_URL = "http://ajax.googleapis.com/ajax/services/search/web?";
    private static final String SEARCH_PICTURES_BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images?";
    private static final String QUERY_PARAM = "q";

    public List<String> searchText(String query){
        Uri searchUri = Uri.parse(SEARCH_TEXT_BASE_URL).buildUpon().appendQueryParameter(QUERY_PARAM, query).build();

        return null;

    }


}
