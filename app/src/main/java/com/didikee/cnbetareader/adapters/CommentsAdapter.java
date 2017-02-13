package com.didikee.cnbetareader.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by didik 
 * Created time 2017/2/10
 * Description: 
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

//    private List<ArrayList<CommentBean>> data;
    private Context context;
    private List<CommentBean> data = new ArrayList<>();

    //    public void setData(List<ArrayList<CommentBean>> data){
//        this.data = data;
//    }
    public void setData(List<CommentBean> data){
        this.data = data;
    }
    public void updateData(List<CommentBean> result) {
        if (result !=null){
            data.addAll(result);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context= parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout
                .layout_item_comment_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        ArrayList<CommentBean> beanList = data.get(position);
//        CommentBean beanList = data.get(position);
//        //填充数据
//        int size = beanList.size();
//        if (size <= 0)return;
//        CommentBean commentBean = beanList.get(size - 1);
//        holder.tvName.setText(TextUtils.isEmpty(commentBean.getUsername()) ? "匿名用户" : commentBean.getUsername());
//        holder.tvTime.setText(commentBean.getCreated_time());
//        holder.tvYes.setText(commentBean.getSupport());
//        holder.tvNo.setText(commentBean.getAgainst());
//
//        ArrayList<com.didikee.cnbetareader.ui.customviews.CommentBean> needBeans = new ArrayList<>();
//        for (CommentBean bean : beanList) {
//            needBeans.add(new com.didikee.cnbetareader.ui.customviews.CommentBean(
//                    TextUtils.isEmpty(commentBean.getUsername()) ? "匿名用户" : commentBean.getUsername(),bean.getContent()));
//        }
//        holder.flContainer.addView(new CommentsView(needBeans,context),FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);


        CommentBean commentBean = data.get(position);
        holder.tvName.setText(TextUtils.isEmpty(commentBean.getUsername()) ? "匿名用户" : commentBean.getUsername());
        holder.tvTime.setText(commentBean.getCreated_time());
        holder.tvYes.setText(commentBean.getSupport());
        holder.tvNo.setText(commentBean.getAgainst());
        holder.tvContent.setText(commentBean.getContent());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
//        @BindView(R.id.fl_container)
//        FrameLayout flContainer;
        @BindView(R.id.tv_yes)
        TextView tvYes;
        @BindView(R.id.tv_no)
        TextView tvNo;
        @BindView(R.id.item)
        LinearLayout item;
        @BindView(R.id.tv_content)
        TextView tvContent;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
