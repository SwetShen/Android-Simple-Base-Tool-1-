package com.archerswet.mearsure;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @description:message
 * @author:archerswet@163.com
 * @date:2021/12/14
 */
public class MyView extends LinearLayout {

    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.RED);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.RED);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int w_size = MeasureSpec.getSize(widthMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int h_size = MeasureSpec.getSize(heightMeasureSpec);

        Log.d("data_","widthMode:" + w_mode + "");
        Log.d("data_","widthSize:" +w_size + "");
        Log.d("data_","heightMode:" +h_mode + "");
        Log.d("data_","heightSize:" +h_size + "");

        int w_spec = MeasureSpec.makeMeasureSpec(w_size,MeasureSpec.UNSPECIFIED);
        int h_spec = MeasureSpec.makeMeasureSpec(h_size,MeasureSpec.UNSPECIFIED);

        int child_size = getChildAt(0).getMeasuredHeight();

        setMeasuredDimension(w_size - 100,child_size);

    }


}
