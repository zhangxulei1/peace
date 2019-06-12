package com.example.ll.comllpeace_part;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import java.util.List;

public class AralmAndRespiteViewpager extends ViewPager {
    private View leftView;
    private View rightView;
    private List<Fragment> fragmentList;
    private float mTrans;//滑动的距离
    private float mLScaleY; //最大的缩小比例
    private static final float IDefaultScaleX = 0.9f; //listView默认的缩小X的比例
    private static final float defaultScaleY = 0.9f; //默认缩小Y的比例
    private static final float scalePrecent = 0.1f;  //默认缩小的比例
    private int screenWidth = 0;//屏幕宽度
    private int scrollX = 230;  //滑动多少距离之后向向相反方向返回
    private int defaultTranslationX = 10;  //默认移动的X距离


    public AralmAndRespiteViewpager(@NonNull Context context) {
        super( context );
    }

    public AralmAndRespiteViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
    }

    public void setFragmentsList(List<Fragment> fragmentList){
        this.fragmentList = fragmentList;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false; //拦截
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false; //不给viewPager滑动
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
       if(position +1<= fragmentList.size() -1){
           //活动距离特别小时，我们认为没有动
           float effectOffset = isSmall(offset) ? 0 : offset;
           leftView = fragmentList.get( position ).getView();  //得到View
           rightView = fragmentList.get( position+1 ).getView();//得到view；
           startAnimation( leftView,rightView,effectOffset,offsetPixels );

       }
        super.onPageScrolled( position, offset, offsetPixels );
    }

    private void startAnimation(View leftView, View rightView, float effectOffset,int postionOffsetPixels) {
        if(leftView != null){
            manageLayer(leftView ,true);
            mLScaleY = (float)(defaultScaleY - scalePrecent*effectOffset);

            if(postionOffsetPixels > scrollX){
                mTrans = scrollX*((postionOffsetPixels - scrollX)/(screenWidth-scrollX))
                        +(postionOffsetPixels-scrollX)-defaultTranslationX;
            }else{
                leftView.bringToFront();
                mTrans = -defaultTranslationX;
            }
            ViewHelper.setTranslationX( leftView,mTrans );
            ViewHelper.setScaleX( leftView,IDefaultScaleX );
            ViewHelper.setScaleY( leftView,mLScaleY );
        }
        if(rightView != null){
            manageLayer( rightView,true );
            mLScaleY = (defaultScaleY - scalePrecent*(1-effectOffset));
            ViewHelper.setScaleX( rightView,IDefaultScaleX );
            ViewHelper.setScaleY( rightView,mLScaleY );
            if(postionOffsetPixels > scrollX){
                mTrans = -getWidth()+scrollX*2+defaultTranslationX;
                if(Math.abs( mTrans )>=getWidth()-postionOffsetPixels){
                mTrans = -getWidth() - getPageMargin()+postionOffsetPixels+defaultTranslationX;
                }
            }else{
                mTrans = -getWidth() - getPageMargin()+postionOffsetPixels*2+defaultTranslationX;
            }
            ViewHelper.setTranslationX( rightView,mTrans );
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private  void manageLayer(View v,boolean enableHardware){
        int layerType = enableHardware ? View.LAYER_TYPE_HARDWARE : View.LAYER_TYPE_NONE;
        if(layerType != v.getLayerType() ){
            v.setLayerType( layerType ,null );
        }

    }


    private boolean isSmall(float positionOffset) {
        return Math.abs( positionOffset ) <0.0001;
    }
}
