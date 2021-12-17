package com.archerswet.bookstore.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.archerswet.bookstore.R;
import com.archerswet.bookstore.adapter.NavListAdapter;
import com.archerswet.bookstore.bean.Nav;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:message
 * @author:archerswet@163.com
 * @date:2021/12/14
 */
public class NavRecyclerView extends LinearLayout {

    private Context context;
    private List<Nav> navList = new ArrayList<>();

    public NavRecyclerView(@NonNull Context context) {
        super(context,null);
        this.context = context;
        init();
    }

    public NavRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        this.context = context;
        init();
    }

    public NavRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    private void init(){

        navList.add(new Nav(R.drawable.nav_recent,"最新上架"));
        navList.add(new Nav(R.drawable.nav_announcement,"公告"));
        navList.add(new Nav(R.drawable.nav_query,"查询"));
        navList.add(new Nav(R.drawable.nav_help,"帮助"));
        navList.add(new Nav(R.drawable.nav_car,"借书记录"));
        navList.add(new Nav(R.drawable.nav_table,"统计数据"));

        LayoutInflater.from(context).inflate(R.layout.layout_nav,this);
        RecyclerView recyclerView = findViewById(R.id.nav_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new NavListAdapter(navList, getContext()));

//        this.setMeasuredDimension(getMeasuredWidth(),recyclerView.getMeasuredHeight());
    }
}
