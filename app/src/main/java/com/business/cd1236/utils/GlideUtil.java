package com.business.cd1236.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.business.cd1236.base.MyApplication;


public class GlideUtil {
    public static void loadImg(String url, ImageView imageView){
        Glide.with(MyApplication.mApp).load(url).into(imageView);
    }
}
