package com.business.cd1236.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.business.cd1236.R;
import com.business.cd1236.utils.StringUtils;


/**
 */
public class PitemView extends LinearLayout {

    private TextView tv_subTitle;
    private TextView tv_num;
    private ViewSwitcher viewSwitcher;

    public PitemView(Context context) {
        this(context, null);
    }

    public PitemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PitemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PitemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        LayoutInflater.from(getContext()).inflate(R.layout.item_p_itemview, this);
        tv_subTitle = findViewById(R.id.tv);
        ImageView imageView = findViewById(R.id.iv);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.P_ItemView);
        tv_subTitle.setText(typedArray.getString(R.styleable.P_ItemView_title));
        tv_subTitle.setTextColor(typedArray.getColor(R.styleable.P_ItemView_titleColor, getResources().getColor(android.R.color.black)));
        imageView.setImageDrawable(typedArray.getDrawable(R.styleable.P_ItemView_drawable));

        typedArray.recycle();
    }


    public void setNumText(int num) {
        if (num == 0) {
            viewSwitcher.setDisplayedChild(0);
        } else {
            viewSwitcher.setDisplayedChild(1);
            tv_num.setText(String.valueOf(num));
        }
    }

    public void setSubTitleText(CharSequence text) {
        if (!StringUtils.isEmpty(text))
            tv_subTitle.setText(text);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

//        if (heightMode == MeasureSpec.EXACTLY) {
//            heightSize = heightSize <= mMaxHeight ? heightSize
//                    : (int) mMaxHeight;
//        }
//
//        if (heightMode == MeasureSpec.UNSPECIFIED) {
//            heightSize = heightSize <= mMaxHeight ? heightSize
//                    : (int) mMaxHeight;
//        }
//        if (heightMode == MeasureSpec.AT_MOST) {
//            heightSize = heightSize <= mMaxHeight ? heightSize
//                    : (int) mMaxHeight;
//        }
        int maxHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,
                heightMode);
        super.onMeasure(widthMeasureSpec, maxHeightMeasureSpec);
    }
}
