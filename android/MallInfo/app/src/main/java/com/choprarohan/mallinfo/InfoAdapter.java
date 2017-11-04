package com.choprarohan.mallinfo;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohan Chopra on 11/4/17.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoViewHolder>{


    Context context;
    private List<Details> items;
    public InfoAdapter(ArrayList<Details> items) {

        this.items = items;

    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        InfoViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);

        View listingView = inflater.inflate(R.layout.card_info, parent, false);
        viewHolder = new InfoViewHolder(listingView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        InfoViewHolder viewHolder = holder;
        configureInfoViewHolder(viewHolder, position);
    }

    private void configureInfoViewHolder(InfoViewHolder viewHolder, int position) {

        String[] names = context.getResources().getStringArray(R.array.names);

        viewHolder.getNameText().setText(names[items.get(position).getSno()%100]);
        viewHolder.getSaleText().setText("Flat "+items.get(position).getPerc()+"% off!");
        viewHolder.getShopText().setText("Shop Number: "+items.get(position).getSno());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
