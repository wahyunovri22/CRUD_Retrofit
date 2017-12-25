package com.example.cia.crud_retrofit.model;

import java.util.List;

/**
 * Created by cia on 12/11/2017.
 */

public class ResponseModel {

    String kode,pesan;

    List<DataModel> biodata;


    public List<DataModel> getBiodata() {
        return biodata;
    }

    public void setBiodata(List<DataModel> biodata) {
        this.biodata = biodata;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
