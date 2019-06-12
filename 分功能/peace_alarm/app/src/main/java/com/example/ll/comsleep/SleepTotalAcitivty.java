package com.example.ll.comsleep;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SleepTotalAcitivty extends AppCompatActivity implements AlarmFragment.CallBackValue,RespiteFragment.RespiteCallBackValue {


    private List<String> respiteList;
    private List<String> alarmList;
    private TextView[] textViews;
    private ViewPager viewPager;
    private Fragment[] fragments;
    private Button buttonSave;
    private String alarmHourValue, alarmMintueValue;
    private String firstCardName="晨诗";

    private String alarmCardMusicName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sleep_total_layout );
        buttonSave = findViewById( R.id.save_all_time );


        init();
        viewPager.setAdapter( new MyAdapter( getSupportFragmentManager() ) );
        getVisibleFragment();
        viewPager.setCurrentItem( 0 );
        getMusic();

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                if (current == 0) {

                    Intent intent = new Intent();
                    intent.putExtra( "data", (Serializable) alarmList );
                    intent.setClass( getApplicationContext(), MaoSleep.class );
                    startActivity( intent );
                } else if (current == 1) {
                    Intent intent = new Intent();
                    intent.putExtra( "data", (Serializable) respiteList );
                    intent.setClass( getApplicationContext(), MaoSleep.class );
                    startActivity( intent );
                }
            }
        } );
    }

    public void init() {
        viewPager = findViewById( R.id.view_pager );
        LinearLayout linearLayout = findViewById( R.id.dot_layout );
        int count = linearLayout.getChildCount();
        textViews = new TextView[count];
        fragments = new Fragment[count];
        fragments[0] = new AlarmFragment();
        fragments[1] = new RespiteFragment();

        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedImage( position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );

        for (int i = 0; i < count; i++) {
            textViews[i] = (TextView) linearLayout.getChildAt( i );
            textViews[i].setEnabled( true );
            textViews[i].setTag( i );
            textViews[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int item = (int) v.getTag();
                    viewPager.setCurrentItem( item );
                    selectedImage( item );
                }
            } );
        }
    }

    private void selectedImage(int item) {
        //这一步从使能到不使能是状态发生了变化，只要状态发生了变化，那么就会简介调用dot里面的select
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setEnabled( true );
        }
        textViews[item].setEnabled( false );
    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super( fm );
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = SleepTotalAcitivty.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) return fragment;
            Log.e( "fragment", String.valueOf( fragment ) );
        }
        return null;
    }


    @Override
    public void SendMessageValue(List<String> data) {
        alarmList = new ArrayList<>( 2 );
        alarmList = data;
    }

    @Override
    public void SendRespiteValue(List<String> data) {
        respiteList = new ArrayList<>( 1 );
        respiteList = data;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView firstCardText;
        firstCardText = findViewById(R.id.fisrt_cart_alarm);
        if (requestCode==12&&resultCode==24) {
           firstCardName = data.getStringExtra( "yinyue" );
            firstCardText.setText(firstCardName  );
            Log.e( "收到没啦", firstCardName );
        } else {
            firstCardText.setText( "晨诗" );
        }
    }


  //  写一个公共方法，让AlarmActivity中获取到当前的值；
    public  String getMusic() {
        List<Map<Object, String>> list = new ArrayList<>();
        Map<Object, String> map0 = new HashMap<>();
        map0.put( "晨诗", "chenshi" );
        Map<Object, String> map1 = new HashMap<>();
        map1.put( "星光", "xingguang" );
        Map<Object, String> map2 = new HashMap<>();
        map2.put( "晓音", "xiaoyin" );
        Map<Object, String> map3 = new HashMap<>();
        map3.put( "浮光", "fuguang" );
        Map<Object, String> map4 = new HashMap<>();
        map4.put( "宿雨", "suyu" );
        Map<Object, String> map5 = new HashMap<>();
        map5.put( "秘境", "mijing" );
        Map<Object, String> map6 = new HashMap<>();
        map6.put( "荷风", "hefeng" );
        Map<Object, String> map7 = new HashMap<>();
        map7.put( "落霞", "luoxia" );
        Map<Object, String> map8 = new HashMap<>();
        map8.put( "初晴", "chuqing" );
        Map<Object, String> map9 = new HashMap<>();
        map9.put( "疏影", "shuying" );
        list.add( 0, map0 );
        list.add( 1, map1 );
        list.add( 2, map2 );
        list.add( 3, map3 );
        list.add( 4, map4 );
        list.add( 5, map5 );
        list.add( 6, map6 );
        list.add( 7, map7 );
        list.add( 8, map8 );
        list.add( 9, map9 );


        for (int i = 0; i <= 9; i++) {
            Map<Object, String> map = list.get( i );
            if (map.get( firstCardName ) != null) {
                return map.get( firstCardName );
            }
        }
        return "chenshi";
    }


}
