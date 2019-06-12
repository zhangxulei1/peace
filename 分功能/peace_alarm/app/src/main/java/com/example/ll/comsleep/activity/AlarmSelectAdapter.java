package com.example.ll.comsleep.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ll.comsleep.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlarmSelectAdapter extends BaseAdapter {

    private Context context;
    private int itemid;
    private List<Map<String, Object>> list;

    public AlarmSelectAdapter(Context context, int itemid, List<Map<String, Object>> list) {
        this.context = context;
        this.itemid = itemid;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from( context );
            convertView = inflater.inflate( itemid, null );
        }

        TextView name = convertView.findViewById( R.id.tv_alarm_select_name );
        //RadioButton image = convertView.findViewById( R.id.alarm_select_icon );


        name.setText( (String) list.get( position ).get( "name" ) );

        return convertView;
    }



}