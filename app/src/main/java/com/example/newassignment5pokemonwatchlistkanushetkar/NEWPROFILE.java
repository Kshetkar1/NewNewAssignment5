package com.example.newassignment5pokemonwatchlistkanushetkar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NEWPROFILE extends BaseAdapter {


    Context context;
    List<String> textList;
    LayoutInflater inflater;



    public NEWPROFILE(Context context, List<String> textList) {
        this.context = context;
        this.textList = textList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return textList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_new_new_profile, null);
        TextView profileText = (TextView) convertView.findViewById(R.id.profileTV);
        profileText.setText(textList.get(position));
        return convertView;
    }

    public void updateAdapter(List<String> textList) {
        this.textList = textList;
        notifyDataSetChanged();
    }
}
