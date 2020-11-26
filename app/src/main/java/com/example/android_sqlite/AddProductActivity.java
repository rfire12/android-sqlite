package com.example.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddProductActivity extends AppCompatActivity {

    private Button addCategoryBtn;
    private Button addProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        addCategoryBtn = findViewById(R.id.addCategoryBtn);
        addProductBtn = findViewById(R.id.addProductBtn);

        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}