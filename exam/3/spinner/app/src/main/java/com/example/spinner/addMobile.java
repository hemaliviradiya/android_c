package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addMobile extends AppCompatActivity implements View.OnClickListener {

    EditText txtName;
    Spinner spncompany;
    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mobile);
        txtName = findViewById(R.id.txtName);
        spncompany =findViewById(R.id.spncompany);
        btnsubmit = findViewById(R.id.btnsubmit);

        String[] company = getResources().getStringArray(R.array.company);
        ArrayAdapter ad= new ArrayAdapter(this, android.R.layout.simple_list_item_1,company);
        spncompany.setAdapter(ad);

        btnsubmit.setOnClickListener(this);

        try {
            Bundle bundle = getIntent().getExtras();
            bundle.getString("mid");
            txtName.setText(bundle.getString("mname"));



        }
        catch (Exception e)
        {

        }

    }

    @Override
    public void onClick(View view) {
        DBHelper db = new DBHelper(addMobile.this);
        mobile mb = new mobile();
        mb.setName(txtName.getText().toString());
        mb.setCompany(spncompany.getSelectedItem().toString());
        int i = db.addmobile(mb);
        if(i==1)
        {
            Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}