package com.business.cd1236.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.business.cd1236.R;


/**
 * 设置头像dialog
 */
public class SetHeaderDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private SelectPicListener selectPicListener;
    private final View tvPhotoTaken;
    private final View tvSelectPic;
    private final View tvCancel;

    @SuppressLint("StringFormatMatches")
    public SetHeaderDialog(Context context, SelectPicListener selectPicListener) {
        super(context);
        this.context = context;
        this.selectPicListener = selectPicListener;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_set_header, null);
        tvPhotoTaken = view.findViewById(R.id.tv_photo_taken);
        tvSelectPic = view.findViewById(R.id.tv_select_pic);
        tvCancel = view.findViewById(R.id.tv_cancel);


        setContentView(view);

        tvPhotoTaken.setOnClickListener(this);
        tvSelectPic.setOnClickListener(this);
        tvCancel.setOnClickListener(this);


        //dialog 一定设置透明 不然直角会隐藏 CardView 圆角
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = 100;
        window.setGravity(Gravity.BOTTOM);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_photo_taken:
                dismiss();
                break;
            case R.id.tv_select_pic:
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }


    @Override
    public void dismiss() {
        if (selectPicListener != null) selectPicListener = null;
        if (context != null) context = null;
        super.dismiss();
    }

    public interface SelectPicListener {
        void onPhototaken();

        void onSelectPic();
    }
}
