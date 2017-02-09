package com.didikee.cnbetareader.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.adapters.ArticleListAdapter;
import com.didikee.cnbetareader.bean.ArticleListBean;
import com.didikee.cnbetareader.bean.Keys;
import com.didikee.cnbetareader.network.HttpMethods;
import com.didikee.cnbetareader.ui.views.OnItemClickListener;
import com.didikee.uilibs.utils.DisplayUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class ArticleListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArticleListAdapter mALAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);

        initRecyclerView();
        requsetArticleList();

    }

    private void initRecyclerView() {
        mALAdapter = new ArticleListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(ArticleListActivity.this,LinearLayoutManager.VERTICAL,false));

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
        mALAdapter.setItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String sid) {
                if (TextUtils.isEmpty(sid)){
                    return;
                }
                Intent intent = new Intent(ArticleListActivity.this,NewsDetailActivity.class);
                intent.putExtra(Keys.SID,sid);
                startActivity(intent);
            }
        });
    }

    private void requsetArticleList() {
        String sid = Integer.MAX_VALUE + "";
        HttpMethods.getInstance().getDefaultArticleList(new Subscriber<ArticleListBean>() {
            @Override
            public void onCompleted() {
                Toast.makeText(ArticleListActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArticleListBean articleListBean) {
                if (articleListBean !=null && articleListBean.getResult().size()>0){
                    List<ArticleListBean.ResultBean> result = articleListBean.getResult();
                    mALAdapter.setData(result);
                    mALAdapter.notifyDataSetChanged();
                }
            }
        }, sid);
    }
}
