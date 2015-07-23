package com.redmondtest.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

    private SearchEngine mSearchEngine;
    private SearchViewAdapter mSearchViewAdapter;

    public SearchFragment() {
        mSearchEngine = new SearchEngine();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        final AutoCompleteTextView searchEdit = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextView);

        mSearchViewAdapter = new SearchViewAdapter(getActivity());
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(mSearchViewAdapter);

        Button buttonSearch = (Button) rootView.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchViewAdapter.clear();
                new FetchSearchTask(mSearchViewAdapter, mSearchEngine).execute(searchEdit.getText().toString());
            }
        });



        return rootView;
    }


}
