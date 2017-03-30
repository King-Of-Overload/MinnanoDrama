package com.salu.minnanodrama.activity.common;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.salu.minnanodrama.R;
import com.salu.minnanodrama.utils.ConstantUtil;
import com.salu.minnanodrama.utils.PreferenceUtils;

import java.lang.ref.WeakReference;

/**
 * 引导页
 * @author Alan-Mac
 */
public class IntroductActivity extends AppIntro {
    private WeakReference<Activity> mReference;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReference=new WeakReference<>(this);
        //TODO:此处需要替换资源
        addSlide(AppIntroFragment.newInstance("汇集众多日剧","只有你想不到", R.drawable.splash_cover,
                getResources().getColor(R.color.white),getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.font_light)));
        addSlide(AppIntroFragment.newInstance("汇集众多日剧","只有你想不到", R.drawable.splash_cover,
                getResources().getColor(R.color.white),getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.font_light)));
        addSlide(AppIntroFragment.newInstance("汇集众多日剧","只有你想不到", R.drawable.splash_cover,
                getResources().getColor(R.color.white),getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.font_light)));
        addSlide(AppIntroFragment.newInstance("汇集众多日剧","只有你想不到", R.drawable.splash_cover,
                getResources().getColor(R.color.white),getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.font_light)));
        setBarColor(getResources().getColor(R.color.colorAccent));
        setSeparatorColor(Color.parseColor("#2196F3"));
        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);
        setDoneText(getString(R.string.enter_text));
        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        //跳转到登录
        Intent intent= new Intent(mReference.get(), LoginActivity.class);
        intent.putExtra(ConstantUtil.ACTIVITY_NAME,IntroductActivity.class.getName());
        startActivity(intent);
        PreferenceUtils.putBoolean(ConstantUtil.IS_OPEN,true);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
