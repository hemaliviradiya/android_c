package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sharedpf extends AppCompatActivity {

    Button Submit;
    EditText mob,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpf);


        SharedPreferences spf2 =getSharedPreferences("login",MODE_PRIVATE);
        if(spf2.contains("mobno") && spf2.contains("pass")){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else{
            Submit=findViewById(R.id.Submit);
            mob=findViewById(R.id.mob);
            pass=findViewById(R.id.pass);
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences spf =getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("mobno",mob.getText().toString());
                    editor.putString("pass",mob.getText().toString());
                    editor.commit();
                    mob.setText("");
                    pass.setText("");
                    finish();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                }
            });
        }
    }
}