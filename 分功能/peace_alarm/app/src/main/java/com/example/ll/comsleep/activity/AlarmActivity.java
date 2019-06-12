package com.example.ll.comsleep.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.ll.comsleep.R;
import com.example.ll.comsleep.SleepTotalAcitivty;

import java.io.IOException;

public class AlarmActivity extends Activity {

    public static final String INTENAL_ACTION_3 ="com.example.demobroadcast.BroadcastAction";

    private String alarmName;
    MediaPlayer mp;

//    public StatusChangeListener.DataManager datamanager;
//    public StatusListener mstatuslisntener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.aty_alarm);

//        mstatuslisntener= new StatusListener();    //初始化监听函数
//        datamanager= StatusChangeListener.DataManager.instance(getApplication());   //创建Datamanager实例
//        datamanager.setStatusChangeListener(mstatuslisntener);   //注册监听器

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("musicName"); //为BroadcastReceiver指定action，即要监听的消息名字。
//        registerReceiver(MyBroadcastReceiver,intentFilter); //注册监听
//        unregisterReceiver(MyBroadcastReceiver); //取消监听






        //alarmName = SleepTotalAcitivty.getMusic();
        Log.e("选择音乐名字:",alarmName);


        mp = new MediaPlayer();
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.suyu);
        try {
            mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                    file.getLength());
            mp.prepare();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setVolume(0.5f, 0.5f);
        mp.setLooping(true);
        mp.start();
        alarmOialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
        }
    }

    public void alarmOialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("主人，你该起床了");
        builder.setPositiveButton("稍后提醒",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alarm();
                        finish();
                    }
                });

        builder.setNegativeButton("停止", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancleAlarm();
                finish();// 关闭窗口
            }
        });
        builder.show().setCanceledOnTouchOutside(false);
        ;

    }

    /**
     * 取消闹钟
     */
    private void cancleAlarm() {
        // Create the same intent, and thus a matching IntentSender, for
        // the one that was scheduled.
        Intent intent = new Intent(AlarmActivity.this, RepeatingAlarm.class);
        intent.setAction("com.ll.alarm");
        PendingIntent sender = PendingIntent.getBroadcast(AlarmActivity.this,
                0, intent, 0);

        // And cancel the alarm.
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(sender);
    }

    private void alarm() {
        // 获取系统的闹钟服务
        AlarmManager am = (AlarmManager) getSystemService( Context.ALARM_SERVICE);
        // 触发闹钟的时间（毫秒）
        long triggerTime = System.currentTimeMillis() + 10000;
        Intent intent = new Intent(this, RepeatingAlarm.class);
        intent.setAction("com.ll.alarm");
        PendingIntent op = PendingIntent.getBroadcast(this, 0, intent, 0);
        // 启动一次只会执行一次的闹钟
        am.set(AlarmManager.RTC, triggerTime, op);
        // 指定时间重复执行闹钟
        // am.setRepeating(AlarmManager.RTC,triggerTime,2000,op);
    }


//    private class  StatusListener implements StatusChangeListener {
//        @Override
//        public void statuschange(String status) {
//            alarmName = status;
//
//        }
//    }

    private class MyBroadcastReceiver extends BroadcastReceiver
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if(INTENAL_ACTION_3.equals(action))
            {
                //处理内容
            }
        }
    }

}
