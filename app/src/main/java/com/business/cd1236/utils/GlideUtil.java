package com.business.cd1236.utils;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import com.bumptech.glide.Glide;
import com.business.cd1236.base.MyApplication;


public class GlideUtil {
    public static void loadImg(String url, ImageView imageView) {
        Glide.with(MyApplication.mApp).load(url).into(imageView);
    }

    public static void loadImg(int url, ImageView imageView) {
        Glide.with(MyApplication.mApp).load(url).into(imageView);
    }

    public static void loadImg(@DrawableRes @Nullable @RawRes Integer resourceId, ImageView imageView) {
        Glide.with(MyApplication.mApp).load(resourceId).into(imageView);
    }
}
