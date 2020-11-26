package com.example.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class EditProductActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private Button editAddCategoryBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private EditText editProductNameTxt;
    private EditText editProductPriceTxt;
    private Spinner editProductCategorySpn;

    private String oldName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        dbHelper = new DatabaseHelper(this);

        editAddCategoryBtn = findViewById(R.id.editAddCategoryBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        editProductNameTxt = (EditText) findViewById(R.id.editProductNameTxt);
        editProductPriceTxt = (EditText) findViewById(R.id.editProductPriceTxt);
        editProductCategorySpn = (Spinner) findViewById(R.id.editProductCategorySpn);

        setCategoryItems();

        Intent intent = getIntent();
        String product = intent.getExtras().getString("product");
        setFieldsValues(product);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteProduct(oldName);
                Intent intent = new Intent(EditProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editProductNameTxt.getText().toString();
                String price = editProductPriceTxt.getText().toString();
                String category = editProductCategorySpn.getSelectedItem().toString();

                if (name.length() == 0 || price.length() == 0 || category.length() == 0) {
                    Toast.makeText(EditProductActivity.this, "All fields must be filled",  Toast.LENGTH_LONG).show();
                    return; // bail
                }

                dbHelper.updateProduct(name, price, category, oldName);

                Intent intent = new Intent(EditProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void setCategoryItems() {
        Cursor cursor = dbHelper.getCategories();
        ArrayList<String> categoryList = new ArrayList<>();

        while(cursor.moveToNext()) {
            categoryList.add(cursor.getString(1));
        }

        ArrayAdapter<String> categoryListAdapter = new ArrayAdapter<String>(
                EditProductActivity.this,
                android.R.layout.simple_list_item_1,
                categoryList
        );

        editProductCategorySpn.setAdapter(categoryListAdapter);
    }

    protected void setFieldsValues(String product) {
        Cursor cursor = dbHelper.getProduct(product);
        ArrayList<String> categoryList = new ArrayList<>();

        while(cursor.moveToNext()) {
            oldName = cursor.getString(1); // Save old name before it gets lost.
            editProductNameTxt.setText(cursor.getString(1));
            editProductPriceTxt.setText(cursor.getString(2));
        }

    }
}
