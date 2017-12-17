package com.example.adaministrator.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.AutoCreate;

import java.util.ArrayList;
import java.util.List;
@AutoCreate
public class ScrollingActivity extends AppCompatActivity implements MyAdapter.ActionListener {

    RecyclerView recyclerView;
    StaggeredGridLayoutManager mLayoutManager;
    List<String> list;
    MyAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
        getWindow().setAllowEnterTransitionOverlap(true);

        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //创建默认的线性LayoutManager
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //        recyclerView.setItemAnimator(new DefaultItemAnimator());

        list = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            list.add("item: " + i);
        }
        //创建并设置Adapter
        mAdapter = new MyAdapter(this, list);
        mAdapter.setActionListener(this);
        recyclerView.setAdapter(mAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setExitTransition(new Explode());
                getWindow().setSharedElementExitTransition(new ChangeImageTransform());

                Intent intent = new Intent(ScrollingActivity.this, AnimatorActivity.class);
                //                ContextCompat.startActivity(ScrollingActivity.this, intent, null);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ScrollingActivity.this).toBundle());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void add(int position) {
        int size = mAdapter.getItemCount();
        String content = "added item: " + size;
        Snackbar.make(fab, content, Snackbar.LENGTH_LONG).show();
        mAdapter.addItem(size, content);
    }

    @Override
    public void remove(int position) {
        String content = "remove item: " + position;
        Snackbar.make(fab, content, Snackbar.LENGTH_LONG).show();
        mAdapter.removeItem(position);
    }

    @Override
    public void update(int position) {
        String content = "update item: " + position;
        Snackbar.make(fab, content, Snackbar.LENGTH_LONG).show();
        mAdapter.update(position, content);
    }
}
