package com.salu.minnanodrama.activity.common;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.salu.minnanodrama.R;
import com.salu.minnanodrama.activity.base.RxBaseActivity;
import com.salu.minnanodrama.utils.DeviceUtils;
import com.salu.minnanodrama.utils.ImageUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class LoginActivity extends RxBaseActivity {
    @BindView(R.id.iv_login_bac)
    ImageView loginBacIV;

    private WeakReference<Activity> mReference;



    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mReference=new WeakReference<>(this);
        finishTask();
    }

    @Override
    public void finishTask() {
        super.finishTask();
        BitmapDrawable bd= (BitmapDrawable) loginBacIV.getDrawable();
        Bitmap bitmap=bd.getBitmap();
        Bitmap frostGlassBitmap= ImageUtils.addFrostedGlassEffect(mReference.get(),bitmap,10);
        if(DeviceUtils.hasJellyBean()){
            loginBacIV.setBackground(new BitmapDrawable(frostGlassBitmap));
        }else{
            loginBacIV.setBackgroundDrawable(new BitmapDrawable(frostGlassBitmap));
        }

    }

    @Override
    public void initToolBar() {

    }
}
