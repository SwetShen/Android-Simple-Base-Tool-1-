package com.archerswet.bookstore;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.archerswet.bookstore.bean.RecordCount;
import com.archerswet.bookstore.request.RequestInterface;
import com.archerswet.bookstore.request.RetrofitTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_edit_user)
    EditText edit_user;

    @BindView(R.id.login_edit_password)
    EditText edit_password;

    @BindView(R.id.login_btn_login)
    TextView btn_login;

    @BindView(R.id.login_btn_regedit)
    TextView btn_regedit;

    @BindView(R.id.login_ck_remember)
    CheckBox ck_remember;

    @BindView(R.id.login_tv_forget)
    TextView tv_forget;

    private Retrofit retrofit;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            String result = msg.getData().getString("data");
            Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();

            Gson gson = new Gson();
            RecordCount recordCount = gson.fromJson(result, new TypeToken<RecordCount>() {
            }.getType());
            if(recordCount == null) return;
            if ("empty account".equals(recordCount.getError())) {
                Toast.makeText(LoginActivity.this, "empty account", Toast.LENGTH_SHORT).show();
            } else if ("empty password".equals(recordCount.getError())) {
                Toast.makeText(LoginActivity.this, "empty password", Toast.LENGTH_SHORT).show();
            } else if ("no".equals(recordCount.getError())) {
                if (recordCount.getCount() == 1) {
                    Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "login error", Toast.LENGTH_SHORT).show();

                }
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        retrofit = RetrofitTool.getRetrofitInstance();
    }

    @OnClick(R.id.login_btn_login)
    public void loginUser(){
        String user = edit_user.getText().toString().trim();
        String password = edit_password.getText().toString().trim();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<ResponseBody> call = requestInterface.login(user,password);
        RetrofitTool.getRetrofitResult(call,handler);

    }
}
