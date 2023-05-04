package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Fileopration extends AppCompatActivity implements View.OnClickListener {

    EditText fedit;
    Button fread,fwrite;
    TextView ftv;
    String string;
    File f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileopration);
        find();
        fwrite.setOnClickListener(this);
        fread.setOnClickListener(this);
    }
    public void find(){
        fedit = findViewById(R.id.fedit);
        fread = findViewById(R.id.fread);
        fwrite=findViewById(R.id.fwrite);
        ftv=findViewById(R.id.ftv);

    }

    @Override
    public void onClick(View v) {
        if(v==fwrite){
            try{
                string = fedit.getText().toString();
                f= new File("My.txt");
                if(string==null || string.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter some Content",Toast.LENGTH_LONG).show();
                }else{
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput(f.getName(), Context.MODE_PRIVATE);
                        fos.write(string.getBytes());
                        fos.close();
                        Toast.makeText(
                                getApplicationContext(),
                                f.getName() + " saved",
                                Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v==fread) {
            try {
                FileInputStream fin =openFileInput(f.getName());
                int c;
                String temp = "";
                while((c = fin.read()) != -1){
                    temp = temp + (char) c;
                }
                ftv.setText(temp);

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
}