//package com.example.ll.comsleep.activity;
//
//
//import android.content.Context;
//
//public class DataManager {
//    Context mContext;
//    static DataManager mDatamanager;
//    StatusChangeListener mStatusChangeListener;
//
//    public DataManager(Context context){
//        this.mContext = context;
//    }
//
//    public static DataManager instance(Context context){
//        if(mDatamanager == null){
//            mDatamanager = new DataManager(context);
//        }
//        return mDatamanager;
//    }
//
//    /**注册监听器**/
//    public void setStatusChangeListener(StatusChangeListener Listener){
//        this.mStatusChangeListener = Listener;
//    }
//
//    /**调用监听器中的方法**/
//    public void StatusChange(String data){
//        mStatusChangeListener.statuschange(data);
//    }
//}
