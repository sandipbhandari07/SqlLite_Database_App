package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;

public class Regpage extends AppCompatActivity {

    EditText username,password,repassword ;
    Button btnSignUp,btnsignIn;
    AwesomeValidation awesomeValidation;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regpage);

        username = (EditText)findViewById(R.id.reg_username);
        password = (EditText)findViewById(R.id.reg_password);
        repassword = findViewById(R.id.repassword);

        btnsignIn=findViewById(R.id.alreadyaccount);
        btnSignUp = (Button) findViewById(R.id.regbtn);

        myDB = new DBHelper(this);


        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regpage.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(Regpage.this, "Fill all the Fields..!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean usercheckResult = myDB.checkusername(user);
                        if (usercheckResult == false) {
                            Boolean regResult = myDB.addUser(user, pass);
                            if (regResult == true) {
                                Toast.makeText(Regpage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), homepageActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Regpage.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Regpage.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Regpage.this, "password not matching", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}