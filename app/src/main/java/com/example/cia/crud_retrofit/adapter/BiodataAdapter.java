package com.example.cia.crud_retrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cia.crud_retrofit.MainActivity;
import com.example.cia.crud_retrofit.R;
import com.example.cia.crud_retrofit.model.DataModel;

import java.util.List;

/**
 * Created by cia on 13/11/2017.
 */

public class BiodataAdapter extends RecyclerView.Adapter<BiodataAdapter.BiodataHolder> {

    private List<DataModel> dataModels;
    private Context context;

    public BiodataAdapter(Context context, List<DataModel> dataModels) {
        this.dataModels = dataModels;
        this.context = context;
    }

    @Override
    public BiodataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_biodata,parent,false);
        BiodataHolder holder = new BiodataHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(BiodataHolder holder, int position) {
        DataModel data = dataModels.get(position);
        holder.id.setText(data.getId());
        holder.nama.setText(dataModels.get(position).getNama());
        holder.usia.setText(dataModels.get(position).getUsia());
        holder.tempat.setText(dataModels.get(position).getTempat());
        holder.dm = data;
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class BiodataHolder extends RecyclerView.ViewHolder {
        TextView id,nama,usia,tempat;
        DataModel dm;
        public BiodataHolder(View itemView) {
            super(itemView);

            id = (TextView)itemView.findViewById(R.id.id_list);
            nama = (TextView)itemView.findViewById(R.id.nama_list);
            usia = (TextView)itemView.findViewById(R.id.usia_list);
            tempat = (TextView)itemView.findViewById(R.id.tempat_list);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("id", dm.getId());
                    intent.putExtra("nama", dm.getNama());
                    intent.putExtra("usia", dm.getUsia());
                    intent.putExtra("tempat", dm.getTempat());

                    context.startActivity(intent);
                }
            });
        }
    }

}
