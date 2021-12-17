package com.archerswet.bookstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.archerswet.bookstore.R;
import com.archerswet.bookstore.bean.Nav;
import com.archerswet.bookstore.tool.ScreenUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:message
 * @author:archerswet@163.com
 * @date:2021/12/12
 */
public class NavListAdapter extends RecyclerView.Adapter {

    private List<Nav> navList;
    private Context context;

    public NavListAdapter(List<Nav> navList, Context context) {
        this.navList = navList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_nav_item,parent,false);
        return new NavListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int width_pixel = ScreenUtil.getScreenWidth(context);
        NavListHolder navListHolder = (NavListHolder) holder;
        navListHolder.linearLayout.setMinimumWidth(width_pixel/4);
        navListHolder.imageView.setImageResource(navList.get(position).getDrawable());
        navListHolder.textView.setText(navList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return navList.size();
    }

    class NavListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nav_item_iv)
        ImageView imageView;

        @BindView(R.id.nav_item_tv)
        TextView textView;

        @BindView(R.id.nav_item_container)
        LinearLayout linearLayout;

        public NavListHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
