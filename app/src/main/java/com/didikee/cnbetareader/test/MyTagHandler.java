package com.didikee.cnbetareader.test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.Editable;
import android.text.Html.TagHandler;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;

import com.didikee.cnbetareader.utils.MD5;

import org.xml.sax.XMLReader;

import java.io.File;

public class MyTagHandler implements TagHandler {

	private Context context;
	
	public MyTagHandler(Context context) {
		this.context = context;
	}
	
	@Override
	public void handleTag(boolean opening, String tag, Editable output,
			XMLReader xmlReader) {
		// TODO Auto-generated method stub

		// �����ǩ<img>
		if (tag.toLowerCase().equals("img")) {
			// ��ȡ����
			int len = output.length();
			// ��ȡͼƬ��ַ
			ImageSpan[] images = output.getSpans(len-1, len, ImageSpan.class);
			String imgURL = images[0].getSource();
			
			// ʹͼƬ�ɵ������������¼�
			output.setSpan(new ImageClick(context, imgURL), len-1, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
	}
	
	private class ImageClick extends ClickableSpan {

		private String url;
		private Context context;
		
		public ImageClick(Context context, String url) {
			this.context = context;
			this.url = url;
		}
		
		@Override
		public void onClick(View widget) {
			// TODO Auto-generated method stub
			// ��ͼƬURLת��Ϊ����·�������Խ�ͼƬ���������ͼƬ�������дΪһ���������������

			String imageName = MD5.md5(url);
			String sdcardPath = Environment.getExternalStorageDirectory().toString(); // ��ȡSDCARD��·��
			//��ȡͼƬ��׺��
			String[] ss = url.split("\\.");
			String ext = ss[ss.length - 1];
			
			// ����ͼƬ���ֵĵ�ַ
			String savePath = sdcardPath + "/" + context.getPackageName() + "/" + imageName + "." + ext;
			
			File file = new File(savePath);
			if (file.exists()) {
				// �������¼�������һ���µ�activity��������ʾͼƬ
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				context.startActivity(intent);
			}
		}
		
	}

}
