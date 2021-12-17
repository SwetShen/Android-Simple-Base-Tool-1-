package com.archerswet.bookstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.archerswet.bookstore.R;
import com.archerswet.bookstore.bean.Book;
import com.archerswet.bookstore.customview.NavRecyclerView;
import com.archerswet.bookstore.request.RequestData;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:message
 * @author:archerswet@163.com
 * @date:2021/12/10
 */
public class BookListAdapter extends RecyclerView.Adapter {

    private List<Book> list;
    private Context context;

    public BookListAdapter(List<Book> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_book_list_item,parent,false);
        return new BookListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookListHolder listHolder = (BookListHolder) holder;
        if(position == 0){
            listHolder.tv_book_container.removeAllViews();
            NavRecyclerView navRecyclerView = new NavRecyclerView(context);
            listHolder.tv_book_container.addView(navRecyclerView);
            listHolder.tv_book_container.measure(listHolder.tv_book_container.getMeasuredWidth(),navRecyclerView.getMeasuredHeight());
        }else{
            //listHolder.iv_book_icon.setImageResource(R.drawable.book);
            Picasso.with(context).load(RequestData.BASEURL + "images/s34037479.jpg").into(listHolder.iv_book_icon);
            listHolder.tv_book_title.setText(list.get(position - 1).getTitle());
            listHolder.tv_book_author.setText(list.get(position - 1).getAuthor());
            listHolder.tv_book_date.setText(list.get(position - 1).getPublish_date());
        }


    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    class BookListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.book_list_item_iv)
        ImageView iv_book_icon;

        @BindView(R.id.book_list_item_title)
        TextView tv_book_title;

        @BindView(R.id.book_list_item_author)
        TextView tv_book_author;

        @BindView(R.id.book_list_item_date)
        TextView tv_book_date;

        @BindView(R.id.book_list_item_container)
        LinearLayout tv_book_container;

        public BookListHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
