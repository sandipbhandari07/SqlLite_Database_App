package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    MaterialButton btnSignUp,btnsignIn;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        btnsignIn =  findViewById(R.id.loginbtn);
        btnSignUp =  findViewById(R.id.regbtnmain);

        myDB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Regpage.class);
                startActivity(intent);
            }
        });
        btnsignIn.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")|| pass.equals("")){
                    Toast.makeText(MainActivity.this,"Fill all the Fields..!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean usercheckResult = myDB.checkusernamePassword(user,pass);
                        if(usercheckResult==true){
                            Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, homepageActivity.class);
                            startActivity(intent); }
                        else{
                            Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
    }
}