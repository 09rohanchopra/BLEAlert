package com.choprarohan.mallinfo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rohan Chopra on 11/4/17.
 */

public class InfoViewHolder extends RecyclerView.ViewHolder {

    public TextView getNameText() {
        return nameText;
    }

    public void setNameText(TextView nameText) {
        this.nameText = nameText;
    }

    public TextView getShopText() {
        return shopText;
    }

    public void setShopText(TextView shopText) {
        this.shopText = shopText;
    }

    public TextView getSaleText() {
        return saleText;
    }

    public void setSaleText(TextView saleText) {
        this.saleText = saleText;
    }

    @BindView(R.id.name_text)
    TextView nameText;
    @BindView(R.id.number_text)
    TextView shopText;
    @BindView(R.id.sale_text)
    TextView saleText;

    public InfoViewHolder(View itemView) {
        super(itemView);


        ButterKnife.bind(this,itemView);


    }



}
