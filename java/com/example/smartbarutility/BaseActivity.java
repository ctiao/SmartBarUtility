package com.example.smartbarutility;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SmartBarUtils.isMX2()){
            //方式1,推荐
            SmartBarUtils.hide(this, getWindow(),SmartBarUtils.SMART_BAR_HEIGHT_PIXEL);

//            //方式2
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            SmartBarUtils.hide(getWindow().getDecorView());
//
//            //方式3
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            SmartBarUtils.hide(this, getWindow());
        }
        setContentView(R.layout.activity_base);
    }

    
}
