package com.example.contactapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {
    EditText name,number;
    Button save,cansel;
    ImageButton back;
    db d;
    int id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout4);
        d=new db(this,"db",null,1);
        name=findViewById(R.id.Ename);
        number=findViewById(R.id.Enumber);
        save=findViewById(R.id.Esave);
        cansel=findViewById(R.id.Ecancel);
        back=findViewById(R.id.backbtn);

        id=getIntent().getIntExtra("id",-1);
        String ename=getIntent().getStringExtra("name");
        String enumber=getIntent().getStringExtra("number");

        name.setText(ename);
        number.setText(enumber);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newn=name.getText().toString().trim();
                String newt=number.getText().toString().trim();

                if(newn.isEmpty() || newt.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Contact newc=new Contact(id,newn,newt);
                d.Update(newc);
                finish();
            }
        });




    }


}
