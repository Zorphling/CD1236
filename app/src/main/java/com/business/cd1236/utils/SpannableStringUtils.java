package com.business.cd1236.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

import com.business.cd1236.R;


/**
 * <p>
 * SpannableStringBuilder和SpannableString主要通过使用setSpan(Object what, int start, int end, int flags)改变文本样式。
 * 对应的参数：
 * start： 指定Span的开始位置
 * end： 指定Span的结束位置，并不包括这个位置。
 * flags：取值有如下四个
 * Spannable. SPAN_INCLUSIVE_EXCLUSIVE：前面包括，后面不包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本不会应用该样式
 * Spannable. SPAN_INCLUSIVE_INCLUSIVE：前面包括，后面包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本也会应用该样式
 * Spannable. SPAN_EXCLUSIVE_EXCLUSIVE：前面不包括，后面不包括
 * Spannable. SPAN_EXCLUSIVE_INCLUSIVE：前面不包括，后面包括
 * what： 对应的各种Span，不同的Span对应不同的样式。已知的可用类有：
 * BackgroundColorSpan : 文本背景色
 * ForegroundColorSpan : 文本颜色
 * MaskFilterSpan : 修饰效果，如模糊(BlurMaskFilter)浮雕
 * RasterizerSpan : 光栅效果
 * StrikethroughSpan : 删除线
 * SuggestionSpan : 相当于占位符
 * UnderlineSpan : 下划线
 * AbsoluteSizeSpan : 文本字体（绝对大小）
 * DynamicDrawableSpan : 设置图片，基于文本基线或底部对齐。
 * ImageSpan : 图片
 * RelativeSizeSpan : 相对大小（文本字体）
 * ScaleXSpan : 基于x轴缩放
 * StyleSpan : 字体样式：粗体、斜体等
 * SubscriptSpan : 下标（数学公式会用到）
 * SuperscriptSpan : 上标（数学公式会用到）
 * TextAppearanceSpan : 文本外貌（包括字体、大小、样式和颜色）
 * TypefaceSpan : 文本字体
 * URLSpan : 文本超链接
 * ClickableSpan : 点击事件
 * <p>
 * <p>
 * //TODO textView 实现 文本改变颜色 或者 加点击事件  根据需求部分文字改变
 */
public class SpannableStringUtils {


    /**
     * 使用SpannableString设置样式——字体颜色
     * 修改字体颜色
     *
     * @param content 改变的文本内容
     * @param color   "#009ad6"
     * @param start   开始文字的位置 坐标从 0开始
     * @param end     改变结束的位置 ，并不包括这个位置。
     * @return
     */
    public static SpannableString textColor(String content, String color, int start, int end) {
        SpannableString spannableString = new SpannableString(content);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
        spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // ((TextView)findViewById(R.id.mode1)).setText(spannableString);
        return spannableString;
    }
    public static SpannableString textColor(String content, @ColorInt int color, int start, int end) {
        SpannableString spannableString = new SpannableString(content);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // ((TextView)findViewById(R.id.mode1)).setText(spannableString);
        return spannableString;
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体颜色
     * SpannableStringBuilder
     * 修改字体颜色
     *
     * @param content 改变的文本内容
     * @param color   #009ad6
     * @param start   开始文字的位置 坐标从 0开始
     * @param end     改变结束的位置 ，并不包括这个位置。
     * @return
     */
    public static SpannableStringBuilder textColor02(String content, String color, int start, int end) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        // spannableString.append("已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
        spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // ((TextView)findViewById(R.id.mode2)).setText(spannableString);
        return spannableString;
    }

