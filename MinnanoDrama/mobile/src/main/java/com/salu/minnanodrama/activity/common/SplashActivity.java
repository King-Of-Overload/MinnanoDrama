package com.salu.minnanodrama.activity.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.salu.minnanodrama.R;
import com.salu.minnanodrama.utils.ConstantUtil;
import com.salu.minnanodrama.utils.PreferenceUtils;
import com.salu.minnanodrama.utils.SystemUiVisibilityUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SplashActivity extends Activity {
    private Unbinder binder;
    @BindView(R.id.splash_iv) ImageView mSplashImage;//启动背景imageView
    private static final int[] SPLASH_PIC=new int[]{R.drawable.splash_cover};
    private Handler mHandler;
    //动画执行时间
    private static final int ANIMATION_DURATION=3000;
    //缩放动画的结束值
    private static final float SCALE_END=1.2F;
    private static final int GO_HOME = 100;
    private static final int GO_LOGIN = 200;
    private WeakReference<Activity> mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        binder= ButterKnife.bind(this);
        mReference=new WeakReference<>(this);
        SystemUiVisibilityUtil.hideStatusBar(getWindow(),true);
        mHandler=new MyHandler();
        mSplashImage.setBackgroundResource(SPLASH_PIC[0]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences= PreferenceUtils.getPreferences();
        Boolean loginStatus=preferences.getBoolean(ConstantUtil.LOGIN_STATUS,false);
        if(loginStatus){
            mHandler.sendEmptyMessageDelayed(GO_HOME,1000);
        }else{
            mHandler.sendEmptyMessageDelayed(GO_LOGIN,1000);
        }
    }

    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GO_LOGIN:{
                    animateImage(1);
                    break;
                }
                case GO_HOME:{
                    animateImage(0);
                }
            }
        }
    }


    /**
      *执行初始动画
      *@param flag  0去主页  1去登录
     */
    private void animateImage(final int flag){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImage, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImage, "scaleY", 1f, SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.addListener(new MyAnimationListener(flag));
        set.start();
    }


    /**
     * 动画执行的监听器
     */
    public class MyAnimationListener extends AnimatorListenerAdapter {
        private int flag=0;
        MyAnimationListener(int flag){
            this.flag=flag;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            if (flag == 0) {//已登录
                //跳转到主界面
                Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
            } else {//未登录
                Boolean isOpen=PreferenceUtils.getBoolean(ConstantUtil.IS_OPEN,false);
                if(isOpen){//打开过
                    Intent intent= new Intent(mReference.get(), LoginActivity.class);
                    intent.putExtra(ConstantUtil.ACTIVITY_NAME,SplashActivity.class.getName());
                    startActivity(intent);
                }else{//没打开过,跳转到引导页
                    Intent intent=new Intent(mReference.get(),IntroductActivity.class);
                    startActivity(intent);
                }

            }
            finish();
        }
    }



    /**

     Observable.timer(2000, TimeUnit.MILLISECONDS)
     .compose(bindToLifecycle())
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe(aLong -> {
     finishTask();
     });

     * **/




}
