package com.example.ll.comsleep;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MaoSleep extends Activity {
    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.mao_sleep );
        tv = findViewById( R.id.tv_text );
        Intent intent = getIntent();
        List<String> data = (ArrayList<String>) intent
                .getSerializableExtra("data");//获取intent中的数据
        data.toString();
        tv.setText( data.toString() );
    }
    public void SendMessageValue(String strValue) {
        tv.setText(strValue);
    }


}