    /**
     * 使用SpannableStringBuilder设置样式——背景颜色
     * 设置背景颜色 --文本内容字体颜色不改变
     * 使用BackgroundColorSpan设置背景颜色。
     *
     * @param content 改变的文本内容
     * @param color   #009ad6
     * @param start   开始文字的位置  坐标从 0开始
     * @param end     改变结束的位置 ，并不包括这个位置。
     * @return
     */
    public static SpannableStringBuilder textBackgroundColor(String content, String color, int start, int end) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor(color));
        spannableString.setSpan(bgColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //((TextView)findViewById(R.id.mode3)).setText(spannableString);
        return spannableString;
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体大小
     * 设置字体大小
     * 使用AbsoluteSizeSpan设置字体大小。
     *
     * @param content  改变的文本内容
     * @param textSize 设置字体大小  代码中 设置字体的大小是 px 如需要设置dp 请先把dp转换为px
     * @param start    开始文字的位置  坐标从 0开始
     * @param end      改变结束的位置 ，并不包括这个位置。
     * @return
     */
    public static SpannableStringBuilder textSize(String content, int textSize, int start, int end) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(textSize);
        spannableString.setSpan(absoluteSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // ((TextView)findViewById(R.id.mode4)).setText(spannableString);
        return spannableString;
    }


    /**
     * 使用SpannableStringBuilder设置样式——粗体\斜体
     *
     * @param content 改变的文本内容
     * @param start   开始文字的位置  坐标从 0开始
     * @param end     改变结束的位置 ，并不包括这个位置。
     * @param Tepy    字体样式  1 :粗体  2:斜体 3:粗斜体
     * @return
     */
    public static SpannableStringBuilder textSetStyle(String content, int start, int end, int Tepy) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        // TODO setSpan可多次使用 也就是一段字符串上都可以使用  粗体,斜体,粗斜体 如有需要请自行配置使用
       /* StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);//粗体
        spannableString.setSpan(styleSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.ITALIC);//斜体
        spannableString.setSpan(styleSpan2, 3, 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan3 = new StyleSpan(Typeface.BOLD_ITALIC);//粗斜体
        spannableString.setSpan(styleSpan3, 6, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode5)).setText(spannableString);
        */
        StyleSpan styleSpan = null;
        switch (Tepy) {
            case Typeface.BOLD://粗体
                styleSpan = new StyleSpan(Typeface.BOLD);//粗体
                spannableString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                break;
            case Typeface.ITALIC://斜体
                styleSpan = new StyleSpan(Typeface.ITALIC);//斜体
                spannableString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                break;
            case Typeface.BOLD_ITALIC://粗斜体
                styleSpan = new StyleSpan(Typeface.BOLD_ITALIC);//粗斜体
                spannableString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                break;
            default://粗体
                styleSpan = new StyleSpan(Typeface.BOLD);//粗体
                spannableString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                break;
        }

        return spannableString;
    }


    /**
     * 使用SpannableStringBuilder设置样式——删除线   文字中间带横线
     *
     * @param content 改变的文本内容
     * @param start   开始文字的位置  坐标从 0开始
     * @param end     改变结束的位置 ，并不包括这个位置。
     */
    public static SpannableStringBuilder textDeleteLine(String content, int start, int end) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // ((TextView) findViewById(R.id.mode6)).setText(spannableString);
        return spannableString;
    }


    /**
     * 使用SpannableStringBuilder设置样式——下划线
     *
     * @param content
     * @param start
     * @param end
     * @return
     */
    public static SpannableStringBuilder textUnderline(String content, int start, int end) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //((TextView) findViewById(R.id.mode7)).setText(spannableString);
        return spannableString;
    }


    /**
     * 使用SpannableStringBuilder设置样式——图片
     *
     * @param context       上下文
     * @param content       改变文本内容
     * @param start
     * @param end
     * @param mipmapId      图片
     * @param isWidthHeight 是否需要测量宽高
     * @return
     */
    public static SpannableStringBuilder textImage(Context context, String content, int start, int end,
                                                   @DrawableRes int mipmapId, boolean isWidthHeight) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        ImageSpan imageSpan = null;

        if (isWidthHeight) { // 需要根据 测量图片的 宽高不
            Drawable drawable = context.getResources().getDrawable(mipmapId);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageSpan = new ImageSpan(drawable);
        } else {
            imageSpan = new ImageSpan(context, mipmapId);
        }
        //将index为6、7的字符用图片替代
        spannableString.setSpan(imageSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // ((TextView) findViewById(R.id.mode8)).setText(spannableString);
        return spannableString;
    }


    /**
     * 使用SpannableStringBuilder设置点击事件
     *
     * @param textView
     * @param content       文本内容
     * @param start         开始文字的位置  坐标从 0开始
     * @param end           改变结束的位置 ，并不包括这个位置。
     * @param clickableSpan 文字点击的回调
     *                      <p>
     *                      textView.setText(spannableString);
     *                      textView.setMovementMethod(LinkMovementMethod.getInstance()); 代码中指定index为 start--end 的字符都成了可点击的文本，其他区域还是不可点击的。
     */
    public static void textClickable(TextView textView, String content, int start, int end, ClickableSpan clickableSpan) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * 使用SpannableStringBuilder设置 文本颜色 和 点击事件
     *
     * @param textView
     * @param content
     * @param start
     * @param end
     * @param color
     * @param clickableSpan
     */
    public static void textColorAndClickable(TextView textView, String content, int start, int end,
                                             String color, ClickableSpan clickableSpan) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        try {
            //文字颜色
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
            spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        } catch (Exception e) {
        }
        //点击数据
        spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setVisibility(View.VISIBLE);
    }

    /**
     * 使用SpannableStringBuilder事件组合使用
     * 根据需求到时自己 封装改变了
     */
    public static void textCombination(Context context, TextView textView, String content) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);
        //图片
        ImageSpan imageSpan = new ImageSpan(context, R.mipmap.ic_launcher);
        spannableString.setSpan(imageSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //文字颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        spannableString.setSpan(colorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //文字背景颜色
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(bgColorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }


}
