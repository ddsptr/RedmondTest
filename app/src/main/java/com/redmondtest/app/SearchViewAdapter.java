package com.redmondtest.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dds on 21.07.15.
 */
public class SearchViewAdapter extends BaseAdapter {

    private ArrayList<SearchItem> mSearchItems = new ArrayList<SearchItem>();
    private Context mContext;
    private LayoutInflater mInflater;

    public SearchViewAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(SearchItem searchItem){
        mSearchItems.add(searchItem);
        notifyDataSetChanged();
    }

    public void clear() {
        mSearchItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSearchItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mSearchItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = mInflater.inflate(R.layout.search_item, null);
        }
        if (mSearchItems != null) {
            TextView tvSearchItemTitle = (TextView) view.findViewById(R.id.search_title);
            tvSearchItemTitle.setText(mSearchItems.get(position).getTitle());
            TextView tvSearchItemContent = (TextView) view.findViewById(R.id.search_content);
            tvSearchItemContent.setText(mSearchItems.get(position).getContent());
            TextView tvSearchItemUrl = (TextView) view.findViewById(R.id.search_url);
            tvSearchItemUrl.setText(mSearchItems.get(position).getUrl());
        }

        return view;
    }
}
