package com.example.cia.crud_retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cia.crud_retrofit.model.ResponseModel;
import com.example.cia.crud_retrofit.rest.ApiRequestBiodata;
import com.example.cia.crud_retrofit.rest.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edtNama,edtUsia,edtTempat;
    Button btnInput,btnTampil,btnUpdate,btnDelete;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        koneksi();

        Intent tampilData = getIntent();
        final String idDta = tampilData.getStringExtra("id");
        if (idDta != null){
            btnTampil.setVisibility(View.GONE);
            btnInput.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            edtNama.setText(tampilData.getStringExtra("nama"));
            edtUsia.setText(tampilData.getStringExtra("usia"));
            edtTempat.setText(tampilData.getStringExtra("tempat"));
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Delete ...");
                pd.setCancelable(false);
                pd.show();
                ApiRequestBiodata api = RetrofitConfig.getRetrofit().create(ApiRequestBiodata.class);
                Call<ResponseModel> deleteBio = api.deleteBiodata(idDta);
                deleteBio.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Toast.makeText(MainActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        Intent i = new Intent(MainActivity.this, TampilActivity.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TampilActivity.class);
                startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("updating ...");
                pd.setCancelable(false);
                pd.show();

                String nama = edtNama.getText().toString();
                String umur = edtUsia.getText().toString();
                String tempat = edtTempat.getText().toString();

                ApiRequestBiodata api = RetrofitConfig.getRetrofit().create(ApiRequestBiodata.class);
                Call<ResponseModel> updateBio = api.updateBiodata(idDta, nama,umur,tempat);
                updateBio.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("retro", "response" + response.body().getPesan());
                        Toast.makeText(MainActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.dismiss();
                        Log.d("retro", "response" + t.getMessage());
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("send data ....");
                pd.setCancelable(false);
                pd.show();

                String mNama = edtNama.getText().toString();
                String mUsia = edtUsia.getText().toString();
                String mTempat = edtTempat.getText().toString();

                ApiRequestBiodata api = RetrofitConfig.getRetrofit().create(ApiRequestBiodata.class);

                Call<ResponseModel> sendBio = api.sendBiodata(mNama,mUsia,mTempat);
                sendBio.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        pd.dismiss();

                        if (kode.equals("1")){
                            Toast.makeText(MainActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("RETRO", "Falure : " + t.getMessage());

                    }
                });
            }
        });
    }

    public void koneksi(){
        edtNama = (EditText)findViewById(R.id.edt_input_nama);
        edtUsia = (EditText)findViewById(R.id.edt_input_usia);
        edtTempat = (EditText)findViewById(R.id.edt_input_tempat);
        btnInput = (Button)findViewById(R.id.btn_input_data);
        btnTampil = (Button)findViewById(R.id.btn_tampil);
        btnUpdate = (Button)findViewById(R.id.btn_update);
        btnDelete = (Button)findViewById(R.id.btn_delete);

        pd = new ProgressDialog(this);
    }
}
