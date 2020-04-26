package com.business.cd1236.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.business.cd1236.R;
import com.business.cd1236.utils.StringUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.jess.arms.utils.ArmsUtils;

public class AlertEditDialog<T> extends Dialog implements View.OnClickListener {

    private Context context;
    private T object;
    private String type;
    private int position;
    private TextView tvTitle;
    private TextInputEditText etInputContent;
    private Button btnNeg;
    private Button btnPos;
    private OnPosClickListener onPosClickListener;

    public AlertEditDialog(Context context, T object, String type,int position, OnPosClickListener onPosClickListener) {
        super(context);
        this.context = context;
        this.object = object;
        this.type = type;
        this.position = position;
        this.onPosClickListener = onPosClickListener;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edittext, null);
        tvTitle = view.findViewById(R.id.tv_title);
        etInputContent = view.findViewById(R.id.et_input_content);
        btnNeg = view.findViewById(R.id.btn_neg);
        btnPos = view.findViewById(R.id.btn_pos);
        setContentView(view);

        btnNeg.setOnClickListener(this);
        btnPos.setOnClickListener(this);
        show();
        etInputContent.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_neg:
                dismiss();
                break;
            case R.id.btn_pos:
                if (!StringUtils.checkString(StringUtils.getEditText(etInputContent))) {
                    ArmsUtils.snackbarText("请输入内容");
                    return;
                }
                if (onPosClickListener != null) {
                    onPosClickListener.onPosClick(object, type, position,StringUtils.getEditText(etInputContent));
                }
                dismiss();
                break;
        }
    }

    @Override
    public void dismiss() {
        if (onPosClickListener != null) onPosClickListener = null;
        if (context != null) context = null;
        super.dismiss();
    }

    public interface OnPosClickListener<T> {
        void onPosClick(T obj, String type,int position, String editText);
    }
}