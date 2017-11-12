package com.example.adaministrator.myapplication.bilibili;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ImageView;

import com.example.adaministrator.myapplication.MDActivity;
import com.example.adaministrator.myapplication.MyAdapter;
import com.example.adaministrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import master.flame.danmaku.ui.widget.DanmakuView;

public class BliBliActivity extends AppCompatActivity {

    private AppBarLayout app_bar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ButtonBarLayout playButton;
    private CollapsingToolbarLayoutState state;
    private ImageView mIvCover;
    private DanmakuView danmakuView;

    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    List<String> list;
    MyAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setSharedElementEnterTransition(new Fade());
        setContentView(R.layout.activity_bli_bli);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        playButton = (ButtonBarLayout) findViewById(R.id.playButton);
        mIvCover = (ImageView) findViewById(R.id.iv_cover);
        danmakuView = (DanmakuView) findViewById(R.id.danmaku);
        //        danmakuView.show();

        //        collapsingToolbarLayout.setTitleEnabled(false);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIvCover.setVisibility(View.GONE);
                app_bar.setExpanded(true);
            }
        });
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.setTitle("EXPANDED");//设置title为EXPANDED
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("");//设置title不显示
                        playButton.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        collapsingToolbarLayout.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setExitTransition(new Explode());
                getWindow().setSharedElementExitTransition(new ChangeImageTransform());
                Intent intent = new Intent(BliBliActivity.this, MDActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(BliBliActivity.this, mIvCover,
                        "cover")
                        .toBundle
                                ());
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
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
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            anim();
        }
    }

    private void anim() {
        // previously invisible view
        View myView = findViewById(R.id.tv_content);

        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }
}
