package com.didikee.cnbetareader.ui;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;


/**
 * Created by didik on 2017/2/9.
 * 统一处理标题等等
 */

public abstract class BaseCnBetaActivity extends AppCompatActivity {
    protected void setToolBar(Toolbar toolbar, CharSequence title, boolean backIcon, CharSequence
            subTitle, @DrawableRes int logo) {
        if (logo !=0){
            toolbar.setLogo(logo);
        }
        if (!TextUtils.isEmpty(subTitle)){
            toolbar.setSubtitle(subTitle);
        }
        if (!TextUtils.isEmpty(title)){
            toolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);
        if (backIcon) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void setToolBar(Toolbar toolbar, CharSequence title, boolean backIcon){
        setToolBar(toolbar,title,backIcon,null,0);
    }
    protected void setToolBar(Toolbar toolbar, CharSequence title){
        setToolBar(toolbar,title,false,null,0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
