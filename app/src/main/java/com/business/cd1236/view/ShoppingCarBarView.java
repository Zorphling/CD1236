package com.business.cd1236.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.business.cd1236.R;

public class ShoppingCarBarView extends RelativeLayout {


    private Context context;
    private TextView bar_num;
    private int count = 0;


    public ShoppingCarBarView(Context context) {
        this(context, null);
    }


    public ShoppingCarBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ShoppingCarBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        RelativeLayout rl = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.shoppingcar_bar_view, this, true);
        bar_num = (TextView) rl.findViewById(R.id.bar_num);
        bar_num.setVisibility(GONE);
    }


    public void add() {
        bar_num.setVisibility(VISIBLE);
        count++;
        if (count < 100) {
            bar_num.setText(count + "");
        } else {
            bar_num.setText("99+");
        }
    }


    public void add(int n)  {
        if (n < 0) {
//            throw new Exception(ShoppingCarBarView.class.getSimpleName() + " add(int n).The param must be a positive num");
        }
        bar_num.setVisibility(VISIBLE);
        count += n;
        if (count < 100) {
            bar_num.setText(count + "");
        } else {
            bar_num.setText("99+");
        }
    }


    public void delete() {
        if (count == 0) {
            bar_num.setVisibility(GONE);
        } else {
            count--;
            if (count == 0) {
                bar_num.setVisibility(GONE);
            } else if (count > 0 && count < 100) {
                bar_num.setVisibility(VISIBLE);
                bar_num.setText(count + "");
            } else {
                bar_num.setVisibility(VISIBLE);
                bar_num.setText("99+");
            }
        }
    }


    public void deleteAll() {
        count = 0;
        bar_num.setVisibility(GONE);
    }
}