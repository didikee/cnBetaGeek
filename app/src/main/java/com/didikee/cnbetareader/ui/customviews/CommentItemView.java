package com.didikee.cnbetareader.ui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.didikee.cnbetareader.R;

/**
 * Created by didik 
 * Created time 2017/2/10
 * Description: 
 */

public class CommentItemView extends LinearLayout{

    private TextView tvTitle;
    private TextView tvContent;

    public CommentItemView(Context context) {
        this(context,null);
    }

    public CommentItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommentItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.layout_item_comment,this);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);

    }

    public TextView getTitle(){
        return tvTitle;
    }
    public TextView getContent(){
        return tvContent;
    }

    public void setTitle(CharSequence title){
        tvTitle.setText(title);
    }
    public void setContent(CharSequence content){
        tvContent.setText(content);
    }

}
