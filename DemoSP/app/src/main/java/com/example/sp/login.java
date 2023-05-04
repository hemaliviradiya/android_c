package com.example.sp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button btnlogin ;
    EditText txtname,txtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin = findViewById(R.id.btnlogin);
        txtname = findViewById(R.id.txtname);
        txtpass = findViewById(R.id.txtpass);

        SharedPreferences sharedPreferences= getSharedPreferences("student",MODE_PRIVATE);

        if(sharedPreferences.contains("name") && sharedPreferences.contains("pass"))
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            btnlogin.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("student",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",txtname.getText().toString());
        editor.putString("pass",txtpass.getText().toString());
        editor.commit();

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}