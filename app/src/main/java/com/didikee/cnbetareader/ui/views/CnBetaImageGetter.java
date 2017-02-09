package com.didikee.cnbetareader.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by didik 
 * Created time 2017/2/9
 * Description: 
 */

public class CnBetaImageGetter implements Html.ImageGetter {

    private final Context context;
    private int imageWidth ;
    public CnBetaImageGetter(Context context,int imageWidth) {
        this.imageWidth = imageWidth;
        this.context = context;
    }

    @Override
    public Drawable getDrawable(String source) {
        Bitmap bitmap = null;
        InputStream is = null;
        Drawable drawable = null;
        try {
//            is = (InputStream) new URL(source).getContent();
            URL url = new URL(source);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            is = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            if (bitmap != null) {
                drawable = new BitmapDrawable(context.getResources(), bitmap);
                if (imageWidth !=0){
                    float scale = imageWidth * 1.0f / bitmap.getWidth();
                    final int height = (int) (bitmap.getHeight() * scale);
                    drawable.setBounds(0, 0, imageWidth, height);
                }else {
                    drawable.setBounds(0, 0, bitmap.getWidth(),
                            bitmap.getHeight());
                }
            }
            is.close();
            return drawable;
        } catch (Exception e) {
            return null;
        }
    }
}
