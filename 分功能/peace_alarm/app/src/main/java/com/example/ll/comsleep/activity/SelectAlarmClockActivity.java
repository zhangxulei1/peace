package com.example.ll.comsleep.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ll.comsleep.R;
import com.example.ll.comsleep.SleepTotalAcitivty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectAlarmClockActivity extends Activity {

    //public StatusChangeListener.DataManager datamanager;

    public static final String INTENAL_ACTION_3 ="com.example.demobroadcast.BroadcastAction";

    private ListView mListView; //首页的ListView
    private List<Brand> namesList; //用于装载数据的集合
    private int selectPosition = 0;//用于记录用户选择的变量,默认选中第一项；
    private Brand selectBrand; //用户选择的闹铃
    private String alarmMusicName;
    private ImageView imageViewCancel;

    private Map<Integer, Integer> musicId;
    private SoundPool sp;
    private String englishMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.select_alarm_clock );
        imageViewCancel = findViewById( R.id.iv_alarm_cancel );//获取返回按钮
        initView();
        initDatas();

        initPalyMusic();
        initMusicId();

        //datamanager= StatusChangeListener.DataManager.instance(getApplication());


        imageViewCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e( "闹铃取消", String.valueOf( selectPosition ) );
                cancelMusic( selectPosition );
                Intent intent = new Intent();
                intent.putExtra( "yinyue", alarmMusicName );
                Log.e( "传的有数据没啦", alarmMusicName );
                setResult( 24, intent );
                finish();
            }
        } );


    }

    private void initView() {
        mListView = (ListView) findViewById( R.id.musiclist );
    }

    private void initDatas() {
        //初始化ListView适配器的数据
        namesList = new ArrayList<>();
        Brand brand0 = new Brand( "晨诗" );
        Brand brand1 = new Brand( "星光" );
        Brand brand2 = new Brand( "晓音" );
        Brand brand3 = new Brand( "浮光" );
        Brand brand4 = new Brand( "宿雨" );
        Brand brand5 = new Brand( "秘境" );
        Brand brand6 = new Brand( "荷风" );
        Brand brand7 = new Brand( "落霞" );
        Brand brand8 = new Brand( "初晴" );
        Brand brand9 = new Brand( "疏影" );
        namesList.add( brand0 );
        namesList.add( brand1 );
        namesList.add( brand2 );
        namesList.add( brand3 );
        namesList.add( brand4 );
        namesList.add( brand5 );
        namesList.add( brand6 );
        namesList.add( brand7 );
        namesList.add( brand8 );
        namesList.add( brand9 );

        final MyAdapter myAdapter = new MyAdapter( this, namesList );
        mListView.setAdapter( myAdapter );
        mListView.setItemChecked( 0, true );
        alarmMusicName = "晨诗";
        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选中的参数
                selectPosition = position;
                myAdapter.notifyDataSetChanged();
                selectBrand = namesList.get( position );
                alarmMusicName = selectBrand.getBandname();
                //播放音乐
                palyMusic( position );
                getMusic();
                Log.e( "音乐名字",englishMusic );
                //datamanager.StatusChange( englishMusic );//创建Datamanager实例
                //Toast.makeText(SelectAlarmClockActivity.this,"您选中的闹钟铃声是："+selectBrand.getBandname(), Toast.LENGTH_SHORT).show();
                //用广播实现
                Intent intent = new Intent(INTENAL_ACTION_3);
                intent.putExtra("musicName", englishMusic);
                sendBroadcast(intent);//传递过去


            }
        } );
    }

    public class MyAdapter extends BaseAdapter {
        Context context;
        List<Brand> brandsList;
        LayoutInflater mInflater;

        public MyAdapter(Context context, List<Brand> mList) {
            this.context = context;
            this.brandsList = mList;
            mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        }

        @Override
        public int getCount() {
            return brandsList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = mInflater.inflate( R.layout.alarm_clock_item, parent, false );
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) convertView.findViewById( R.id.tv_alarm_select_name );
                viewHolder.select = (RadioButton) convertView.findViewById( R.id.alarm_select_icon );
                convertView.setTag( viewHolder );
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText( brandsList.get( position ).getBandname() );

            if (selectPosition == position) {
                viewHolder.select.setChecked( true );
            } else {
                viewHolder.select.setChecked( false );
            }
            return convertView;
        }
    }

    public class ViewHolder {
        TextView name;
        RadioButton select;
    }

    //播放音频的方法
    public void initPalyMusic() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams( 1 );
            sp = builder.build();
        } else {
            sp = new SoundPool( 1, 0, 5 );
        }
    }

    private void initMusicId() {
        musicId = new HashMap<>();
        musicId.put( 0, sp.load( getApplicationContext(), R.raw.chuchen, 0 ) );
        musicId.put( 1, sp.load( getApplicationContext(), R.raw.xingguang, 1 ) );
        musicId.put( 2, sp.load( getApplicationContext(), R.raw.xiaoyin, 2 ) );
        musicId.put( 3, sp.load( getApplicationContext(), R.raw.chuchen, 3 ) );
        musicId.put( 4, sp.load( getApplicationContext(), R.raw.suyu, 4 ) );
        musicId.put( 5, sp.load( getApplicationContext(), R.raw.mijing, 5 ) );
        musicId.put( 6, sp.load( getApplicationContext(), R.raw.chuchen, 6 ) );
        musicId.put( 7, sp.load( getApplicationContext(), R.raw.chuchen, 7 ) );
        musicId.put( 8, sp.load( getApplicationContext(), R.raw.chuchen, 8 ) );
        musicId.put( 9, sp.load( getApplicationContext(), R.raw.chuchen, 9 ) );
    }

    private void palyMusic(int id) {
        sp.play( musicId.get( id ), 1, 1, 0, 0, 1 );
    }

    //取消播放
    private void cancelMusic(int id) {
        sp.stop( musicId.get( id ) );
    }

//    //写一个公共方法，让AlarmActivity中获取到当前的值；
    public  void getMusic() {
        List<Map<Object, String>> list = new ArrayList<>();
        Map<Object, String> map0 = new HashMap<>();
        map0.put( "晨诗", "chenshi" );
        Map<Object, String> map1 = new HashMap<>();
        map1.put( "星光", "xingguang" );
        Map<Object, String> map2 = new HashMap<>();
        map2.put( "晓音", "xiaoyin" );
        Map<Object, String> map3 = new HashMap<>();
        map3.put( "浮光", "chenshi" );
        Map<Object, String> map4 = new HashMap<>();
        map4.put( "宿雨", "suyu" );
        Map<Object, String> map5 = new HashMap<>();
        map5.put( "秘境", "mijing" );
        Map<Object, String> map6 = new HashMap<>();
        map6.put( "荷风", "chenshi" );
        Map<Object, String> map7 = new HashMap<>();
        map7.put( "落霞", "chenshi" );
        Map<Object, String> map8 = new HashMap<>();
        map8.put( "初晴", "chenshi" );
        Map<Object, String> map9 = new HashMap<>();
        map9.put( "疏影", "chenshi" );
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
            if (map.get( alarmMusicName ) != null) {
                 englishMusic = map.get( alarmMusicName );
                 break;
            }else{
                englishMusic = "chenshi";
            }
        }
    }
}
