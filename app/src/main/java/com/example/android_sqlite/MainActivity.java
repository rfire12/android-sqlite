package com.example.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ProductsListFragment productsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsListFragment = new ProductsListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, productsListFragment)
                .commit();
    }
}