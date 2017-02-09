package com.didikee.cnbetareader.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by didik 
 * Created time 2017/2/9
 * Description: 
 */

public class URLImageParser implements Html.ImageGetter{
    private final Context context;
    private int imageWidth ;
    public URLImageParser(Context context,int imageWidth) {
        this.imageWidth = imageWidth;
        this.context = context;
    }
    Drawable urlDrawable = null;
    public Drawable getDrawable(String source) {
        ImageGetterAsyncTask asyncTask =
                new ImageGetterAsyncTask();
        asyncTask.execute(source);
        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {


        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        @Override
        protected void onPostExecute(Drawable result) {
            urlDrawable = result;
        }

        public Drawable fetchDrawable(String urlString) {
            Bitmap bitmap = null;
            Drawable drawable = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
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
//                Drawable drawable = Drawable.createFromStream(is);
//                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth(), 0
//                        + drawable.getIntrinsicHeight());
                return drawable;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
