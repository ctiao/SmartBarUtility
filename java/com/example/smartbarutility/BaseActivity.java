package com.example.smartbarutility;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SmartBarUtils.isMX2()){
            //SmartBarUtils.hide(getWindow().getDecorView());
            SmartBarUtils.hide(this,getWindow());
        }
        setContentView(R.layout.activity_base);
    }

    
}
