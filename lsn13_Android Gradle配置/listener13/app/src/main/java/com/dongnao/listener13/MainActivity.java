package com.dongnao.listener13;

import android.app.Activity;
import android.os.Bundle;

import com.dongnao.library.Library;

import listener13.Test;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        View v = null;
//        v.setOnClickListener(view ->{
//
//        });
        Library.test();
        Test.test();

    }

}
