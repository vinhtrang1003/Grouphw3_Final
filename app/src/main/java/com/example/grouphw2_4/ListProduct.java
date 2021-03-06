package com.example.grouphw2_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import javaapplication1.Account;
import javaapplication1.Products;

public class ListProduct extends AppCompatActivity implements ListProductRecylerViewAdapter.ItemClickListener {
    int accPosition;
    List<Account> accList;
    List<Products> ProductsList;
    ListProductRecylerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        Intent intent = getIntent();

        // Try to hard code and display item 1 2 3
        accList = (List<Account>) intent.getSerializableExtra("accountList");
        accPosition = getIntent().getExtras().getInt("accPosition");;



        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListProductRecylerViewAdapter(this, accList, accPosition);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent2 = new Intent(this, DisplayProductDetails.class);
        intent2.putExtra("productPosition",(int) position);
        intent2.putExtra("accPos",(int) accPosition);
        intent2.putExtra("accList", (Serializable) accList);
        startActivity(intent2);

    }
}