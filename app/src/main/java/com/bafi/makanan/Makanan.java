package com.bafi.makanan;

import java.io.Serializable;

public class Makanan implements Serializable {
    private String nama;
    private int harga;

    public static int MAKANAN_INTENT = 2;

    public Makanan(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public static String angkaKeString(int angka) {
        String angkaStr = String.valueOf(angka);
        String hasilStr = "";
        int count = 0;
        for(int i = angkaStr.length() - 1; i >= 0; i --) {
            hasilStr = angkaStr.charAt(i) + hasilStr;
            if((count - 2)%3 == 0 && i != 0)
                hasilStr = "." + hasilStr;

            count ++;
        }

        return hasilStr;
    }

    public String getFormattedHarga(){
        return "Rp. " + angkaKeString(harga);
    }
}
