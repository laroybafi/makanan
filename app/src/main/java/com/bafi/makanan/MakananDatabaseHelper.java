package com.bafi.makanan;

import android.content.ContentValues; //import library yang berisi kelas untuk mengakses dan publish data pada perangkat
import android.content.Context; //import library yang berisi kelas untuk mengakses dan publish data pada perangkat
import android.database.Cursor; //import library yang berisi kelas untuk mengakses dan publish data pada perangkat
import android.database.sqlite.SQLiteDatabase; //import library database sqlite pada android
import android.database.sqlite.SQLiteOpenHelper; //import library database sqlite pada android

import java.util.ArrayList;
import java.util.List;

public class MakananDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "makanan.db"; //nama database
    private static final String TABLE_MAKANAN = "makanan"; //nama tabel

    private static final String FIELD_NAMA = "nama"; //kata pada field yang akan diisikan
    private static final String FIELD_HARGA = "harga"; //kata pada field yang akan diisikan
    private static final int DATABASE_VERSION = 1; //identifier dari kita

    MakananDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    } //menambahkan kelas Java baru ke proyek yang bernama DictionaryDatabase

    @Override
    public void onCreate(SQLiteDatabase db) {               //membuat database baru, kalau sudah ada biasanya diskip
        db.execSQL("CREATE TABLE " + TABLE_MAKANAN +     //membuat tabel bernama Tabel dictionary
                "(_id integer PRIMARY KEY," +               //primary key dengan tipe data integer
                FIELD_NAMA + " TEXT, " +                    //field word yang berisi text string
                FIELD_HARGA + " TEXT);");              //field definition yang berisi text string
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Handle database upgrade as needed
    }

    public void saveRecord(String nama, String harga) { //mencari id dari kata yang akan disimpan
        long id = findMakananID(nama);
        if (id>0) {
            updateRecord(id, nama,harga); //kalau kata sudah ada dalam database, maka kata direplace dengan inputan baru
        } else {
            addRecord(nama,harga); //kalau kata belum ada di database maka akan ditambahkan baru
        }
    }

    public long addRecord(String nama, String harga) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIELD_NAMA, nama);
        values.put(FIELD_HARGA, harga);
        return db.insert(TABLE_MAKANAN, null, values);
    }

    public int updateRecord(long id, String word, String definition) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put(FIELD_NAMA, word);
        values.put(FIELD_HARGA, definition);
        return db.update(TABLE_MAKANAN, values, "_id = ?", new String[]{String.valueOf(id)});
    }
    public int deleteRecord(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_MAKANAN, "_id = ?", new String[]{String.valueOf(id)});
    }

    public long findMakananID(String word) {
        long returnVal = -1;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT _id FROM " + TABLE_MAKANAN + " WHERE " + FIELD_NAMA + " = ?",
                new String[]{word});
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            returnVal = cursor.getInt(0);
        }
        return returnVal;
    }

    public List<Makanan> getMakananList() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, " + FIELD_NAMA + ", " + FIELD_HARGA +
                " FROM " + TABLE_MAKANAN + " ORDER BY " + FIELD_NAMA +
                " ASC";
        Cursor cs = db.rawQuery(query, null);
        List<Makanan> makananList = new ArrayList();

        if (cs.moveToFirst()){
            do{
                String nama = cs.getString(cs.getColumnIndex(FIELD_NAMA));
                int harga = Integer.parseInt(cs.getString(cs.getColumnIndex(FIELD_HARGA)));
                // do what ever you want here
                makananList.add(new Makanan(nama, harga));
            }while(cs.moveToNext());
        }
        cs.close();
        return makananList;
    }
}
