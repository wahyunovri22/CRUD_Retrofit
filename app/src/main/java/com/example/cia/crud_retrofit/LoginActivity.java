package com.example.cia.crud_retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cia.crud_retrofit.model.ResponseModel;
import com.example.cia.crud_retrofit.rest.ApiRequestBiodata;
import com.example.cia.crud_retrofit.rest.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail,edtPassword;
    private Button btnLogin;
    private TextView txtRegister,txtLupa;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Binding();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("send data ....");
                pd.setCancelable(false);
                pd.show();

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                ApiRequestBiodata api = RetrofitConfig.getRetrofit().create(ApiRequestBiodata.class);
                Call<ResponseModel> login = api.Login(email,password);
                login.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        pd.dismiss();

                        if (kode.equals("1")){
                            Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("RETRO", "Falure : " + t.getMessage());
                    }
                });
            }
        });

        txtLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LupaPasswordActivity.class);
                startActivity(intent);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Binding(){
        edtEmail = (EditText)findViewById(R.id.edt_login_email);
        edtPassword = (EditText)findViewById(R.id.edt_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtRegister = (TextView) findViewById(R.id.txt_register);
        txtLupa = (TextView)findViewById(R.id.txt_lupa_password);
        pd = new ProgressDialog(this);
    }
}
