package com.example.adaministrator.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MDActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<String> list;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
        getWindow().setAllowEnterTransitionOverlap(true);
        setContentView(R.layout.activity_md);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        list = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            list.add("item: " + i);
        }
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        //        fab.setTransitionName("fab");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSomeButtonClicked(view);
            }
        });
    }

    public void onSomeButtonClicked(View view) {
        getWindow().setExitTransition(new Explode());
        getWindow().setSharedElementExitTransition(new ChangeImageTransform());
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAfterTransition();
    }

}
