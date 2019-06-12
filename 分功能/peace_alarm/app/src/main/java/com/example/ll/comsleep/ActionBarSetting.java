package com.example.ll.comsleep;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ll.comsleep.activity.SelectAlarmClockActivity;

public class ActionBarSetting extends Activity {

    //选择第一个卡片，闹钟选择
    private LinearLayout selectAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);

        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_sleep);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        selectAlarm=findViewById(R.id.alarm_clock_select);
        selectAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ActionBarSetting.this,SelectAlarmClockActivity.class);
                startActivity(i);
            }
        });
    }
}
