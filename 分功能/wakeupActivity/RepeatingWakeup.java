package cn.edu.hebtu.software.peace.wakeupActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.edu.hebtu.software.peace.alarmactivity.Alarm2Activity;
import cn.edu.hebtu.software.peace.alarmactivity.Alarm3Activity;
import cn.edu.hebtu.software.peace.alarmactivity.Alarm4Activity;
import cn.edu.hebtu.software.peace.alarmactivity.Alarm5Activity;
import cn.edu.hebtu.software.peace.alarmactivity.Alarm6Activity;
import cn.edu.hebtu.software.peace.alarmactivity.AlarmActivity;

public class RepeatingWakeup extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction()!=null){
            switch (intent.getAction()){
                case "com.ll.wakeup.a":
                    intent = new Intent(context,WakeupaActivity.class);
                    break;
                case "com.ll.wakeup.b":
                    intent = new Intent(context,WakeupbActivity.class);
                    break;
                case "com.ll.wakeup.c":
                    intent = new Intent(context,WakeupcActivity.class);
                    break;
                case "com.ll.wakeup.d":
                    intent = new Intent(context,WakeupdActivity.class);
                    break;

                case "com.ll.wakeup.e":
                    intent = new Intent(context,WakeupeActivity.class);
                    break;
                case "com.ll.wakeup.f":
                    intent = new Intent(context,WakeupfActivity.class);
                    break;
            }
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
