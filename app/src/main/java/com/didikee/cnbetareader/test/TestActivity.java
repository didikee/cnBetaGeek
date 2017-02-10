package com.didikee.cnbetareader.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.bean.ArticleListBean;
import com.didikee.cnbetareader.network.HttpMethods;
import com.didikee.cnbetareader.ui.customviews.CommentBean;
import com.didikee.cnbetareader.ui.customviews.CommentsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;


public class TestActivity extends AppCompatActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.activity_test)
    LinearLayout activityTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);


        addCommentsView();
//        getDouBanMovie();
    }

    private void addCommentsView() {
        CommentsView commentsView = new CommentsView(this);
        ArrayList<CommentBean> comments = new ArrayList<>();
        comments.add(new CommentBean("一级", "你根本不是司机,我应该是上了假车,快放我下车!!!"));
        comments.add(new CommentBean("二级", "我是司机,滴滴,开车了,目的地----秋明山!!!"));
        comments.add(new CommentBean("三级", "污污污~~~老司机,带带我~~~"));
        comments.add(new CommentBean("四级", "我仿佛错过了什么!"));
        comments.add(new CommentBean("五级", "我就不信那个小学生会把我的脸按在键盘上,哈哈哈是独立地位哦就dsjiodjsojssddnldfiegnbiof大街上看到dsijdoijsd史蒂文sdn"));
        commentsView.setFloors(comments);

        activityTest.addView(commentsView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void getDouBanMovie() {

        String sid = Integer.MAX_VALUE + "";
        HttpMethods.getInstance().getDefaultArticleList(new Subscriber<ArticleListBean>() {
            @Override
            public void onCompleted() {
                Toast.makeText(TestActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArticleListBean s) {

            }
        }, sid);
    }
}
