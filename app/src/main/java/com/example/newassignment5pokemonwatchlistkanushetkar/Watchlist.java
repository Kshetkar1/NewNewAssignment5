package com.example.newassignment5pokemonwatchlistkanushetkar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Watchlist extends BaseAdapter {

    Context context;
    List<PokeInfo> watchlist;
    LayoutInflater inflater;
    public Watchlist(Context context, List<PokeInfo> watchlist) {
        this.context = context;
        this.watchlist = watchlist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return watchlist.size();
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
    public View getView(int position, View convertTheView, ViewGroup ViewGROUP) {
        convertTheView = inflater.inflate(R.layout.activity_watchlist_lv, null);
        TextView watchlistName = (TextView) convertTheView.findViewById(R.id.WATCHLISTNameTV);
        watchlistName.setText(watchlist.get(position).getName());
        TextView watchlistID = (TextView) convertTheView.findViewById(R.id.WATCHLISTIDTV);
        watchlistID.setText("#"+String.valueOf(watchlist.get(position).getId()));
        return convertTheView;
    }

    public void updateAdapter(List<PokeInfo> watchList) {
    }
}
