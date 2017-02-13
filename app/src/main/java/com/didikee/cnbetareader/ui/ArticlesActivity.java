package com.didikee.cnbetareader.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.adapters.ArticleListAdapter;
import com.didikee.cnbetareader.bean.ArticleListBean;
import com.didikee.cnbetareader.bean.Keys;
import com.didikee.cnbetareader.network.HttpMethods;
import com.didikee.cnbetareader.ui.views.interf.OnCommentsButtonClickListener;
import com.didikee.cnbetareader.ui.views.interf.OnItemClickListener;
import com.didikee.uilibs.utils.DisplayUtil;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class ArticlesActivity extends BaseCnBetaActivity implements OnItemClickListener<String>,OnCommentsButtonClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindString(R.string.app_name)
    String appName;

    private ArticleListAdapter mALAdapter;

    private boolean isLoading = false; // 是否正在加载更多
    private LinearLayoutManager linearLayoutManager;

    private int smoothUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);

        setToolBar(toolbar,appName);
        initRecyclerView();
        initSwipeRefreshLayout();
        initParams();
    }

    private void initParams() {
        smoothUp = DisplayUtil.dp2px(this,56);
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestArticleList(Integer.MAX_VALUE + "");
            }
        });
        autoRefresh();
    }
    public void autoRefresh(){
        swipeRefreshLayout.setRefreshing(true);
        requestArticleList(Integer.MAX_VALUE + "");
    }

    private void initRecyclerView() {
        mALAdapter = new ArticleListAdapter();
        linearLayoutManager = new LinearLayoutManager(ArticlesActivity
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
        mALAdapter.setItemClickListener(this);
        mALAdapter.setOnCommentsButtonClickListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ( newState == RecyclerView.SCROLL_STATE_IDLE){
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && lastVisibleItemPosition == mALAdapter.getItemCount() -1 ){
                        // start loadMore
                        requestArticleList(mALAdapter.getLastSid());
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void requestArticleList(String sid) {
        HttpMethods.getInstance().getDefaultArticleList(new Subscriber<ArticleListBean>() {
            @Override
            public void onCompleted() {
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;
            }

            @Override
            public void onError(Throwable e) {
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;
            }

            @Override
            public void onNext(ArticleListBean articleListBean) {
                if (articleListBean !=null && articleListBean.getResult().size()>0){
                    List<ArticleListBean.ResultBean> result = articleListBean.getResult();
                    if (isLoading){
                        mALAdapter.update(result);
                        mALAdapter.notifyDataSetChanged();
                        recyclerView.smoothScrollBy(0,smoothUp);
                    }else {
                        mALAdapter.setData(result);
                        mALAdapter.notifyDataSetChanged();
                    }

                }
            }
        }, sid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_act_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Toast.makeText(this,"感谢使用,欢迎反馈"+"\n\n" + "          by didikee",Toast.LENGTH_LONG).show();
                break;
            case R.id.totop:
                recyclerView.smoothScrollToPosition(0);
                break;
            case R.id.refresh:
                autoRefresh();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.setting:
                Toast.makeText(this,"即将开放无图模式!",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(View view, String sid) {
        if (TextUtils.isEmpty(sid)){
            return;
        }
        Intent intent = new Intent(ArticlesActivity.this,NewsDetailActivity.class);
        intent.putExtra(Keys.SID,sid);
        startActivity(intent);
    }

    @Override
    public void onCommentsBtnClick(String sid,int comments) {
        if (TextUtils.isEmpty(sid) || comments <=0){
            Toast.makeText(this, "暂时还没有评论~", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ArticlesActivity.this,CommentsActivity.class);
        intent.putExtra(Keys.SID,sid);
        startActivity(intent);
    }
}
