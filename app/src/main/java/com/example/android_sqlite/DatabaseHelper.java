package com.example.android_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "store", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String productQuery = "CREATE TABLE product (id integer primary key autoincrement, name varchar(128) not null, price varchar(128), category varchar(128) not null)";
        db.execSQL(productQuery);

        String categoryQuery = "CREATE TABLE category (id integer primary key autoincrement, name varchar(128) not null)";
        db.execSQL(categoryQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists product");
        db.execSQL("drop table if exists category");
        onCreate(db);
    }

    public boolean createProduct(String name, String price, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues product = new ContentValues();

        product.put("name", name);
        product.put("price", price);
        product.put("category", category);

        if( db.insert("product", null, product) != -1 ) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from product", null);
    }

    public boolean createCategory(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues category = new ContentValues();

        category.put("name", name);

        if( db.insert("category", null, category) != -1 ) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from category", null);
    }
}
