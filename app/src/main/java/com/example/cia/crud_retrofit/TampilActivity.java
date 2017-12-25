package com.example.cia.crud_retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cia.crud_retrofit.adapter.BiodataAdapter;
import com.example.cia.crud_retrofit.model.DataModel;
import com.example.cia.crud_retrofit.model.ResponseModel;
import com.example.cia.crud_retrofit.rest.ApiRequestBiodata;
import com.example.cia.crud_retrofit.rest.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog pd;
    private List<DataModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        pd = new ProgressDialog(this);
        recyclerView = (RecyclerView)findViewById(R.id.rv_tampil);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.show();

        ApiRequestBiodata api = RetrofitConfig.getRetrofit().create(ApiRequestBiodata.class);
        Call<ResponseModel> getData = api.getBiodata();
        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.dismiss();
                list = response.body().getBiodata();
                mAdapter = new BiodataAdapter(TampilActivity.this, list);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TampilActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
