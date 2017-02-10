package com.didikee.cnbetareader.ui.customviews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.didikee.cnbetareader.R;
import com.didikee.uilibs.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by didik 
 * Created time 2017/2/10
 * Description: 
 */

public class CommentsView extends FrameLayout{
    private @ColorInt int mFloorBackgroundColor = Color.WHITE;// 楼层背景
    private int mFloorPadding = 0; //楼层叠加时,上左右的padding
    private @DrawableRes int mFloorBackgroundDrawableRes;

    public CommentsView(Context context) {
        this(context,null);
    }

    public CommentsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommentsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initParams();
    }

    private void initParams() {
        mFloorPadding = DisplayUtil.dp2px(getContext(),3);
        mFloorBackgroundDrawableRes = R.drawable.shape_comment_background;
        setBackgroundColor(mFloorBackgroundColor);
    }

    /**
     * 楼层
     * @param floorComments 1,2,3...依次排序
     */
    public void setFloors(List<CommentBean> floorComments){
        if (floorComments ==null || floorComments.size()<=0)return;
        int size = floorComments.size();
        int lastHeight = 0;
        ArrayList<Pair<View,FrameLayout.LayoutParams>> allView = new ArrayList<>();
        int almost = DisplayUtil.dp2px(getContext(), 32);
        for (int i = 0; i < size; i++) {
            LayoutParams floorLayoutParams = getFloorLayoutParams(size, i);
            CommentBean commentBean = floorComments.get(i);
            CommentItemView itemView =new CommentItemView(getContext());
            itemView.setTitle(commentBean.getName());
            itemView.setContent(commentBean.getContent());
            int paddingTop = lastHeight + mFloorPadding * i;
            Log.e("test","top: "+paddingTop);
            itemView.setPadding(0,paddingTop,0,0);
//            模仿 煎蛋盖楼
//            if (i == size -1 ){
//                itemView.setBackgroundColor(mFloorBackgroundColor);
//            }else {
//                itemView.setBackgroundResource(mFloorBackgroundDrawableRes);
//            }
//            demo 演示
            itemView.setBackgroundResource(mFloorBackgroundDrawableRes);
            allView.add(new Pair<View, LayoutParams>(itemView,floorLayoutParams));
            int alx1 = getHeight(getContext(),commentBean.getName(),12,getCurrentWidth(size,i),Typeface.DEFAULT,0);
            int alx2 = getHeight(getContext(),commentBean.getContent(),14,getCurrentWidth(size,i),Typeface.DEFAULT,0);
            Log.e("test","i: "+i +" alx1: "+alx1 +"  alx2: "+alx2+" almost: "+almost) ;
            lastHeight =lastHeight + alx1 +alx2+ almost;
            Log.e("test","lastHeight: "+lastHeight) ;
        }
        for (int i = allView.size() - 1; i >= 0; i--) {// 倒序添加
            Pair<View, LayoutParams> viewLayoutParamsPair = allView.get(i);
            this.addView(viewLayoutParamsPair.first,viewLayoutParamsPair.second);
        }
    }

    /**
     * 在绘制之前,测量 TextView 的高度
     * @param context  上下文
     * @param text 内容
     * @param textSize sp
     * @param deviceWidth TextView 所在容器的宽度[重要]
     * @param typeface 加粗等等样式
     * @param padding 内间距
     * @return TextView 的高度
     */
    public int getHeight(Context context, CharSequence text, int textSize, int deviceWidth, Typeface typeface, int padding) {
        TextView textView = new TextView(context);
        textView.setPadding(padding,padding,padding,padding);
        textView.setTypeface(typeface);
        textView.setText(text, TextView.BufferType.SPANNABLE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }

    private int getCurrentWidth(int size,int order){
        int width = DisplayUtil.getWindowPixels(getContext()).first;
        width -= DisplayUtil.dp2px(getContext(),16) *2;//手动计算
        int totalPadding = (size - order) * mFloorPadding;
        return width - totalPadding;
    }

    private FrameLayout.LayoutParams getFloorLayoutParams(int size , int order){
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int totalPadding = (size -1 - order) * mFloorPadding;
        params.setMargins(totalPadding,totalPadding,totalPadding,0);
        return params;
    }


}
