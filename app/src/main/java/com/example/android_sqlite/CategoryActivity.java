package com.example.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText addCategoryNameTxt;
    private Button saveCategoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        addCategoryNameTxt = (EditText) findViewById(R.id.addCategoryNameTxt);
        saveCategoryBtn = findViewById(R.id.saveCategoryBtn);

        dbHelper = new DatabaseHelper(this);

        saveCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addCategoryNameTxt.getText().toString();

                if (name.length() == 0) {
                    Toast.makeText(CategoryActivity.this, "Name is empty",  Toast.LENGTH_LONG).show();
                    return; // bail
                }

                if (dbHelper.createCategory(name)){
                    Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CategoryActivity.this, "An error occurred",  Toast.LENGTH_LONG).show();
                }


                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

    }
}