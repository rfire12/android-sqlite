package com.example.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Button addCategoryBtn;
    private Button addProductBtn;
    private EditText productNameTxt;
    private EditText productPriceTxt;
    private Spinner categorySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        dbHelper = new DatabaseHelper(this);

        addCategoryBtn = findViewById(R.id.addCategoryBtn);
        addProductBtn = findViewById(R.id.addProductBtn);
        productNameTxt = (EditText) findViewById(R.id.productNameTxt);
        productPriceTxt = (EditText) findViewById(R.id.productPriceTxt);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        setCategoryItems();

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
                String name = productNameTxt.getText().toString();
                String price = productPriceTxt.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();

                if (name.length() == 0 || price.length() == 0 || category.length() == 0) {
                    Toast.makeText(AddProductActivity.this, "All fields must be filled",  Toast.LENGTH_LONG).show();
                    return; // bail
                }

                if (dbHelper.createProduct(name, price, category)){
                    Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddProductActivity.this, "An error occurred",  Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    protected void setCategoryItems() {
        Cursor cursor = dbHelper.getProducts();
        ArrayList<String> productsList = new ArrayList<>();

        while(cursor.moveToNext()) {
            productsList.add(cursor.getString(1));
        }

        ArrayAdapter<String> productsListAdapter = new ArrayAdapter<String>(
                AddProductActivity.this,
                android.R.layout.simple_list_item_1,
                productsList
        );

        categorySpinner.setAdapter(productsListAdapter);
    }
}