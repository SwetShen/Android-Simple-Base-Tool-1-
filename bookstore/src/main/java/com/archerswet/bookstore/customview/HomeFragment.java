package com.archerswet.bookstore.customview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.archerswet.bookstore.R;
import com.archerswet.bookstore.adapter.BookListAdapter;
import com.archerswet.bookstore.adapter.NavListAdapter;
import com.archerswet.bookstore.bean.Book;
import com.archerswet.bookstore.bean.Nav;
import com.archerswet.bookstore.request.RequestInterface;
import com.archerswet.bookstore.request.RetrofitTool;
import com.archerswet.bookstore.tool.ScreenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    @BindView(R.id.home_rv)
    RecyclerView recyclerView;

    private Retrofit retrofit;



    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            String result = msg.getData().getString("data");
            Gson gson = new Gson();
            List<Book> books = gson.fromJson(result,new TypeToken<List<Book>>(){}.getType());
            if(books != null){
                recyclerView.setAdapter(new BookListAdapter(books,getContext()));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home,container,false);
        ButterKnife.bind(this,view);

//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new BookListAdapter());

        retrofit = RetrofitTool.getRetrofitInstance();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<ResponseBody> call = requestInterface.queryAll();
        RetrofitTool.getRetrofitResult(call,handler);


        return view;
    }
}
