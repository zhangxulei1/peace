package cn.edu.hebtu.software.peace.alarmactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RepeatingAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction()!=null){
            switch (intent.getAction()){
                case "com.ll.alarm.":
                    intent = new Intent(context,AlarmActivity.class);
                    break;
                case "com.ll.alarm.chenshi":
                    intent = new Intent(context,AlarmActivity.class);
                    break;
                case "com.ll.alarm.xingguang":
                    intent = new Intent(context,Alarm2Activity.class);
                    break;
                case "com.ll.alarm.xiaoyin":
                    intent = new Intent(context,Alarm3Activity.class);
                    break;
                case "com.ll.alarm.fuguang":
                    intent = new Intent(context,Alarm4Activity.class);
                    break;

                case "com.ll.alarm.suyu":
                    intent = new Intent(context,Alarm5Activity.class);
                    break;
                case "com.ll.alarm.mijing":
                    intent = new Intent(context,Alarm6Activity.class);
                    break;
            }
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.e("广播李阿敏",context.getPackageName()+"zhi");
            context.startActivity(intent);
        }

    }
}
