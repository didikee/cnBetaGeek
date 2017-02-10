package com.didikee.cnbetareader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.test.TestActivity;

public class WelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,TestActivity.class));
//        startActivity(new Intent(this,ArticlesActivity.class));
    }
}
