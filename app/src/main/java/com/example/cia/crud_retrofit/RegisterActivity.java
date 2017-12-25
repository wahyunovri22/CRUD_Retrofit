package com.example.cia.crud_retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cia.crud_retrofit.model.ResponseLogin;
import com.example.cia.crud_retrofit.model.ResponseModel;
import com.example.cia.crud_retrofit.rest.ApiRequestBiodata;
import com.example.cia.crud_retrofit.rest.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail,edtPassword,edtRule;
    private Button btnRegister;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Binding();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("send data ....");
                pd.setCancelable(false);
                pd.show();

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String rule = edtRule.getText().toString();

                ApiRequestBiodata api = RetrofitConfig.getRetrofit().create(ApiRequestBiodata.class);

                Call<ResponseLogin> postLogin = api.postLogin(email,password,rule);
                postLogin.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        pd.dismiss();
                        Clear();

                        if (kode.equals("1")){
                            Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        //pd.dismiss();
                        Log.d("RETRO", "Falure : " + t.getMessage());
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    public void Clear(){
        edtRule.setText(null);
        edtEmail.setText(null);
        edtPassword.setText(null);
    }

    public void Binding(){
        edtEmail = (EditText)findViewById(R.id.edt_register_email);
        edtPassword = (EditText)findViewById(R.id.edt_register_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        edtRule = (EditText)findViewById(R.id.edt_register_rule);

        pd = new ProgressDialog(this);
    }
}
