package com.didikee.cnbetareader.test;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html.ImageGetter;
import android.widget.TextView;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.utils.MD5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyImageGetter implements ImageGetter {
	
	private Context context;
	private TextView tv;
	
	public MyImageGetter(Context context, TextView tv) {
		this.context = context;
		this.tv = tv;
	}
	
	@Override
	public Drawable getDrawable(String source) {

		String imageName = MD5.md5(source);
		String sdcardPath = Environment.getExternalStorageDirectory().toString();

		String[] ss = source.split("\\.");
		String ext = ss[ss.length - 1];
		
		String savePath = sdcardPath + "/" + context.getPackageName() + "/" + imageName + "." + ext;
		
		File file = new File(savePath);
		if (file.exists()) {
			Drawable drawable = Drawable.createFromPath(savePath);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
			return drawable;
		}

		Resources res = context.getResources();
		URLDrawable drawable = new URLDrawable(res.getDrawable(R.mipmap.ic_launcher));
		new ImageAsync(drawable).execute(savePath, source);
		return drawable;
		
	}

	private class ImageAsync extends AsyncTask<String, Integer, Drawable> {

		private URLDrawable drawable;
		
		public ImageAsync(URLDrawable drawable) {
			this.drawable = drawable;
		}
		
		@Override
		protected Drawable doInBackground(String... params) {
			// TODO Auto-generated method stub
			String savePath = params[0];
			String urlString = params[1];
			
			InputStream in = null;
			try {
//				HttpGet http = new HttpGet(url);
//				HttpClient client = new DefaultHttpClient();
//				HttpResponse response = (HttpResponse) client.execute(http);
//				BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(response.getEntity());
//				in = bufferedHttpEntity.getContent();
				URL url = new URL(urlString);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				in = urlConnection.getInputStream();
				
			} catch (Exception e) {
				try {
					if (in != null)
						in.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

			if (in == null) return drawable;
			
			try {
				File file = new File(savePath);
				String basePath = file.getParent();
				File basePathFile = new File(basePath);
				if (!basePathFile.exists()) {
					basePathFile.mkdirs();
				}
				file.createNewFile();
				FileOutputStream fileout = new FileOutputStream(file);
				byte[] buffer = new byte[4*1024];
				while (in.read(buffer) != -1) {
					fileout.write(buffer);
				}
				fileout.flush();
				
				Drawable mDrawable = Drawable.createFromPath(savePath);
				return mDrawable;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return drawable;
		}

		@Override
		protected void onPostExecute(Drawable result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				drawable.setDrawable(result);
				tv.setText(tv.getText());
			}
		}
		
	}

	public class URLDrawable extends BitmapDrawable {
		
		private Drawable drawable;
		
		public URLDrawable(Drawable defaultDraw) {
			setDrawable(defaultDraw);
		}
		
		private void setDrawable(Drawable nDrawable) {
			drawable = nDrawable;
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
			setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		}

		@Override
		public void draw(Canvas canvas) {
			// TODO Auto-generated method stub
			drawable.draw(canvas);
		}
		
	}
}
