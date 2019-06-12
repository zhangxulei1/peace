package cn.edu.hebtu.software.peace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WakeTimeActivity extends Activity implements View.OnClickListener{
    private int time;
    private Boolean flag1=false;
    private Boolean flag2=false;
    private Boolean flag3=false;
    private Boolean flag4=false;
    private ImageView three;
    private ImageView five;
    private ImageView ten;
    private ImageView shiwu;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wakeup_time);
        
        initView();

        panduan();
        three.setOnClickListener(this);
        five.setOnClickListener(this);
        ten.setOnClickListener(this);
        shiwu.setOnClickListener(this);
        back.setOnClickListener(this);
        
        
    }

    private void panduan() {
        Intent i=getIntent();
        String t=i.getStringExtra("t");
        Pattern p=Pattern.compile("\\d+");
        Matcher m=p.matcher(t);
        m.find();
        String ti=m.group();
        int sj= Integer.parseInt(ti);
        if (sj==3){
            three.setImageResource(R.drawable.circle_selected);
            five.setImageResource(R.drawable.circle_select);
            ten.setImageResource(R.drawable.circle_select);
            shiwu.setImageResource(R.drawable.circle_select);
        }else if(sj==5){
            five.setImageResource(R.drawable.circle_selected);
            three.setImageResource(R.drawable.circle_select);
            ten.setImageResource(R.drawable.circle_select);
            shiwu.setImageResource(R.drawable.circle_select);
        }else if(sj==10){
            three.setImageResource(R.drawable.circle_select);
            five.setImageResource(R.drawable.circle_select);
            ten.setImageResource(R.drawable.circle_selected);
            shiwu.setImageResource(R.drawable.circle_select);
        }else if (sj==15){
            three.setImageResource(R.drawable.circle_select);
            five.setImageResource(R.drawable.circle_select);
            ten.setImageResource(R.drawable.circle_select);
            shiwu.setImageResource(R.drawable.circle_selected);
        }
    }


    private void initView() {
        three=findViewById(R.id.three);
        five=findViewById(R.id.five);
        ten=findViewById(R.id.ten);
        shiwu=findViewById(R.id.shiwu);
        back=findViewById(R.id.back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.three:
                if (flag1==false){
                three.setImageResource(R.drawable.circle_selected);
                five.setImageResource(R.drawable.circle_select);
                ten.setImageResource(R.drawable.circle_select);
                shiwu.setImageResource(R.drawable.circle_select);
                time=3;}
                break;
            case R.id.five:
                if (flag2==false){
                five.setImageResource(R.drawable.circle_selected);
                three.setImageResource(R.drawable.circle_select);
                ten.setImageResource(R.drawable.circle_select);
                shiwu.setImageResource(R.drawable.circle_select);
                time=5;
                break;}
            case R.id.ten:
                if (flag3==false){
                three.setImageResource(R.drawable.circle_select);
                five.setImageResource(R.drawable.circle_select);
                ten.setImageResource(R.drawable.circle_selected);
                shiwu.setImageResource(R.drawable.circle_select);
                time=10;
                break;}
            case R.id.shiwu:
                if (flag4==false){
                    three.setImageResource(R.drawable.circle_select);
                    five.setImageResource(R.drawable.circle_select);
                    ten.setImageResource(R.drawable.circle_select);
                    shiwu.setImageResource(R.drawable.circle_selected);
                    time=15;
                break;}
            case R.id.back:
                Intent i=getIntent();
                i.putExtra("time",time);
                setResult(2,i);
                finish();
                break;

        }
    }
}
