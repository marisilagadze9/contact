package com.example.contactapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity {

    EditText name,number;
    Button add;
    db d;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        name=findViewById(R.id.name);
        number=findViewById(R.id.number);
        add=findViewById(R.id.add);
        d=new db(getApplicationContext(),"db",null,1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String t=number.getText().toString();

                if(n.isEmpty() || t.isEmpty()){
                    Toast.makeText(getApplicationContext(),"sheavset orive veli",Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact c=new Contact(0,n,t);
                d.add(c);
                finish();



            }
        });

    }
}
