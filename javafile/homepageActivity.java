package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class homepageActivity extends AppCompatActivity {

    MaterialButton btnadd,btnview,logout;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        btnadd=findViewById(R.id.addexp);
        logout=findViewById(R.id.logout);
        btnview=findViewById(R.id.viewexp);
        viewall();
        dbHelper=new DBHelper(this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(homepageActivity.this, "logout successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homepageActivity.this,Add_Expenses.class);
                startActivity(intent);
            }
        });
    }
    public void viewall(){
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res=dbHelper.readalldata();
                if (res.getCount() == 0){
                    showmessage("error","nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("id : "+res.getString(0)+"\n");
                    buffer.append("expname : "+res.getString(1)+"\n");
                    buffer.append("date : "+res.getString(2)+"\n");
                    buffer.append("amount : "+res.getString(3)+"\n\n");
                }
                //show all the data
                showmessage("Data",buffer.toString());

            }
        });
    }
    public void showmessage(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}