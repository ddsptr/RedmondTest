package com.redmondtest.app;

import android.os.AsyncTask;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by dds on 21.07.15.
 */
public class FetchSearchTask extends AsyncTask<String, Object, Void> {
    private final String LOG_TAG = FetchSearchTask.class.getSimpleName();

    private SearchViewAdapter mSearchAdapter;
    private SearchEngine mSearchEngine;

    public FetchSearchTask(SearchViewAdapter searchAdapter, SearchEngine mSearchEngine) {
        this.mSearchAdapter = searchAdapter;
        this.mSearchEngine = mSearchEngine;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        mSearchAdapter.addItem((SearchItem) values[0]);
    }

    @Override
    protected Void doInBackground(String... params) {

        //TODO switch

        List<SearchItem> searchItems = mSearchEngine.searchText(params[0]);

        for (SearchItem searchItem : searchItems) {
            publishProgress(searchItem);
        }

        return null;
    }

}
