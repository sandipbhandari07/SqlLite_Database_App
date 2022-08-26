package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Add_Expenses extends AppCompatActivity {

    TextView expensehead,date,amount;
    MaterialButton btnsave , back;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        expensehead =  findViewById(R.id.exphead);
        date = findViewById(R.id.date);
        amount = findViewById(R.id.amount);
        back = findViewById(R.id.backbtn);
        btnsave = findViewById(R.id.savebtn);

        addData();

        myDB=new DBHelper(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void addData(){
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean inserted =  myDB.addData(expensehead.getText().toString(),amount.getText().toString(),date.getText().toString());
              if (inserted==true)
              {
                  Toast.makeText(Add_Expenses.this, "success", Toast.LENGTH_SHORT).show();
              }
              else {
                  Toast.makeText(Add_Expenses.this, "failed", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}