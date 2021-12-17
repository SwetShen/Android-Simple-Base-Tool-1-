package com.archerswet.bookstore.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archerswet.bookstore.R;

/**
*@description:message
*@author:archerswet@163.com
*@date:2021/12/10 10:10
*/
public class LayoutInflaterTool {

    public static View getTabItemView(int drawable, String title, Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab_item,null);
        ImageView imageView = view.findViewById(R.id.tab_item_iv);
        TextView textView = view.findViewById(R.id.tab_item_tv);
        imageView.setImageResource(drawable);
        textView.setText(title);
        return view;
    }
}
