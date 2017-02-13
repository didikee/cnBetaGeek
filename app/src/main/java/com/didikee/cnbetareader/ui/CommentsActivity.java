package com.didikee.cnbetareader.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.adapters.CommentsAdapter;
import com.didikee.cnbetareader.bean.CommentBean;
import com.didikee.cnbetareader.bean.CommentBeanList;
import com.didikee.cnbetareader.bean.Keys;
import com.didikee.cnbetareader.network.HttpMethods;
import com.didikee.uilibs.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class CommentsActivity extends BaseCnBetaActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager linearLayoutManager;
    private CommentsAdapter commentsAdapter;
    private String curSid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        getIntentData();
        setToolBar(toolbar, "评论",true);
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        } else {
            curSid = intent.getStringExtra(Keys.SID);
        }
        if (TextUtils.isEmpty(curSid)) {
            finish();
        }
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestComments(1);
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        requestComments(1);
    }

    private void initRecyclerView() {
        commentsAdapter = new CommentsAdapter();
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
        recyclerView.setAdapter(commentsAdapter);
    }

    private void requestComments(int page) {
        HttpMethods.getInstance().getNewsComments(new Subscriber<CommentBeanList>() {
            @Override
            public void onCompleted() {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommentBeanList s) {
                List<ArrayList<CommentBean>> comments = doCommentsData(s);
                if (comments == null || comments.size() <= 0) {
                    Toast.makeText(CommentsActivity.this, "数据异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                commentsAdapter.setData(comments);
                commentsAdapter.notifyDataSetChanged();
            }
        }, curSid, page);
    }

    private List<ArrayList<CommentBean>> doCommentsData(CommentBeanList commentBeanList) {
        List<ArrayList<CommentBean>> data = null;
        String status = commentBeanList.getStatus();
        if (Keys.RESULT_OK.equalsIgnoreCase(status)) {
            data = new ArrayList<>();
            List<CommentBean> result = commentBeanList.getResult();
            for (CommentBean bean : result) {
                String tid = bean.getTid();
                if (!TextUtils.isEmpty(tid)) {
                    // TODO 添加序列
                }
                ArrayList<CommentBean> item = new ArrayList<>();
                item.add(bean);
                data.add(item);

            }
        }
        return data;
    }
}
