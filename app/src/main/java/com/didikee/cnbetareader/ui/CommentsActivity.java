package com.didikee.cnbetareader.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.adapters.ArticleListAdapter;
import com.didikee.uilibs.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsActivity extends BaseCnBetaActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        setToolBar(toolbar,"评论");
        initRecyclerView();
        initSwipeRefreshLayout();
    }
    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestArticleList(Integer.MAX_VALUE + "");
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        requestArticleList(Integer.MAX_VALUE + "");
    }

    private void initRecyclerView() {
        mALAdapter = new ArticleListAdapter();
        linearLayoutManager = new LinearLayoutManager(CommentsActivity
                .this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        final int dp2 = DisplayUtil.dp2px(this, 4);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView
                    .State state) {
                outRect.bottom = dp2;
                outRect.top = dp2;
            }

        });
        recyclerView.setAdapter(mALAdapter);

    }
}
