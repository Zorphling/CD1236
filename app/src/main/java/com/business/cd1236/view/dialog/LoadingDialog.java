package com.business.cd1236.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.business.cd1236.R;


public class LoadingDialog extends Dialog {

    private ImageView mIv_hog;
    private AnimationDrawable mAnimationStart;
    private Animation myAlphaAnimation;

    private LoadingDialog(@NonNull Context context, CharSequence charSequence) {
        super(context, R.style.CustomerDialogStyle);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") View rootView = inflater.inflate(R.layout.dialog_loading, null);
        TextView tvMessage = rootView.findViewById(R.id.tv_message);
        tvMessage.setText(charSequence);

        mIv_hog = rootView.findViewById(R.id.iv_hog_header);
//        mIv_hog.setImageResource(R.drawable.hog_animation_start);
//        mAnimationStart = (AnimationDrawable) mIv_hog.getDrawable();


        myAlphaAnimation= AnimationUtils.loadAnimation(context, R.anim.loading);
        myAlphaAnimation.setInterpolator(new LinearInterpolator());
        mIv_hog.startAnimation(myAlphaAnimation);


        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(rootView);
    }

    public LoadingDialog(@NonNull Context context) {
        this(context, "请求网络中...");
    }

    @Override
    public void show() {
        if (mAnimationStart != null && !mAnimationStart.isRunning())
            mAnimationStart.start();
        super.show();
    }

    @Override
    public void hide() {
        if (mAnimationStart != null && mAnimationStart.isRunning())
            mAnimationStart.stop();
        super.hide();
    }

    @Override
    public void dismiss() {
        if (mAnimationStart != null && mAnimationStart.isRunning())
            mAnimationStart.stop();
        super.dismiss();
    }

    @Override
    public void cancel() {
        if (mAnimationStart != null) {
            mAnimationStart.stop();
        }
        super.cancel();
    }
}
