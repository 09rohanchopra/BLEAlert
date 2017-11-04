package com.choprarohan.mallinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    @BindView(R.id.info_recycler)
    RecyclerView infoRecyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    ArrayList<Details> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);




    }

    @Override
    protected void onStart() {
        super.onStart();


        items = new ArrayList<>();

        infoRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);







        InfoAdapter infoAdapter = new InfoAdapter(items);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setMoveDuration(1000);
        infoRecyclerView.setItemAnimator(animator);
        infoRecyclerView.setAdapter(infoAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        infoRecyclerView.setLayoutManager(layoutManager);



        infoRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);



    }
}
